package by.etc.finaltask.dao.user;

import by.etc.finaltask.domain.User;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.dao.exception.DaoRollbackException;

import java.util.List;

public interface UserDao {
    boolean isValidPassword(String email, String password) throws DaoException;

    void register(User newUser, String password) throws DaoException, DaoRollbackException;

    User getUserInfo(String email) throws DaoException;

    User takeUser(int userId) throws DaoException;

    void removeStudent(int userId) throws DaoRollbackException, DaoException;

    void removeTeacher(int userId) throws DaoRollbackException, DaoException;

    void editUser(int userId, String email, String firstName, String lastName, User.Sex userSex) throws DaoException;

    void restoreUser(int userId) throws DaoException;

    List<User> findAllUsers() throws DaoException;
}
