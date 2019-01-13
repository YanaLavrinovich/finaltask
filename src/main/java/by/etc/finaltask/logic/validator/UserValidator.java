package by.etc.finaltask.logic.validator;

import by.etc.finaltask.bean.User;

public class UserValidator {
    private static final String ID_REGEX = "^[\\d]+$";
    private static final String PASSWORD_REGEX = "^[\\w\\d._]{4,12}$";
    private static final String EMAIL_REGEX = "^[\\w._\\d-]+@[A-Za-z]+.[A-Za-z]{2,3}$";
    private static final String NAME_REGEX = "^[\\w]+$";
    private static final String SEX_REGEX = "^(FEMALE|MALE|МУЖЧИНА|ЖЕНЩИНА)$";
    private static final String ROLE_REGEX = "^(ADMIN|TEACHER|STUDENT|АДМИН|УЧИТЕЛЬ|СТУДЕНТ)$";

    public boolean isValidId(String id) {
        return id != null && id.matches(ID_REGEX);
    }

    public boolean isValidPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    public boolean isValidUserInfo(String email, String firstName, String lastName, String sex, String role) {
        return isValidEmail(email) && isValidName(firstName) && isValidName(lastName) && isValidSex(sex) && isValidRole(role);
    }

    public boolean isValidEditUser(String userId, String email, String firstName, String lastName, String sex) {
        return isValidId(userId) && isValidName(firstName) && isValidName(lastName) && isValidEmail(email) && isValidSex(sex);
    }

    public boolean isValidName(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    public boolean isValidSex(String sex) {
        return sex != null && sex.toUpperCase().matches(SEX_REGEX);
    }

    public boolean isValidRole(String role) {
        return role != null && role.toUpperCase().matches(ROLE_REGEX);
    }


}
