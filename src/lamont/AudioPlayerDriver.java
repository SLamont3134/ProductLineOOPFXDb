/**
 * The AudioPlayerDriver class was created simply to test the AudioPlayer class.
 *
 * @author Sean Lamont
 * @brief The AudioPlayerDriver class was created simply to test the AudioPlayer class.
 * @date 10/8/19
 */

package lamont;

public class AudioPlayerDriver {

  public static void main(String[] args) {
    testPlayer();
  }
  public static void testPlayer() {
    AudioPlayer player1 = new AudioPlayer("DP-X1A", "Onkyo",
        "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    System.out.println(player1);
    player1.play();
    player1.stop();
    player1.next();
    player1.previous();
  }

}
