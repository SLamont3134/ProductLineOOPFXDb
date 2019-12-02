/**
 * The Widget class was built as a test class for Product.
 *
 * @author Sean Lamont
 * @brief The Widget class was built as a test class for Product.
 * @date 10/8/19
 */
package lamont;
// conflict between CheckStyle and google format.

/**
 * Test Widget class to test Product.
 *
 * @author Sean Lamont
 */
public class Widget extends Product {

  /**
   * Constructor.
   *
   * @param name String, name of the Widget.
   * @param manufacturer String, the manufacturer of the Widget.
   * @param type ItemType, the specific enum for ItemType for this Widget.
   * @throws IllegalProductArgumentException thrown if parameter is invalid.
   */
  Widget(String name, String manufacturer, ItemType type) throws IllegalProductArgumentException {
    super(name, manufacturer, type);
  }

  /**
   * Basic toString().
   *
   * @return String, returns info for Widget to String.
   */
  public String toString() {
    return super.toString();
  }
}
