package by.etc.finaltask.logic;

import by.etc.finaltask.bean.User;
import by.etc.finaltask.logic.exception.UserLogicException;

public interface UserLogic {

    boolean isValidUser(String login, String password) throws UserLogicException;

    User getUserInformation(String login) throws UserLogicException;

    void addNewUser(User user) throws UserLogicException;
}
