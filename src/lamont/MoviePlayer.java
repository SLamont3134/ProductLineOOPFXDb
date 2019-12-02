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

  /**
   * Default empty Constructor.
   *
   * @throws IllegalProductArgumentException thrown if parameter is invalid.
   */
  public MoviePlayer() throws IllegalProductArgumentException {
    super();
    setScreen(new Screen());
    setMonitorType(MonitorType.LED);
  }

  /**
   * Four argument constructor.
   *
   * @param name String, name of movie player.
   * @param manufacturer String, name of manufacturer.
   * @param screen Screen, type of screen in movie player.
   * @param monitorType MonitorType, type of monitor.
   * @throws IllegalProductArgumentException thrown if parameter is invalid.
   */
  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType)
      throws IllegalProductArgumentException {
    super(name, manufacturer, ItemType.Visual);
    setScreen(screen);
    setMonitorType(monitorType);
  }

  /**
   * Five argument constructor.
   *
   * @param id int, the id for the MoviePlayer.
   * @param name String, name of movie player.
   * @param manufacturer String, name of manufacturer.
   * @param screen Screen, type of screen in movie player.
   * @param monitorType MonitorType, type of monitor.
   * @throws IllegalProductArgumentException thrown if parameter is invalid.
   */
  public MoviePlayer(
      int id, String name, String manufacturer, Screen screen, MonitorType monitorType)
      throws IllegalProductArgumentException {
    super(id, name, manufacturer, ItemType.Visual);
    setScreen(screen);
    setMonitorType(monitorType);
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
   * Returns the Product's Screen.
   *
   * @return Screen, returns a Screen object.
   */
  public Screen getScreen() {
    return screen;
  }

  /**
   * Sets the MoviePlayer's screen to the parameter.
   *
   * @param screen the Product's screen to be set.
   * @throws IllegalProductArgumentException thrown if parameter is invalid.
   */
  public void setScreen(Screen screen) throws IllegalProductArgumentException {
    if (screen instanceof Screen && screen != null) {
      this.screen = screen;
    } else
      throw new IllegalProductArgumentException(
          "Invalid Screen " + screen + " Must be of type Screen.");
  }

  /**
   * Returns the Product's monitorType.
   *
   * @return MonitorType.
   */
  public MonitorType getMonitorType() {
    return monitorType;
  }

  /**
   * Sets the monitor type of the MoviePlayer
   *
   * @param monitorType MonitorType the monitor type to be set.
   * @throws IllegalProductArgumentException thrown if parameter is invalid.
   */
  public void setMonitorType(MonitorType monitorType) throws IllegalProductArgumentException {
    if (monitorType instanceof MonitorType && monitorType != null) {
      this.monitorType = monitorType;
    } else
      throw new IllegalProductArgumentException(
          "Invalid Monitor " + monitorType + " Must be a MonitorType");
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
