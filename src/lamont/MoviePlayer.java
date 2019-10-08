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

// Create a class called MoviePlayer that extends Product and implements MultimediaControl.
public class MoviePlayer extends Product implements MultimediaControl {

  // Add 2 fields to this class called screen and monitor type and assign appropriate types to them.
  private Screen screen;

  private MonitorType monitorType;

  public MoviePlayer() {
    super();
    this.screen = new Screen();
    this.monitorType = MonitorType.LED;
  }

  public MoviePlayer(
      int id,
      String name,
      String manufacturer,
      String type,
      Screen screen,
      MonitorType monitorType) {
    super(id, name, manufacturer, type);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  // Complete the methods from the MultimediaControl interface in a similar
  // fashion to the audio player.
  @Override
  public void play() {
    System.out.println("Playing movie");
  }

  @Override
  public void stop() {
    System.out.println("Stopping movie");
  }

  @Override
  public void previous() {
    System.out.println("Previous movie");
  }

  @Override
  public void next() {
    System.out.println("Next movie");
  }

  // Create a toString method that calls the product toString,
  // displays the monitor and the screen details.
  public String toString() {
    return super.toString() + screen.toString() + "\nMonitor Type: " + monitorType.toString();
  }
}
