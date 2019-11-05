/**
 * The AudioPlayerDriver class was created simply to test the AudioPlayer class.
 *
 * @author Sean Lamont
 * @brief The AudioPlayerDriver class was created simply to test the AudioPlayer class.
 * @date 10/8/19
 */
package lamont;
// Conflict between Google Format and CheckStyle.

/** Class to test AudioPlayer class. */
public class AudioPlayerDriver {

  /**
   * Runnable class to test AudioPlayer class.
   *
   * @param args main class.
   */
  public static void main(String[] args) {
    testPlayer();
  }

  /** Tests the AudioPlayer class. */
  public static void testPlayer() {
    AudioPlayer player1 =
        new AudioPlayer(
            "DP-X1A", "Onkyo", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    System.out.println(player1);
    player1.play();
    player1.stop();
    player1.next();
    player1.previous();
  }
}
