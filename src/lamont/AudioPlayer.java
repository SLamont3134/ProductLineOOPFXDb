/**
 * The AudioPlayer class creates AudioPlayers.
 *
 * @author Sean Lamont
 * @brief The AudioPlayer class creates Audio Players.
 * @date 10/8/19
 */
package lamont;
// Conflict between Google formatting and CheckStyle.

/** Audio Player extends Product implements MultimediaControl. */
public class AudioPlayer extends Product implements MultimediaControl {

  private String supportedAudioFormats;

  private String supportedPlaylistFormats;

  /**
   * Four argument Constructor.
   *
   * @param name String, name of AudioPlayer.
   * @param manufacturer String, name of manufacturer.
   * @param supportedAudioFormats String, all the supported audio formats.
   * @param supportedPlaylistFormats String, all of the supported playlist formats.
   */
  public AudioPlayer(
      String name,
      String manufacturer,
      String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(name, manufacturer, ItemType.Audio);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  /** Default Constructor. */
  public AudioPlayer() {
    this("default", "default", "none", "none");
  }

  /** Overridden Play method. */
  @Override
  public void play() {
    System.out.println("Playing");
  }

  /** Overridden Stop method. */
  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  /** Overridden Previous method. */
  @Override
  public void previous() {
    System.out.print("Previous");
  }

  /** Overriden Next method. */
  @Override
  public void next() {
    System.out.println("Next");
  }

  /**
   * To String method that returns a description of the AudioPlayer in column format.
   *
   * @return String, description of AudioPlayer.
   */
  public String toString() {
    return super.toString()
        + "\nSupported Audio Formats: "
        + supportedAudioFormats
        + "\nSupported Playlist Formats: "
        + supportedPlaylistFormats;
  }

  /**
   * To String specifically formatted for product Log window.
   *
   * @return String, formatted for rows description.
   */
  @Override
  public String productLogString() {
    return super.productLogString();
  }
}
