package by.etc.finaltask.logic.exception;

public class UserLogicException extends Exception {
    public UserLogicException() {
    }

    public UserLogicException(String message) {
        super(message);
    }

    public UserLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserLogicException(Throwable cause) {
        super(cause);
    }
}
