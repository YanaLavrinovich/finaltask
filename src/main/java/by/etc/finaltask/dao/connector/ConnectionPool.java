package by.etc.finaltask.dao.connector;


import java.sql.*;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private static volatile ConnectionPool instance = new ConnectionPool();

    private static final int DEFAULT_POOL_SIZE = 5;
    private static final String DRIVER = "database.driver";
    private static final String URL = "database.url";
    private static final String USER = "database.user";
    private static final String PASSWORD = "database.password";
    private static final String POOL_SIZE = "database.poolSize";
    private static final String DATABASE = "database";


    private ConnectionPool() {
        ResourceBundle bundle = ResourceBundle.getBundle(DATABASE);
        this.driverName = bundle.getString(DRIVER);
        this.url = bundle.getString(URL);
        this.user = bundle.getString(USER);
        this.password = bundle.getString(PASSWORD);
        try {
            this.poolSize = Integer.valueOf(bundle.getString(POOL_SIZE));
        } catch (NumberFormatException e) {
            this.poolSize = DEFAULT_POOL_SIZE;
        }
    }

    public void initPoolData() throws ConnectionPoolException {
        try {
            Class.forName(driverName);
            givenAwayConQueue = new ArrayBlockingQueue<>(poolSize);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionQueue.add(connection);
            }
        } catch (SQLException e) {
            //logger.error(message, e);
            throw new ConnectionPoolException("SQL Exception: cannot get connection", e);
        } catch (ClassNotFoundException e) {
            //logger.error(message, e);
            throw new ConnectionPoolException("Can't find database driver class", e);
        }
    }

    public void dispose() throws ConnectionPoolException {
        clearConnectionQueue();
    }

    private void clearConnectionQueue() throws ConnectionPoolException {
        try {
            closeConnectionsQueue(connectionQueue);
            closeConnectionsQueue(givenAwayConQueue);
        } catch (SQLException e) {
            //logger.error(message, e);
            throw new ConnectionPoolException("Closing connection error", e);
        }

    }

    private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection;
        while ((connection = queue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            connection.close();
        }
    }

    public Connection takeConnection() {
        Connection connection;
        try {
            connection = connectionQueue.take();
            givenAwayConQueue.add(connection);
        } catch (InterruptedException e) {
            //logger.error(message, e);
            throw new ConnectionPoolException("Error connecting to the data source.", e);
        }
        return connection;
    }

    public Connection takeManualCommitConnection() throws ConnectionException {
        Connection connection = takeConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new ConnectionException("Can't set false commit.", e);
        }
        return connection;
    }


    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet)
            throws ConnectionPoolException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            //closeConnection(connection);
            //logger.error("Closing result set error", e);
            throw new ConnectionPoolException("Closing result set error", e);
        }
        closeStatement(statement);
        closeConnection(connection);
    }

    public void closeStatement(Statement statement) throws ConnectionPoolException {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            //logger.error("Closing statement error", e);
            throw new ConnectionPoolException("Closing statement error", e);
        }
    }

    public void closeConnection(Connection connection) throws ConnectionPoolException {
        if (!givenAwayConQueue.remove(connection)) {
            // logger.log(Level.ERROR, "Error deleting connection from the given away connections pool");
            throw new ConnectionPoolException("Error deleting connection from the given away connections pool");
        }
        try {
            if (connection.isClosed()) {
                connection = reopenConnection();
            }
            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }

        } catch (SQLException e) {
            //logger.error("Can't access connection", e);
            throw new ConnectionPoolException("Can't access connection", e);
        }

        if (!connectionQueue.offer(connection)) {
            throw new ConnectionPoolException("Error allocating connection in the pool");
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    private Connection reopenConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
