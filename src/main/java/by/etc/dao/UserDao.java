package by.etc.dao;

import by.etc.bean.User;

import java.sql.SQLException;

public interface UserDao {
    boolean signIn(String login, String password) throws SQLException;
    void registration(User newUser) throws SQLException;
}
