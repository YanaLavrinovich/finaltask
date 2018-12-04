package by.etc.finaltask.logic.builder;

import by.etc.finaltask.bean.Role;
import by.etc.finaltask.bean.Sex;
import by.etc.finaltask.bean.User;

public class UserBuilder {

    public User build(String login, String password, String firstName, String lastName, Sex sex, Role role){
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setSex(sex);
        newUser.setRole(role);

        return newUser;
    }
}
