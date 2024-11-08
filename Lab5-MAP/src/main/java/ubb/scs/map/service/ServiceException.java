package ubb.scs.map.service;

/**
 * ServiceException is a custom exception class that extends RuntimeException.
 * It is used to represent exceptions that occur at the service layer of the application.
 * This class provides multiple constructors to allow flexibility when throwing exceptions,
 * such as including an error message, the cause of the exception, or both.
 */
public class ServiceException extends RuntimeException {
    /**
     * Default constructor for ServiceException.
     * Creates a ServiceException with no message or cause.
     */
    public ServiceException() {}

    /**
     * Constructs a ServiceException with a specified error message.
     *
     * @param message The error message describing the exception.
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a ServiceException with a specified error message and cause.
     *
     * @param message The error message describing the exception.
     * @param cause The underlying cause of the exception (another throwable).
     */
    public ServiceException(String message, Throwable cause) {
      super(message, cause);
    }

    /**
     * Constructs a ServiceException with a specified cause.
     *
     * @param cause The underlying cause of the exception (another throwable).
     */
    public ServiceException(Throwable cause) {
      super(cause);
    }

    /**
     * Constructs a ServiceException with a specified message, cause,
     * and two additional options for suppression and stack trace behavior.
     *
     * @param message The error message describing the exception.
     * @param cause The underlying cause of the exception (another throwable).
     * @param enableSuppression Whether suppression is enabled or disabled.
     *                         (Suppression allows multiple exceptions to be suppressed for later reporting.)
     * @param writableStackTrace Whether the stack trace should be writable.
     */
    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
    }
}
