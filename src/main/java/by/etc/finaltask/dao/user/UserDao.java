package by.etc.finaltask.dao.user;

import by.etc.finaltask.bean.Sex;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.dao.exception.DaoRollbackException;

import java.util.List;

public interface UserDao {
    boolean isValidPassword(String login, String password) throws DaoException;

    void registration(User newUser, String password) throws DaoException, DaoRollbackException;

    User getUserInfo(String login) throws DaoException;

    User takeUser(int userId) throws DaoException;

    void removeStudent(int userId) throws DaoRollbackException, DaoException;

    void removeTeacher(int userId) throws DaoRollbackException, DaoException;

    void editUser(int userId, String email, String firstName, String lastName, Sex userSex) throws DaoException;

    void restoreUser(int userId) throws DaoException;

    List<User> findAllUsers() throws DaoException;
}
