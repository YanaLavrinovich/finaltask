package by.etc.finaltask.dao.impl;

import by.etc.finaltask.bean.Role;
import by.etc.finaltask.bean.Sex;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.dao.UserDao;
import by.etc.finaltask.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserDao implements UserDao {

    private static final String URL = "jdbc:mysql://localhost/portal?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "Super_mario_01";
    private Connection connection;

    public SQLUserDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("lol");
        }
    }

    public String getPasswordAccordingLogin(String login) throws DaoException {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            final String SELECT_PASSWORD = "SELECT password FROM users WHERE login = ?";

            PreparedStatement statement = connection.prepareStatement(SELECT_PASSWORD);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            String passwordFromBase = null;
            if (resultSet.next()) {
                passwordFromBase = resultSet.getString(1);
            }
            return passwordFromBase;
        } catch (SQLException e) {
            throw new DaoException("Exception with authorization", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void registration(User newUser) throws DaoException {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            final String INSERT_USER = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?)";
            final String SELECT_ROLE_ID = "SELECT id FROM roles WHERE name = ?";

            PreparedStatement userStatement = connection.prepareStatement(INSERT_USER);
            PreparedStatement roleStatement = connection.prepareStatement(SELECT_ROLE_ID);

            roleStatement.setString(1, newUser.getRole().toString());
            ResultSet resultSet = roleStatement.executeQuery();

            if (resultSet.next()) {
                int roleId = resultSet.getInt(1);
                userStatement.setInt(1, newUser.getId());
                userStatement.setString(2, newUser.getLogin());
                userStatement.setString(3, newUser.getPassword());
                userStatement.setString(4, newUser.getFirstName());
                userStatement.setString(5, newUser.getLastName());
                userStatement.setString(6, newUser.getSex().toString());
                userStatement.setInt(7, roleId);

                userStatement.execute();
            }
        } catch (SQLException e) {
            throw new DaoException("Can't insert new user", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public User getUserInformation(String login) throws DaoException {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            final String SELECT_INFO = "SELECT users.id, login, password, firstName, lastName, sex, roles.name FROM users" +
                    " JOIN roles ON users.roleId = roles.id" +
                    " WHERE login = ?";

            PreparedStatement statement = connection.prepareStatement(SELECT_INFO);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setFirstName(resultSet.getString(4));
                user.setLastName(resultSet.getString(5));
                user.setSex(Sex.valueOf(resultSet.getString(6).toUpperCase()));
                user.setRole(Role.valueOf(resultSet.getString(7).toUpperCase()));

            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Exception with finding user", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
