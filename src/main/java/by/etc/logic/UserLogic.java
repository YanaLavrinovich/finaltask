package by.etc.logic;

import java.sql.SQLException;

public interface UserLogic {

    boolean isValidUser(String login, String password) throws SQLException;
}
