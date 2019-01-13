package by.etc.finaltask.logic.user;

import by.etc.finaltask.bean.User;
import by.etc.finaltask.logic.exception.InvalidInputException;
import by.etc.finaltask.logic.exception.UserLogicException;

import java.util.List;

public interface UserLogic {

    boolean isValidUser(String email, String password) throws UserLogicException, InvalidInputException;

    User getUserInformation(String email) throws UserLogicException, InvalidInputException;

    void addNewUser(String email, String firstName, String lastName, String sex, String role, String password) throws UserLogicException, InvalidInputException;

    User takeUser(String userId) throws UserLogicException, InvalidInputException;

    void remove(String userId, String role) throws InvalidInputException, UserLogicException;

    void editProfile(String userId, String email, String firstName, String lastName, String sex) throws InvalidInputException, UserLogicException;

    void restoreUser(String userId) throws InvalidInputException, UserLogicException;

    List<User> findAllUsers() throws UserLogicException;
}
