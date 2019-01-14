package by.etc.finaltask.bean;

import java.io.Serializable;

/**
 * Entity class for Users database table.
 */
public class User implements Serializable {

    /**
     * Serialization UID.
     */
    private static final long serialVersionUID = 4958483859493859385L;

    /**
     * The user's id.
     */
    private int id;

    /**
     * The user's email.
     */
    private String email;

    /**
     * The user's first name.
     */
    private String firstName;

    /**
     * The user's last name.
     */
    private String lastName;

    /**
     * The {@link Sex}.
     */
    private Sex sex;

    /**
     * The {@link Role}.
     */
    private Role role;

    /**
     * Is user deleted value.
     */
    private boolean isDeleted;

    /**
     * Defines possible user's sex.
     */
    public enum Sex {

        /**
         * The female sex.
         */
        FEMALE("female"),

        /**
         * The male sex.
         */
        MALE("male");

        private String stringValue;

        Sex(String stringValue) {
            this.stringValue = stringValue;
        }
    }

    /**
     * Defines possible user's roles.
     */
    public enum Role {

        /**
         * The admin role.
         */
        ADMIN,

        /**
         * The teacher role.
         */
        TEACHER,

        /**
         * The student role.
         */
        STUDENT;
    }

    public User() {
    }

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

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
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
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        User user = (User) obj;
        return user.getId() == id && user.isDeleted() == isDeleted &&
                user.getEmail() != null && user.getEmail().equals(email) &&
                user.getFirstName() != null && user.getFirstName().equals(firstName) &&
                user.getLastName() != null && user.getLastName().equals(lastName) &&
                user.getSex() != null && user.getSex().equals(sex) &&
                user.getRole() != null && user.getRole().equals(role);

    }

    @Override
    public String toString() {
        return String.format("Entity %s [id %d, email %s, first name %s, last name %s, sex %s, role %s, isDeleted %s]",
                getClass().getSimpleName(), id, email, firstName, lastName, sex, role, isDeleted);
    }
}
