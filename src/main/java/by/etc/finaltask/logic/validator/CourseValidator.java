package by.etc.finaltask.logic.validator;

public class CourseValidator {
    private static final String NAME_REGEX = "^[\\w\\s]+$";
    private static final String DATE_REGEX = "^[1-9][\\d]{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    private static final String USER_ID_REGEX = "^\\d+$";

    public boolean isValidCourse(String name, String dateStart, String dateFinish, int userId) {
        boolean result = isValidName(name) &&
                isValidDate(dateStart) &&
                isValidDate(dateFinish) &&
                isValidUserId(userId);
        return result;
    }

    public boolean isValidName(String name) {
        return name.matches(NAME_REGEX);
    }

    public boolean isValidDate(String date) {
        return date.matches(DATE_REGEX);
    }

    public boolean isValidUserId(int userId) {
        String userIdString = String.valueOf(userId);
        return userIdString.matches(USER_ID_REGEX);
    }
}
