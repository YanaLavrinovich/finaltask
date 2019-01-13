package by.etc.finaltask.bean;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 4958483859493859385L;

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private Sex sex;
    private Role role;
    private String isDeleted;

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setSex(by.etc.finaltask.bean.Sex sex) {
        this.sex = sex;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + id;
        result = prime * result + email.hashCode();
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
             user.getEmail() != null && user.getEmail().equals(email) &&
                user.getFirstName() != null && user.getFirstName().equals(firstName) &&
                user.getLastName() != null && user.getLastName().equals(lastName) &&
                user.getSex() != null && user.getSex().equals(sex) &&
                user.getRole() != null && user.getRole().equals(role);
    }

    @Override
    public String toString() {
        return String.format("Entity %s [id %d, email %s, first name %s, last name %s, sex %s, role %s]",
                getClass().getSimpleName(), id, email, firstName, lastName, sex, role);
    }
}
