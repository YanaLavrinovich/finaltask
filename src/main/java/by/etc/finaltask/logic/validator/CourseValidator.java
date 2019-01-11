package by.etc.finaltask.logic.validator;

import java.sql.Date;

public class CourseValidator {
    private static final String NAME_REGEX = "^[\\w\\s'[А-я]]+$";
    private static final String DATE_REGEX = "^[1-9][\\d]{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    private static final String NUMBER_REGEX = "^[\\d]+$";
    private static final String MARK_REGEX = "^(\\d|10)$";

    public boolean isValidCourse(String name, String dateStart, String dateFinish, String userId) {
        boolean result = isValidName(name) &&
                isValidDate(dateStart) &&
                isValidDate(dateFinish) &&
                isValidUserId(userId);
        return result;
    }

    public boolean isValidCourse(String name, String dateStart, String dateFinish) {
        boolean result = isValidName(name) &&
                isValidDate(dateStart) &&
                isValidDate(dateFinish);
        return result;
    }

    public boolean isValidName(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    public boolean isValidDate(String date) {
        return date != null && date.matches(DATE_REGEX);
    }

    public boolean isValidUserId(String userId) {
        return userId != null && userId.matches(NUMBER_REGEX);
    }

    public boolean isValidCourseId(String courseId) {
        return courseId != null && courseId.matches(NUMBER_REGEX);
    }

    public boolean isValidMark(String mark) {
        return mark != null && mark.matches(MARK_REGEX);
    }

    public boolean isValidDateRange(String dateStart, String dateFinish) {
        Date start = Date.valueOf(dateStart);
        Date finish = Date.valueOf(dateFinish);
        return start.before(finish);
    }

}
