package lamont;

/**
 * The Item Interface. The acceptable item methods.
 *
 * @author Sean Lamont
 * @date 10/7/19
 */
public interface Item {

  /**
   * A method getId that would return an int.
   *
   * @return int, will return Item ID.
   */
  int getId();

  /**
   * A method setName that would have one String parameter.
   *
   * @param name String, will set String of Item name.
   * @throws IllegalProductArgumentException thrown if a parameter is invalid.
   */
  void setName(String name) throws IllegalProductArgumentException;

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
   * @throws IllegalProductArgumentException thrown if a parameter is invalid.
   */
  void setManufacturer(String manufacturer) throws IllegalProductArgumentException;

  /**
   * A method getManufacturer that would return a String.
   *
   * @return String, will return String of the Item manufacturer.
   */
  String getManufacturer();
}
