package by.etc.finaltask.dao.exception;

public class DaoException extends Exception {
    private static final long serialVersionUID = 2434930405803850322L;

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
