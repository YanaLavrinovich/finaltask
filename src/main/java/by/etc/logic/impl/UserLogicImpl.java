package by.etc.logic.impl;

import by.etc.dao.UserDao;
import by.etc.dao.impl.SQLUserDao;
import by.etc.logic.UserLogic;

import java.sql.SQLException;

public class UserLogicImpl implements UserLogic {

    public boolean isValidUser(String login, String password) throws SQLException {
        UserDao userDao = new SQLUserDao();
        return userDao.signIn(login, password);
    }
}
