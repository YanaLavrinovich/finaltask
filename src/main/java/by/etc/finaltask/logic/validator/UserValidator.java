package by.etc.finaltask.logic.validator;

import by.etc.finaltask.bean.User;

public class UserValidator {
    private static final String PASSWORD_REGEX = "^[\\w\\d._]{4,12}$";
    private static final String EMAIL_REGEX = "^[\\w._\\d-]+@[A-Za-z]+.[A-Za-z]{2,3}$";
    private static final String NAME_REGEX = "^[\\w]+$";
    private static final String ROLE_REGEX = "^(ADMIN|TEACHER|STUDENT)$";

    public boolean isValidPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    public boolean isValidUserInfo(User user) {
        boolean result = isValidEmail(user.getEmail());
        result = isValidName(user.getFirstName());
        result = isValidName(user.getLastName());
        result = isValidRole(user.getRole().toString());
        return result;
    }

    public boolean isValidName(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    public boolean isValidRole(String role) {
        return role != null && role.matches(ROLE_REGEX);
    }
}
