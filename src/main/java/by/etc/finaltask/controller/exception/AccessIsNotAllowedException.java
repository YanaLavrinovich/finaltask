package by.etc.finaltask.controller.exception;

public class AccessIsNotAllowedException extends Exception {
    private static final long serialVersionUID = -2105804762294629669L;

    public AccessIsNotAllowedException() {
    }

    public AccessIsNotAllowedException(String message) {
        super(message);
    }

    public AccessIsNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessIsNotAllowedException(Throwable cause) {
        super(cause);
    }
}
