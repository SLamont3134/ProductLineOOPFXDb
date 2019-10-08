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
    AudioPlayer player1 =
        new AudioPlayer(2, "Pyle PDV156BK", "Oracle Production", "MP3", "Dance Party");
    System.out.println(player1.toString());
  }
}
