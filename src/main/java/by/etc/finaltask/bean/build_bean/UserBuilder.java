package by.etc.finaltask.bean.build_bean;

import by.etc.finaltask.bean.Role;
import by.etc.finaltask.bean.Sex;
import by.etc.finaltask.bean.User;

public class UserBuilder {

    public User build(String email, String firstName, String lastName, Sex sex, Role role){
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setSex(sex);
        newUser.setRole(role);
        return newUser;
    }

    public User build(int id, String email, String firstName, String lastName){
        User newUser = new User();
        newUser.setId(id);
        newUser.setEmail(email);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        return newUser;
    }
}
