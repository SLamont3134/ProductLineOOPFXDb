package edu.fgcu.eagle.stlamont3134;

/**
 * The MoviePlayerDriver class was created simply to test the MoviePlayer class.
 *
 * @author Sean Lamont
 */
public class MoviePlayerDriver {
  public static void main(String[] args) {
    testMoviePlayer();
  }

  /** Class to test MoviePlayer class. */
  public static void testMoviePlayer() {
    try {
      Screen newScreen = new Screen("720x480", 40, 22);
      MoviePlayer moviePlayer1 =
          new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);
      System.out.println(moviePlayer1);
      moviePlayer1.play();
      moviePlayer1.stop();
      moviePlayer1.next();
      moviePlayer1.previous();
    } catch (IllegalProductArgumentException e) {
      System.out.println(e);
    }
  }
}
