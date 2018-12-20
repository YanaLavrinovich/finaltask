package by.etc.finaltask.logic.exception;

public class UserLogicException extends Exception {
    private static final long serialVersionUID = 4398543950938430840L;

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
