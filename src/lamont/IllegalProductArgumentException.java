package lamont;

/**
 * The Exception that is thrown by all Product setters if a parameter is found to be invalid by the
 * setter.
 *
 * @author Sean Lamont
 */
public class IllegalProductArgumentException extends Exception {
  public IllegalProductArgumentException() {
    super("Invalid argument for the Stock class method");
  }

  public IllegalProductArgumentException(String message) {
    super(message);
  }
}
