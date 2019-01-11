package by.etc.finaltask.dao.user;

import by.etc.finaltask.bean.Role;
import by.etc.finaltask.bean.Sex;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.bean.build_bean.UserBuilder;
import by.etc.finaltask.dao.connector.ConnectionException;
import by.etc.finaltask.dao.connector.ConnectionPool;
import by.etc.finaltask.dao.connector.ConnectionPoolException;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.dao.exception.DaoRollbackException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserDao implements UserDao {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String CHECK_PASSWORD = "SELECT count(id) FROM users WHERE email = ? AND password = ? AND isDeleted = 'false'";
    private static final String INSERT_USER = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ROLE_ID = "SELECT id FROM roles WHERE name = ?";
    private static final String SELECT_INFO = "SELECT users.id, email, firstName, lastName, sex, roles.name FROM users" +
            " JOIN roles ON users.roles_id = roles.id WHERE email = ?";
    private static final String TAKE_USER_BY_ID = "SELECT email, firstName, lastName, sex, roles.name FROM users" +
            " JOIN roles ON users.roles_id = roles.id WHERE users.id = ?";
    private static final String REMOVE_USER = "UPDATE users SET isDeleted = 'true' WHERE id = ?";
    private static final String REMOVE_USER_OF_COURSE = "UPDATE training" +
            " SET course_status_id = (SELECT id FROM course_status WHERE course_status.name = 'leave')" +
            " WHERE users_id = ?";
    private static final String REMOVE_COURSES_OF_USER = "UPDATE courses" +
            " SET isDeleted = 'true' WHERE users_id = ?";
    private static final String REJECT_STUDENT_OF_DELETED_COURSE = "UPDATE training" +
                        " SET course_status_id = (SELECT id FROM course_status WHERE course_status.name = 'reject')" +
                        " WHERE courses_id IN (SELECT id FROM courses WHERE users_id = ?)";

    public boolean isValidPassword(String login, String password) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String countFromBase = null;
        try {
            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement(CHECK_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                countFromBase = resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception with dataBase", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Exception with take connection", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return !countFromBase.equals("0");
    }

    public void registration(User newUser, String password) throws DaoException, DaoRollbackException {
        Connection connection = null;
        PreparedStatement userStatement = null;
        PreparedStatement roleStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeManualCommitConnection();
            userStatement = connection.prepareStatement(INSERT_USER);
            roleStatement = connection.prepareStatement(SELECT_ROLE_ID);

            roleStatement.setString(1, newUser.getRole().toString());
            resultSet = roleStatement.executeQuery();

            if (resultSet.next()) {
                int roleId = resultSet.getInt(1);
                userStatement.setInt(1, newUser.getId());
                userStatement.setString(2, newUser.getEmail());
                userStatement.setString(3, password);
                userStatement.setString(4, newUser.getFirstName());
                userStatement.setString(5, newUser.getLastName());
                userStatement.setString(6, newUser.getSex().toString());
                userStatement.setInt(7, roleId);
                userStatement.setString(8, "false");

                userStatement.execute();
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoRollbackException("Exception with rollback.", e);
            }
            throw new DaoException("Can't insert new user", e);
        } catch (ConnectionPoolException | ConnectionException e) {
            throw new DaoException("Exception with take connection", e);
        } finally {
            connectionPool.closeStatement(roleStatement);
            connectionPool.closeConnection(connection, userStatement, resultSet);
        }
    }

    public User getUserInfo(String email) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement(SELECT_INFO);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setFirstName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setSex(Sex.valueOf(resultSet.getString(5).toUpperCase()));
                user.setRole(Role.valueOf(resultSet.getString(6).toUpperCase()));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Exception with finding user", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Exception with take connection", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public User takeUser(int userId) throws DaoException {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            UserBuilder userBuilder = new UserBuilder();
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(TAKE_USER_BY_ID);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                Sex sex = Sex.valueOf(resultSet.getString(4).toUpperCase());
                Role role = Role.valueOf(resultSet.getString(5).toUpperCase());
                user = userBuilder.build(email, firstName, lastName, sex, role);
                user.setId(userId);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't take user.", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return user;
    }

    @Override
    public void removeStudent(int userId) throws DaoRollbackException, DaoException {
        Connection connection = null;
        PreparedStatement trainingStatement = null;
        PreparedStatement userStatement = null;
        try {
            connection = connectionPool.takeManualCommitConnection();
            userStatement = connection.prepareStatement(REMOVE_USER);
            userStatement.setInt(1, userId);
            userStatement.execute();

            trainingStatement = connection.prepareStatement(REMOVE_USER_OF_COURSE);
            trainingStatement.setInt(1, userId);
            trainingStatement.execute();
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoRollbackException("Exception with rollback.", e);
            }
            throw new DaoException("Can't remove user.", e);
        } catch (ConnectionException e) {
            throw new DaoException("Can't get connection.", e);
        } finally {
            connectionPool.closeStatement(trainingStatement);
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void removeTeacher(int userId) throws DaoRollbackException, DaoException {
        Connection connection = null;
        PreparedStatement courseStatement = null;
        PreparedStatement userStatement = null;
        PreparedStatement trainingStatement = null;
        try {
            connection = connectionPool.takeManualCommitConnection();
            userStatement = connection.prepareStatement(REMOVE_USER);
            userStatement.setInt(1, userId);
            userStatement.execute();

            courseStatement = connection.prepareStatement(REMOVE_COURSES_OF_USER);
            courseStatement.setInt(1, userId);
            courseStatement.execute();

            trainingStatement = connection.prepareStatement(REJECT_STUDENT_OF_DELETED_COURSE);
            trainingStatement.setInt(1, userId);
            trainingStatement.execute();

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoRollbackException("Exception with rollback.", e);
            }
            throw new DaoException("Can't remove user.", e);
        } catch (ConnectionException e) {
            throw new DaoException("Can't get connection.", e);
        } finally {
            connectionPool.closeStatement(userStatement);
            connectionPool.closeStatement(trainingStatement);
            connectionPool.closeStatement(courseStatement);
            connectionPool.closeConnection(connection);
        }
    }
}
