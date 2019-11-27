/**
 * The ItemType enum. The acceptable item types for the product
 *
 * @author Sean Lamont
 * @date 10/7/19
 */
package lamont;
// conflict between CheckStyle and google format.

/** Create an enum called ItemType that will store the following information:. */
public enum ItemType {
  Audio("AU"),
  Visual("VI"),
  AudioMobile("AM"),
  VisualMobile("VM");

  private final String code;

  ItemType(String code) {
    this.code = code;
  }

  public String getCode(){
    return code;
  }
}
