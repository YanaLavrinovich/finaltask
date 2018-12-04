package by.etc.finaltask.dao.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    private ConnectionPool() {
        ResourceBundle bundle = ResourceBundle.getBundle("database");
        this.driverName = bundle.getString("db.driver");
        this.url = bundle.getString("db.url");
        this.user = bundle.getString("db.user");
        this.password = bundle.getString("db.password");
        try {
            this.poolSize = Integer.valueOf(bundle.getString("db.poolSize"));
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
            final String message = "SQL Exception: cannot get connection";
            //logger.error(message, e);
            throw new ConnectionPoolException(message, e);
        } catch (ClassNotFoundException e) {
            final String message = "Can't find database driver class";
            //logger.error(message, e);
            throw new ConnectionPoolException(message, e);
        }
    }

    public void dispose() throws ConnectionPoolException {
        clearConnectionQueue();
    }

    private void clearConnectionQueue() throws ConnectionPoolException {
        try {
            closeConnectionsQueue(givenAwayConQueue);
            closeConnectionsQueue(connectionQueue);
        } catch (SQLException e) {
            final String message = "Closing connection error";
            //logger.error(message, e);
            throw new ConnectionPoolException(message, e);
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

    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
            givenAwayConQueue.add(connection);
        } catch (InterruptedException e) {
            final String message = "Error connecting to the data source.";
            //logger.error(message, e);
            throw new ConnectionPoolException(message, e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) throws ConnectionPoolException {
        if (!givenAwayConQueue.remove(connection)) {
            final String message = "Error deleting connection from the given away connections pool";
            //logger.error(message);
            throw new ConnectionPoolException(message);
        }
        try {
            if (connection.isClosed()) {
                connection = reopenConnection();
            }
            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }

        } catch (SQLException e) {
            final String message = "Can't access connection";
            //logger.error(message, e);
            throw new ConnectionPoolException(message, e);
        }

        if (!connectionQueue.offer(connection)) {
            final String message = "Error allocating connection in the pool";
            throw new ConnectionPoolException(message);
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    private Connection reopenConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
