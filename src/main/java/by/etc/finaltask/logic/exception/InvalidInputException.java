package by.etc.finaltask.logic.exception;

public class InvalidInputException extends Exception {
    private static final long serialVersionUID = 6049583048093409580L;

    public InvalidInputException() {
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputException(Throwable cause) {
        super(cause);
    }
}
