package by.etc.finaltask.logic.exception;

public class CourseLogicException extends Exception {
    private static final long serialVersionUID = 4933490020393848404L;

    public CourseLogicException() {
    }

    public CourseLogicException(String message) {
        super(message);
    }

    public CourseLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseLogicException(Throwable cause) {
        super(cause);
    }
}
