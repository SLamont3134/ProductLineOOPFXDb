package lamont;

public class AudioPlayer extends Product implements MultimediaControl {

  private String supportedAudioFormats;

  private String supportedPlaylistFormats;


  public AudioPlayer(
      //int id,
      String name,
      String manufacturer,
      String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(//id,
        name, manufacturer, ItemType.Audio);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }
  public AudioPlayer(){
      this("default", "default", "none", "none");
  }

  @Override
  public void play() {
    System.out.println("Playing");
  }

  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  @Override
  public void previous() {
    System.out.print("Previous");
  }

  @Override
  public void next() {
    System.out.println("Next");
  }

  public String toString() {
    return super.toString()
        + "\nSupported Audio Formats: "
        + supportedAudioFormats
        + "\nSupported Playlist Formats: "
        + supportedPlaylistFormats;
  }
  @Override
  public String productLogString(){
    return super.productLogString();
  }
}
