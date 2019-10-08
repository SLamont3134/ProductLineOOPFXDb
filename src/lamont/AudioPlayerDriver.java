/**
 * The AudioPlayerDriver class was created simply to test the AudioPlayer class.
 *
 * @author Sean Lamont
 * @brief The Abstract Product class that establishes the framework for all of it's child classes .
 * @date 10/8/19
 */
package lamont;

public class AudioPlayerDriver {

  public static void main(String[] args) {
    AudioPlayer player1 = new AudioPlayer("Zune", "Microsoft", "MP3", "Dance Party");
    player1.play();
    player1.stop();
    player1.next();
    player1.previous();
    System.out.println(player1.toString());
  }
}
