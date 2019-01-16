package by.etc.finaltask.domain.builddomain;

import by.etc.finaltask.domain.User;
import by.etc.finaltask.logic.validation.UserValidatorImpl;

import java.io.Serializable;

/**
 * Build user. Parameters are validated with {@link UserValidatorImpl}.
 */
public class UserBuilder implements Serializable {
    private static final long serialVersionUID = 2299823445903644174L;

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private User.Sex sex;
    private User.Role role;
    private boolean isDeleted;

    public UserBuilder() {
        sex = User.Sex.MALE;
        role = User.Role.STUDENT;
    }

    /**
     * Adds id to {@link User}.
     *
     * @param id user id
     */
    public void addId(int id) {
        this.id = id;
    }

    /**
     * Adds email to {@link User}.
     *
     * @param email user email
     */
    public void addEmail(String email) {
        this.email = email;
    }

    /**
     * Adds first name to {@link User}.
     *
     * @param firstName user first name
     */
    public void addFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Adds last name to {@link User}.
     *
     * @param lastName user last name
     */
    public void addLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Adds sex to {@link User}.
     *
     * @param sex user sex
     */
    public void addSex(String sex) {
        this.sex = User.Sex.valueOf(sex.toUpperCase());
    }

    /**
     * Adds role to {@link User}.
     *
     * @param role user role
     */
    public void addRole(String role) {
        this.role = User.Role.valueOf(role.toUpperCase());
    }

    /**
     * Adds is deleted to {@link User}.
     *
     * @param isDeleted user is deleted
     */
    public void addIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * Builds {@link User}.
     *
     * @return new user
     */
    public User build() {
        User newUser = new User();
        newUser.setId(id);
        newUser.setEmail(email);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setSex(sex);
        newUser.setRole(role);
        newUser.setIsDeleted(isDeleted);
        return newUser;
    }
}
