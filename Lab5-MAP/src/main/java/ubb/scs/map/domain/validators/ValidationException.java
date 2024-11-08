package ubb.scs.map.domain.validators;

/**
 * The ValidationException class represents an exception that is thrown when
 * a validation error occurs within the application.
 * It extends the RuntimeException, indicating that it is an unchecked exception.
 */
public class ValidationException extends RuntimeException {

    /**
     * Default constructor for ValidationException.
     * Initializes a new instance of ValidationException without a message or cause.
     */
    public ValidationException() {
    }

    /**
     * Constructs a new ValidationException with the specified detail message.
     *
     * @param message The detail message that provides more information about the exception.
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Constructs a new ValidationException with the specified detail message and cause.
     *
     * @param message The detail message that provides more information about the exception.
     * @param cause The cause of the exception, which can be used to retrieve additional information.
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new ValidationException with the specified cause.
     *
     * @param cause The cause of the exception, which can be used to retrieve additional information.
     */
    public ValidationException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new ValidationException with the specified detail message, cause,
     * suppression enabled or disabled, and writable stack trace enabled or disabled.
     *
     * @param message The detail message that provides more information about the exception.
     * @param cause The cause of the exception, which can be used to retrieve additional information.
     * @param enableSuppression Whether or not suppression is enabled or disabled.
     * @param writableStackTrace Whether or not the stack trace should be writable.
     */
    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
