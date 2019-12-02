package lamont;

public class IllegalProductArgumentException extends Exception {
  public IllegalProductArgumentException() {
    super("Invalid argument for the Stock class method");
  }

  public IllegalProductArgumentException(String message) {
    super(message);
  }
}
