/**
 * The Screen class that creates screen object implementing ScreenSpec.
 *
 * @author Sean Lamont
 * @brief The Screen class that creates screen object implementing ScreenSpec..
 * @date 10/8/19
 */
package lamont;
// conflict between CheckStyle and google format.

/**
 * Create a class called Screen that implements ScreenSpec. Add three fields String resolution int
 * refreshrate int responsetime
 */
public class Screen implements ScreenSpec {

  // Add three fields
  // String resolution
  private String resolution;

  // int refreshrate
  private int refreshrate;

  // int responsetime
  private int responseTime;

  /** Default Constructor. */
  public Screen() {
    this.resolution = "760x580";
    this.refreshrate = 60;
    this.responseTime = 20;
  }

  /**
   * Overloaded Constructor.
   *
   * @param resolution String, resolution of screen. ie 760x480.
   * @param refreshrate int, refresh rate f screen in Hz.
   * @param responseTime int, response time in ms.
   */
  public Screen(String resolution, int refreshrate, int responseTime) {
    this.resolution = resolution;
    this.refreshrate = refreshrate;
    this.responseTime = responseTime;
  }

  // Complete the methods from the ScreenSpec interface.
  @Override
  public String getResolution() {
    return resolution;
  }

  @Override
  public int getRefreshRate() {
    return refreshrate;
  }

  @Override
  public int getResponseTime() {
    return responseTime;
  }

  //
  //

  /**
   * Add a toString method that will return the details of the 3 field in the. same format as the
   * Product Class.
   *
   * @return String, Screen description in String format.
   */
  public String toString() {
    return "Screen:\nResolution: "
        + resolution
        + "\nRefresh rate: "
        + refreshrate
        + "\nResponse time: "
        + responseTime;
  }
}