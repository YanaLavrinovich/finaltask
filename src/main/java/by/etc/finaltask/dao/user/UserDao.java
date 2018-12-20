package by.etc.finaltask.dao.user;

import by.etc.finaltask.bean.User;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.dao.exception.DaoRollbackException;

public interface UserDao {
    boolean isValidPassword(String login, String password) throws DaoException;
    void registration(User newUser, String password) throws DaoException, DaoRollbackException;
    User getUserInfo(String login) throws DaoException;
}
