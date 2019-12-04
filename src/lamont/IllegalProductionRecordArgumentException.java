package lamont;

public class IllegalProductionRecordArgumentException extends Exception {

  public IllegalProductionRecordArgumentException() {
    super("Invalid argument for the Stock class method");
  }

  public IllegalProductionRecordArgumentException(String message) {
    super(message);
  }
}
