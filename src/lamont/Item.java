/**
 * The Item Interface. The acceptable item methods.
 *
 * @author Sean Lamont
 * @date 10/7/19
 */
package lamont;
// conflict between CheckStyle and Google Formatting.

/**
 * Create an interface called Item that will force all classes to implement the following
 * functions:.
 */
public interface Item {

  /**
   * A method getId that would return an int.
   *
   * @return int, will return Item ID.
   */
  int getID();

  /**
   * A method setName that would have one String parameter.
   *
   * @param name String, will set String of Item name.
   */
  void setName(String name);

  /**
   * A method getName that would return a String.
   *
   * @return String, will return String of Item name.
   */
  String getName();

  /**
   * A method setManufacturer that would have one String parameter.
   *
   * @param manufacturer String, will set Item manufacturer.
   */
  void setManufacturer(String manufacturer);

  /**
   * A method getManufacturer that would return a String.
   *
   * @return String, will return String of the Item manufacturer.
   */
  String getManufacturer();
}
