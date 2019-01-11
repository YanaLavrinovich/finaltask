package by.etc.finaltask.logic.user;

import by.etc.finaltask.bean.Role;
import by.etc.finaltask.bean.Sex;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.bean.build_bean.UserBuilder;
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

        if (!validator.isValidEmail(email) || !validator.isValidPassword(password)) {
            throw new InvalidInputException("Wrong email or password");
        }

        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            return userDao.isValidPassword(email, password);
        } catch (DaoException e) {
            throw new UserLogicException("Exception in logic", e);
        }
    }

    public User getUserInformation(String email) throws UserLogicException, InvalidInputException {
        UserValidator validator = ValidatorFactory.getInstance().getUserValidator();

        if (!validator.isValidEmail(email)) {
            throw new InvalidInputException("Wrong email");
        }

        UserDao userDao = DaoFactory.getInstance().getUserDao();
        User user;
        try {
            user = userDao.getUserInfo(email);
        } catch (DaoException e) {
            throw new UserLogicException("Can't get information about user.", e);
        }
        return user;
    }

    @Override
    public void addNewUser(String email, String firstName, String lastName, String sex, String role, String password) throws UserLogicException, InvalidInputException {
        UserValidator validator = ValidatorFactory.getInstance().getUserValidator();

        if (!validator.isValidUserInfo(email, firstName, lastName, sex, role) || !validator.isValidPassword(password)) {
            throw new InvalidInputException("Wrong params in new user.");
        }

        Sex userSex = Sex.valueOf(sex.toUpperCase());
        Role userRole = Role.valueOf(role.toUpperCase());

        UserBuilder builder = new UserBuilder();
        User user = builder.build(email, firstName, lastName, userSex, userRole);

        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            userDao.registration(user, password);
        } catch (DaoException | DaoRollbackException e) {
            throw new UserLogicException("Can't add new user in base.", e);
        }
    }

    @Override
    public User takeUser(String userId) throws UserLogicException, InvalidInputException {
        UserValidator validator = ValidatorFactory.getInstance().getUserValidator();

        if (!validator.isValidId(userId)) {
            throw new InvalidInputException("Wrong user id." + userId);
        }
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        User user = null;
        int id = Integer.valueOf(userId);
        try {
            user = userDao.takeUser(id);
        } catch (DaoException e) {
            throw new UserLogicException("Can't take user.", e);
        }
        return user;
    }

    @Override
    public void remove(String userId, String role) throws InvalidInputException, UserLogicException {
        UserValidator validator = ValidatorFactory.getInstance().getUserValidator();

        if (!validator.isValidId(userId) || !validator.isValidRole(role)) {
            throw new InvalidInputException("Wrong user id or role.");
        }
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        int id = Integer.valueOf(userId);
        try {
            if(role.toUpperCase().equals(Role.TEACHER.toString())) {
                userDao.removeTeacher(id);
            } else if(role.toUpperCase().equals(Role.STUDENT.toString())) {
                userDao.removeStudent(id);
            }
        } catch (DaoException | DaoRollbackException e) {
            throw new UserLogicException("Can't remove user.", e);
        }
    }
}
