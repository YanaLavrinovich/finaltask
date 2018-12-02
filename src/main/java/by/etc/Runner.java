package by.etc;

import by.etc.dao.UserDao;
import by.etc.dao.impl.SQLUserDao;
import by.etc.logic.UserLogic;
import by.etc.logic.impl.UserLogicImpl;

import java.sql.SQLException;

public class Runner {
    public static void main(String[] args) {
        try {
            UserLogic userLogic = new UserLogicImpl();
            boolean result = userLogic.isValidUser("root","root");

            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
