package by.etc.finaltask.dao.exception;

public class DaoRollbackException extends Exception {
    private static final long serialVersionUID = 1039240580305730583L;

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
