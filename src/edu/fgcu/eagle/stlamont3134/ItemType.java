package edu.fgcu.eagle.stlamont3134;

/**
 * The ItemType enum. The acceptable item types for the product
 *
 * @author Sean Lamont
 */
public enum ItemType {
  Audio("AU"),
  Visual("VI"),
  AudioMobile("AM"),
  VisualMobile("VM"),
  Null("NL");

  private final String code;

  ItemType(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
