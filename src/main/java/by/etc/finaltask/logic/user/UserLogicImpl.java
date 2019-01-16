package by.etc.finaltask.logic.user;

import by.etc.finaltask.domain.User;
import by.etc.finaltask.domain.builddomain.UserBuilder;
import by.etc.finaltask.dao.DaoFactory;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.dao.exception.DaoRollbackException;
import by.etc.finaltask.dao.user.UserDao;
import by.etc.finaltask.logic.exception.InvalidInputException;
import by.etc.finaltask.logic.exception.UserLogicException;
import by.etc.finaltask.logic.validation.UserValidator;
import by.etc.finaltask.logic.validation.ValidationException;
import by.etc.finaltask.logic.validation.ValidatorFactory;

import java.util.List;

public class UserLogicImpl implements UserLogic {

    public boolean isValidUser(String email, String password) throws UserLogicException, InvalidInputException {
        UserValidator validator = ValidatorFactory.getInstance().getUserValidator();

        try {
            validator.isValidEmail(email);
            validator.isValidPassword(password);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong email or password", e);
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

        try {
            validator.isValidEmail(email);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong email", e);
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

        try {
            validator.isValidUserInfo(email, firstName, lastName, sex, role);
            validator.isValidPassword(password);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong params in new user.", e);
        }

        UserBuilder builder = new UserBuilder();
        builder.addEmail(email);
        builder.addFirstName(firstName);
        builder.addLastName(lastName);
        builder.addSex(sex);
        builder.addRole(role);
        User user = builder.build();

        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            userDao.register(user, password);
        } catch (DaoException | DaoRollbackException e) {
            throw new UserLogicException("Can't add new user in base.", e);
        }
    }

    @Override
    public User takeUser(String userId) throws UserLogicException, InvalidInputException {
        UserValidator validator = ValidatorFactory.getInstance().getUserValidator();

        try {
            validator.isValidId(userId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong user id." + userId, e);
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

        try {
            validator.isValidId(userId);
            validator.isValidRole(role);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong user id or role.", e);
        }

        UserDao userDao = DaoFactory.getInstance().getUserDao();
        int id = Integer.valueOf(userId);
        try {
            if(role.equals(User.Role.TEACHER.getStringValue())) {
                userDao.removeTeacher(id);
            } else if(role.equals(User.Role.STUDENT.getStringValue())) {
                userDao.removeStudent(id);
            }
        } catch (DaoException | DaoRollbackException e) {
            throw new UserLogicException("Can't remove user.", e);
        }
    }

    @Override
    public void editProfile(String userId, String email, String firstName, String lastName, String sex) throws InvalidInputException, UserLogicException {
        UserValidator userValidator = ValidatorFactory.getInstance().getUserValidator();

        try {
            userValidator.isValidEditUser(userId, email, firstName, lastName, sex);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong input parameters!", e);
        }

        int id = Integer.valueOf(userId);
        User.Sex userSex = User.Sex.valueOf(sex.toUpperCase());
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            userDao.editUser(id, email, firstName, lastName, userSex);
        } catch (DaoException e) {
            throw new UserLogicException("Can't edit user.", e);
        }
    }

    @Override
    public void restoreUser(String userId) throws InvalidInputException, UserLogicException {
        UserValidator validator = ValidatorFactory.getInstance().getUserValidator();

        try {
            validator.isValidId(userId);
        } catch (ValidationException e) {
            throw new InvalidInputException("Wrong user id.", e);
        }

        UserDao userDao = DaoFactory.getInstance().getUserDao();
        int id = Integer.valueOf(userId);
        try {
            userDao.restoreUser(id);
        } catch (DaoException e) {
            throw new UserLogicException("Can't restore user.", e);
        }
    }

    @Override
    public List<User> findAllUsers() throws UserLogicException {
        List<User> users;
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            users = userDao.findAllUsers();
        } catch (DaoException e) {
            throw new UserLogicException("Can't get list of users.", e);
        }
        return users;
    }
}
