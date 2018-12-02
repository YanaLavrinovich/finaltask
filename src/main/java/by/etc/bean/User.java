package by.etc.bean;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Sex sex;
    private Role role;

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + id;
        result = prime * result + login.hashCode();
        result = prime * result + password.hashCode();
        result = prime * result + firstName.hashCode();
        result = prime * result + lastName.hashCode();
        result = prime * result + sex.hashCode();
        result = prime * result + role.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        User user = (User) obj;
        return user.getId() == id &&
             user.getLogin() != null && user.getLogin().equals(login) &&
                user.getPassword() != null && user.getPassword().equals(password) &&
                user.getFirstName() != null && user.getFirstName().equals(firstName) &&
                user.getLastName() != null && user.getLastName().equals(lastName) &&
                user.getSex() != null && user.getSex().equals(sex) &&
                user.getRole() != null && user.getRole().equals(role);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
