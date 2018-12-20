package by.etc.finaltask.logic.validator;

public class ValidatorFactory {
    private static ValidatorFactory instance = new ValidatorFactory();

    private UserValidator userValidator = new UserValidator();
    private CourseValidator courseValidator = new CourseValidator();

    public UserValidator getUserValidator() {
        return userValidator;
    }

    public CourseValidator getCourseValidator() {
        return courseValidator;
    }

    public static ValidatorFactory getInstance() {
        return instance;
    }
}
