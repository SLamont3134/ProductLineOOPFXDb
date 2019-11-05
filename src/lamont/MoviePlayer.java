/**
 * The MoviePlayer class that creates screen object extending Product and implementing
 * MultimediaControl.
 *
 * @author Sean Lamont
 * @brief * The MoviePlayer class that creates screen object extending Product and implementing
 *     MultimediaControl.
 * @date 10/8/19
 */
package lamont;
// conflict between google formats and CheckStyle.

/** Create a class called MoviePlayer that extends Product and implements MultimediaControl. */
public class MoviePlayer extends Product implements MultimediaControl {

  // Add 2 fields to this class called screen and monitor type and assign appropriate types to them.
  private Screen screen;

  private MonitorType monitorType;

  /** Default empty Constructor. */
  public MoviePlayer() {
    super();
    this.screen = new Screen();
    this.monitorType = MonitorType.LED;
  }

  /**
   * Four argument constructor.
   *
   * @param name String, name of movie player.
   * @param manufacturer String, name of manufacturer.
   * @param screen Screen, type of screen in movie player.
   * @param monitorType MonitorType, type of monitor.
   */
  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.Visual);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  // Complete the methods from the MultimediaControl interface in a similar
  // fashion to the audio player.

  /** Play function. */
  @Override
  public void play() {
    System.out.println("Playing movie");
  }

  /** Stop function. */
  @Override
  public void stop() {
    System.out.println("Stopping movie");
  }

  /** Previous function. */
  @Override
  public void previous() {
    System.out.println("Previous movie");
  }

  /** Next function. */
  @Override
  public void next() {
    System.out.println("Next movie");
  }

  /**
   * Create a toString method that calls the product toString, displays the monitor and the screen
   * details.
   *
   * @return String, the description of movie player in String format.
   */
  public String toString() {
    return super.toString() + screen.toString() + "\nMonitor Type: " + monitorType.toString();
  }

  /**
   * toString method specically formatted for the product log window.
   *
   * @return String, formatted for product log window.
   */
  @Override
  public String productLogString() {
    return super.productLogString();
  }
}
