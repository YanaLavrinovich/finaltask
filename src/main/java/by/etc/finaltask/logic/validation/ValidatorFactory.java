package by.etc.finaltask.logic.validation;

public class ValidatorFactory {
    private static ValidatorFactory instance = new ValidatorFactory();

    private UserValidator userValidator = new UserValidatorImpl();
    private CourseValidator courseValidator = new CourseValidatorImpl();
    private TrainingValidator trainingValidator = new TrainingValidatorImpl();

    public UserValidator getUserValidator() {
        return userValidator;
    }

    public CourseValidator getCourseValidator() {
        return courseValidator;
    }

    public TrainingValidator getTrainingValidator() {
        return trainingValidator;
    }

    public static ValidatorFactory getInstance() {
        return instance;
    }
}
