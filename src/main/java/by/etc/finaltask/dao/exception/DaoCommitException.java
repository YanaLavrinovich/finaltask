package by.etc.finaltask.dao.exception;

public class DaoCommitException extends Exception {
    private static final long serialVersionUID = 4395034594395340559L;

    public DaoCommitException() {
    }

    public DaoCommitException(String message) {
        super(message);
    }

    public DaoCommitException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoCommitException(Throwable cause) {
        super(cause);
    }
}
