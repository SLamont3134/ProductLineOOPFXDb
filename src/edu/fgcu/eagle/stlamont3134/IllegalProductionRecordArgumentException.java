package edu.fgcu.eagle.stlamont3134;

/**
 * The Exception that is thrown by all Production Record setters if a parameter is found to be
 * invalid by the setter.
 *
 * @author Sean Lamont
 */
public class IllegalProductionRecordArgumentException extends Exception {

  public IllegalProductionRecordArgumentException() {
    super("Invalid argument for the Stock class method");
  }

  public IllegalProductionRecordArgumentException(String message) {
    super(message);
  }
}
