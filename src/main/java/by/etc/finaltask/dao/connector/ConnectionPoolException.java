package by.etc.finaltask.dao.connector;

public class ConnectionPoolException extends RuntimeException {
    private static final long serialVersionUID = 5875783830957385646L;

    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
