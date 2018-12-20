package by.etc.finaltask.logic.exception;

public class CourseLogicException extends Exception {
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
