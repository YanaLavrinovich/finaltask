package by.etc.finaltask;

import by.etc.finaltask.bean.User;
import by.etc.finaltask.logic.UserLogic;
import by.etc.finaltask.logic.exception.UserLogicException;
import by.etc.finaltask.logic.impl.UserLogicImpl;

public class Runner {
    public static void main(String[] args) {
        try {
            UserLogic userLogic = new UserLogicImpl();
            boolean result = userLogic.isValidUser("root","root");
            User user = userLogic.getUserInformation("root");
            System.out.println(result);
        } catch (UserLogicException e) {
            e.printStackTrace();
        }
    }
}
