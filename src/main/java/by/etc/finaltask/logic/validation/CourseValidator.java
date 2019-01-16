package by.etc.finaltask.logic.validation;

/**
 * Validates course form input. Throws {@link ValidationException} with the error message if validation fails.
 */
public interface CourseValidator {

    /**
     * Validates input course's info for validity.
     *
     * @param name       course's name
     * @param dateStart  course's date start
     * @param dateFinish course's date finish
     * @param userId     user's id
     * @throws ValidationException if course's info is incorrect
     */
    void isValidCourse(String name, String dateStart, String dateFinish, String userId) throws ValidationException;

    /**
     * Validates input course's info for validity.
     *
     * @param name       course's name
     * @param dateStart  course's date start
     * @param dateFinish course's date finish
     * @throws ValidationException if course's info is incorrect
     */
    void isValidCourse(String name, String dateStart, String dateFinish) throws ValidationException;

    /**
     * Validates input course's name for validity.
     *
     * @param name course's name
     * @throws ValidationException if course's name is incorrect
     */
    void isValidName(String name) throws ValidationException;

    /**
     * Validates input course's date for validity.
     *
     * @param date course's name
     * @throws ValidationException if course's date is incorrect
     */
    void isValidDate(String date) throws ValidationException;

    /**
     * Validates input user's id for validity.
     *
     * @param userId user's id
     * @throws ValidationException if user's id is incorrect
     */
    void isValidUserId(String userId) throws ValidationException;

    /**
     * Validates input course's id for validity.
     *
     * @param courseId course's id
     * @throws ValidationException if course's id is incorrect
     */
    void isValidCourseId(String courseId) throws ValidationException;

    /**
     * Validates input course's date range for validity.
     *
     * @param dateStart  course's date start
     * @param dateFinish course's date finish
     * @throws ValidationException if date finish is before date start
     */
    void isValidDateRange(String dateStart, String dateFinish) throws ValidationException;

    void isValidDescription(String description) throws ValidationException;
}
