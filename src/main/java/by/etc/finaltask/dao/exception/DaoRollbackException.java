package by.etc.finaltask.dao.exception;

public class DaoRollbackException extends Exception {
    public DaoRollbackException() {
    }

    public DaoRollbackException(String message) {
        super(message);
    }

    public DaoRollbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoRollbackException(Throwable cause) {
        super(cause);
    }
}
