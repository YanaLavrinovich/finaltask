package by.etc.finaltask.logic.validation;

import java.sql.Date;

public class CourseValidatorImpl implements CourseValidator {
    private static final String NAME_REGEX = "^[\\w\\s'[А-я]]+$";
    private static final String DATE_REGEX = "^[1-9][\\d]{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    private static final String NUMBER_REGEX = "^[\\d]+$";

    /**
     * @see CourseValidator#isValidCourse(String, String, String, String)
     */
    public void isValidCourse(String name, String dateStart, String dateFinish, String userId) throws ValidationException {
        isValidName(name);
        isValidDate(dateStart);
        isValidDate(dateFinish);
        isValidUserId(userId);
    }

    /**
     * @see CourseValidator#isValidCourse(String, String, String)
     */
    public void isValidCourse(String name, String dateStart, String dateFinish) throws ValidationException {
        isValidName(name);
        isValidDate(dateStart);
        isValidDate(dateFinish);

    }

    /**
     * @see CourseValidator#isValidName(String)
     */
    public void isValidName(String name) throws ValidationException {
        if (name == null || !name.matches(NAME_REGEX)) {
            throw new ValidationException("The course's name is wrong. It is null or has incorrect data");
        }
    }

    /**
     * @see CourseValidator#isValidDate(String)
     */
    public void isValidDate(String date) throws ValidationException {
        if (date == null || !date.matches(DATE_REGEX)) {
            throw new ValidationException("The course's date is wrong. It is null or has incorrect data");
        }
    }

    /**
     * @see CourseValidator#isValidUserId(String)
     */
    public void isValidUserId(String userId) throws ValidationException {
        if (userId == null || !userId.matches(NUMBER_REGEX)) {
            throw new ValidationException("The user's id is wrong. It is null or has incorrect data");
        }
    }

    /**
     * @see UserValidator#isValidId(String)
     */
    public void isValidCourseId(String courseId) throws ValidationException {
        if (courseId == null || !courseId.matches(NUMBER_REGEX)) {
            throw new ValidationException("The course's id is wrong. It is null or has incorrect data");
        }
    }

    /**
     * @see UserValidator#isValidId(String)
     */
    public void isValidDateRange(String dateStart, String dateFinish) throws ValidationException {
        Date start = Date.valueOf(dateStart);
        Date finish = Date.valueOf(dateFinish);
        if (start.after(finish)) {
            throw new ValidationException("The course's range of date is wrong.");
        }
    }
}
