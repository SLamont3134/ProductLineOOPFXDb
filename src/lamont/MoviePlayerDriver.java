/**
 * The MoviePlayerDriver class was created simply to test the MoviePlayer class.
 *
 * @author Sean Lamont
 * @brief The MoviePlayerDriver class was created simply to test the MoviePlayer class.
 * @date 10/8/19
 */
package lamont;

// Create a diver class to test the functionality of the movie player.
public class MoviePlayerDriver {
  public static void main(String[] args) {
    Screen screen1 = new Screen("720x480", 40, 22);
    MoviePlayer moviePlayer1 =
        new MoviePlayer(
            1, "DBPOWER MK101", "Oracle Production", "VisualMobile", screen1, MonitorType.LCD);
    System.out.println(moviePlayer1);

    Screen screen2 = new Screen("1366x768", 40, 22);
    MoviePlayer moviePlayer2 =
        new MoviePlayer(
            2, "Pyle PDV156BK", "Oracle Production", "VisualMobile", screen2, MonitorType.LED);
    System.out.println(moviePlayer2);
  }
}
