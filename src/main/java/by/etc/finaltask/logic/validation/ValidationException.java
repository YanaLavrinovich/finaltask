package by.etc.finaltask.logic.validation;

public class ValidationException extends Exception{
    private static final long serialVersionUID = 787617164731343845L;

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }
}
