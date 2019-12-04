/**
 * The AudioPlayer class is used by the GUI to create Audio Players.
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
   * Sets the supported audio formats for the AudioPlayer Object.
   *
   * @param supportedAudioFormats String, supported types.
   * @throws IllegalProductArgumentException thrown if a parameter is invalid.
   */
  public void setSupportedAudioFormats(String supportedAudioFormats)
      throws IllegalProductArgumentException {
    if (supportedAudioFormats != null && supportedAudioFormats.length() > 0) {
      this.supportedAudioFormats = supportedAudioFormats;
    } else
      throw new IllegalProductArgumentException(
          "Invalid Formats " + supportedAudioFormats + " Must be at least one character.");
  }

  /**
   * Sets the supported playlist formats for the AudioPlayer Object.
   *
   * @param supportedPlaylistFormats String, supported types.
   * @throws IllegalProductArgumentException thrown if a parameter is invalid.
   */
  public void setSupportedPlaylistFormats(String supportedPlaylistFormats)
      throws IllegalProductArgumentException {
    if (supportedPlaylistFormats != null && supportedPlaylistFormats.length() > 0) {
      this.supportedPlaylistFormats = supportedPlaylistFormats;
    } else
      throw new IllegalProductArgumentException(
          "Invalid Formats " + supportedPlaylistFormats + " Must be at least one character.");
  }

  /**
   * Four argument Constructor.
   *
   * @param name String, name of AudioPlayer.
   * @param manufacturer String, name of manufacturer.
   * @param supportedAudioFormats String, all the supported audio formats.
   * @param supportedPlaylistFormats String, all of the supported playlist formats.
   * @throws IllegalProductArgumentException thrown if a parameter is invalid.
   */
  public AudioPlayer(
      String name,
      String manufacturer,
      String supportedAudioFormats,
      String supportedPlaylistFormats)
      throws IllegalProductArgumentException {
    super(name, manufacturer, ItemType.Audio);
    setSupportedAudioFormats(supportedAudioFormats);
    setSupportedPlaylistFormats(supportedPlaylistFormats);
  }

  /**
   * Five argument Constructor.
   *
   * @param id int, id of the AudioPlayer.
   * @param name String, name of AudioPlayer.
   * @param manufacturer String, name of manufacturer.
   * @param supportedAudioFormats String, all the supported audio formats.
   * @param supportedPlaylistFormats String, all of the supported playlist formats.
   * @throws IllegalProductArgumentException thrown if a parameter is invalid.
   */
  public AudioPlayer(
      int id,
      String name,
      String manufacturer,
      String supportedAudioFormats,
      String supportedPlaylistFormats)
      throws IllegalProductArgumentException {
    super(id, name, manufacturer, ItemType.Audio);
    setSupportedAudioFormats(supportedAudioFormats);
    setSupportedPlaylistFormats(supportedPlaylistFormats);
  }

  /**
   * Three argument Constructor. Sets Audio format and Playlist format to default settings.
   *
   * @param id int, id of the AudioPlayer.
   * @param name String, name of AudioPlayer.
   * @param manufacturer String, name of manufacturer.
   * @throws IllegalProductArgumentException thrown if a parameter is invalid.
   */
  public AudioPlayer(
      int id,
      String name,
      String manufacturer)
      throws IllegalProductArgumentException {
    super(id, name, manufacturer, ItemType.Audio);
    setSupportedAudioFormats("DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC");
    setSupportedPlaylistFormats("M3U/PLS/WPL");
  }

  /**
   * Default Constructor.
   *
   * @throws IllegalProductArgumentException thrown if a parameter is invalid.
   */
  public AudioPlayer() throws IllegalProductArgumentException {
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
        + "Supported Audio Formats: "
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
