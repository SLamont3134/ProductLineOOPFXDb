/**
 * The ScreenDriver class was created simply to test the Screen class.
 *
 * @author Sean Lamont
 * @brief The ScreenDriver class was created simply to test the Screen class.
 * @date 10/8/19
 */
package lamont;
// conflict between google format and CheckStyle

/** Create a Driver class for Screen that tests the functionality of the screen class. */
public class ScreenDriver {

  /**
   * The main runnable class to test Screen class.
   *
   * @param args main arguments.
   */
  public static void main(String[] args) {

    Screen screen1 = new Screen("1960x1460", 120, 1);
    Screen screen2 = new Screen();
    System.out.println(screen1.getRefreshRate());
    System.out.println(screen1.getResolution());
    System.out.println(screen1.getResponseTime());
    System.out.println(screen1.toString());
    System.out.println(screen2.toString());
  }
}
