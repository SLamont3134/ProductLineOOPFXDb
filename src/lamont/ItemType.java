package lamont;

public enum ItemType {
  Audio("AU"),
  Visual("VI"),
  AudioMobile("AM"),
  VisualMobile("VM");

  private final String code;

  ItemType(String code){
    this.code= code;
  }
}
