package ubb.scs.map.faptebune.repository;

public class RepoException extends RuntimeException {

  /**
   * Constructs a ServiceException with a specified error message.
   *
   * @param message The error message describing the exception.
   */
    public RepoException(String message) {
        super(message);
    }

  /**
   * Constructs a ServiceException with a specified error message and cause.
   *
   * @param message The error message describing the exception.
   * @param cause The underlying cause of the exception (another throwable).
   */
  public RepoException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a ServiceException with a specified cause.
   *
   * @param cause The underlying cause of the exception (another throwable).
   */
  public RepoException(Throwable cause) {
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
  public RepoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
