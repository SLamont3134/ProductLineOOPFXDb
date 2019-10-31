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
    testMoviePlayer();
  }
  public static void testMoviePlayer(){
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer moviePlayer1 = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen,
        MonitorType.LCD);
    System.out.println(moviePlayer1);
    moviePlayer1.play();
    moviePlayer1.stop();
    moviePlayer1.next();
    moviePlayer1.previous();
  }

}
