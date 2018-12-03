package by.etc.finaltask.dao;

import by.etc.finaltask.bean.User;
import by.etc.finaltask.dao.exception.DaoException;

public interface UserDao {
    String getPasswordAccordingLogin(String login) throws DaoException;
    void registration(User newUser) throws DaoException;
    User getUserInformation(String login) throws DaoException;
}
