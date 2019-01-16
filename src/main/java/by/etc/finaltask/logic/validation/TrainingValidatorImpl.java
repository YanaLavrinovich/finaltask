package by.etc.finaltask.logic.validation;

import java.sql.Date;

public class TrainingValidatorImpl implements TrainingValidator {
    private static final String NAME_REGEX = "^[\\w\\s' -]+$";
    private static final String DATE_REGEX = "^[1-9][\\d]{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    private static final String NUMBER_REGEX = "^[\\d]+$";
    private static final String MARK_REGEX = "^(\\d|10)$";
    private static final String STATUS_REGEX = "^(approved|completed|excluded|in process|leaved|rejected|requested)$";
    private static final String TEXT_REGEX = "[a-zA-Z][a-zA-Z '-.,]*";

    /**
     * @see TrainingValidator#isValidCourseInfo(String, String, String, String)
     */
    public void isValidCourseInfo(String name, String dateStart, String dateFinish, String userId) throws ValidationException {
        isValidName(name);
        isValidDate(dateStart);
        isValidDate(dateFinish);
        isValidUserId(userId);
    }

    /**
     * @see TrainingValidator#isValidCourseInfo(String, String, String)
     */
    public void isValidCourseInfo(String name, String dateStart, String dateFinish) throws ValidationException {
        isValidName(name);
        isValidDate(dateStart);
        isValidDate(dateFinish);
    }

    /**
     * @see TrainingValidator#isValidName(String)
     */
    public void isValidName(String name) throws ValidationException {
        if(name != null && name.matches(NAME_REGEX)) {
            throw new ValidationException("The course's name is wrong. It is null or has incorrect data");
        }
    }

    /**
     * @see TrainingValidator#isValidCourseStatus(String)
     */
    public void isValidCourseStatus(String courseStatus) throws ValidationException {
        if (courseStatus == null || !courseStatus.matches(STATUS_REGEX)) {
            throw new ValidationException("The course's status is wrong. It is null or has incorrect data");
        }
    }

    /**
     * @see TrainingValidator#isValidDate(String)
     */
    public void isValidDate(String date) throws ValidationException {
        if(date == null || !date.matches(DATE_REGEX)) {
            throw new ValidationException("The course's date is wrong. It is null or has incorrect data");
        }
    }

    /**
     * @see TrainingValidator#isValidUserId(String)
     */
    public void isValidUserId(String userId) throws ValidationException {
        if(userId == null || !userId.matches(NUMBER_REGEX)) {
            throw new ValidationException("The user's id is wrong. It is null or has incorrect data");
        }
    }

    /**
     * @see TrainingValidator#isValidDateRange(String, String)
     */
    public void isValidDateRange(String dateStart, String dateFinish) throws ValidationException {
        Date start = Date.valueOf(dateStart);
        Date finish = Date.valueOf(dateFinish);
        if(start.after(finish)) {
            throw new ValidationException("The course's range of date is wrong. It is null or has incorrect data");
        }
    }

    /**
     * @see TrainingValidator#isValidMark(String)
     */
    public void isValidMark(String mark) throws ValidationException {
        if(mark == null || !mark.matches(MARK_REGEX)) {
            throw new ValidationException("The training's mark is wrong. It is null or has incorrect data");
        }
    }

    @Override
    public void isValidComment(String comment) throws ValidationException {
        if(comment == null || !comment.matches(TEXT_REGEX)) {
            throw new ValidationException("The training's comment is wrong. It is null or has incorrect data");
        }
    }

}
