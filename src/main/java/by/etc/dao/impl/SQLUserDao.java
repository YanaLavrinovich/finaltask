package by.etc.dao.impl;

import by.etc.bean.User;
import by.etc.dao.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SQLUserDao implements UserDao {

    private static final String URL = "jdbc:mysql://localhost/portal";
    private static final String USER = "root";
    private static final String PASSWORD = "Super_mario_01";
    private Connection connection;

    public SQLUserDao() throws SQLException {

    }

    public boolean signIn(String login, String password) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        final String SELECT_PASSWORD = "SELECT password FROM users WHERE login = ?";

        PreparedStatement statement = connection.prepareStatement(SELECT_PASSWORD);
        statement.setString(1,login);
        ResultSet resultSet = statement.executeQuery();
        boolean result = false;
        if(resultSet.next()) {
            String passwordFromBase = resultSet.getString(1);
            if(password.equals(passwordFromBase)) {
                result = true;
            }
        }
        connection.close();
        return result;
    }

    public void registration(User newUser) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        final String INSERT_USER = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?)";
        final String SELECT_ROLE_ID = "SELECT id FROM roles WHERE name = ?";

        PreparedStatement userStatement = connection.prepareStatement(INSERT_USER);
        PreparedStatement roleStatement = connection.prepareStatement(SELECT_ROLE_ID);

        roleStatement.setString(1, newUser.getRole().toString());
        ResultSet resultSet = roleStatement.executeQuery();

        if(resultSet.next()) {
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
        connection.close();
    }
}
