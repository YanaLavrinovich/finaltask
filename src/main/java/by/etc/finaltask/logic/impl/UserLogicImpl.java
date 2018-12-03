package by.etc.finaltask.logic.impl;

import by.etc.finaltask.bean.User;
import by.etc.finaltask.dao.UserDao;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.dao.impl.SQLUserDao;
import by.etc.finaltask.logic.UserLogic;
import by.etc.finaltask.logic.exception.UserLogicException;

public class UserLogicImpl implements UserLogic {
    private static UserDao userDao = new SQLUserDao();

    public boolean isValidUser(String login, String password) throws UserLogicException {
        try {
            String passwordFromBase = userDao.getPasswordAccordingLogin(login);
            return passwordFromBase.equals(password);
        } catch (DaoException e) {
            throw new UserLogicException("Exception in logic", e);
        }
    }

    public User getUserInformation(String login) throws UserLogicException {
        User user = null;
        try {
            user = userDao.getUserInformation(login);
        } catch (DaoException e) {
            throw new UserLogicException("Can't get information about user", e);
        }
        return user;
    }
}
