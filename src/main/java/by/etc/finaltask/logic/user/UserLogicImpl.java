package by.etc.finaltask.logic.user;

import by.etc.finaltask.bean.User;
import by.etc.finaltask.dao.DaoFactory;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.dao.exception.DaoRollbackException;
import by.etc.finaltask.dao.user.UserDao;
import by.etc.finaltask.logic.exception.InvalidInputException;
import by.etc.finaltask.logic.exception.UserLogicException;
import by.etc.finaltask.logic.validator.UserValidator;
import by.etc.finaltask.logic.validator.ValidatorFactory;

public class UserLogicImpl implements UserLogic {

    public boolean isValidUser(String email, String password) throws UserLogicException, InvalidInputException {
        UserValidator validator = ValidatorFactory.getInstance().getUserValidator();

        if (validator.isValidEmail(email) && validator.isValidPassword(password)) {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            try {
                return userDao.isValidPassword(email, password);
            } catch (DaoException e) {
                throw new UserLogicException("Exception in logic", e);
            }
        } else {
            throw new InvalidInputException("Wrong email or password");
        }
    }

    public User getUserInformation(String email) throws UserLogicException, InvalidInputException {
        UserValidator validator = ValidatorFactory.getInstance().getUserValidator();

        if (validator.isValidEmail(email)) {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            User user;
            try {
                user = userDao.getUserInfo(email);
            } catch (DaoException e) {
                throw new UserLogicException("Can't get information about user.", e);
            }
            return user;
        } else {
            throw new InvalidInputException("Wrong email");
        }
    }

    @Override
    public void addNewUser(User user, String password) throws UserLogicException, InvalidInputException {
        UserValidator validator = ValidatorFactory.getInstance().getUserValidator();

        if (validator.isValidUserInfo(user) && validator.isValidPassword(password)) {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            try {
                userDao.registration(user, password);
            } catch (DaoException | DaoRollbackException e) {
                throw new UserLogicException("Can't add new user in base.", e);
            }
        } else {
            throw new InvalidInputException("Wrong params in user or password");
        }
    }
}
