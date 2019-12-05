package lamont;

import java.util.Date;

/**
 * The Abstract Product class that establishes the basic functionality for all classes that
 * implement it.
 *
 * @author Sean Lamont
 * @brief The Abstract Product class that establishes the framework for all of it's child classes .
 * @date 10/8/19
 */
public abstract class Product implements Item {

  // Product will implement the basic functionality that all items on a lamont.production line
  // should have.
  // Add the following fields to Product

  //    int id
  private int id;
  //    String type
  private ItemType type;
  //    String manufacturer
  private String manufacturer;
  //    String name
  private String name;

  private Date manDate;

  // Complete the methods from the interface Item.

  /**
   * A method getId that would return an int Gets the product ID.
   *
   * @return int, product ID.
   */
  public int getId() {
    return this.id;
  }

  /**
   * Sets the product ID.
   *
   * @param newId int, sets product ID to this value.
   * @throws IllegalProductArgumentException thrown if a parameter is not valid.
   */
  public void setId(int newId) throws IllegalProductArgumentException {
    if (newId > 0) {
      this.id = newId;
    } else {
      throw new IllegalProductArgumentException(
          "Illegal ID " + newId + " Must be a number greater than 0.");
    }
  }

  /**
   * A method setName that would have one String parameter.
   *
   * @param name String, the value fo this String will be set to product name.
   * @throws IllegalProductArgumentException thrown if a parameter is not valid.
   */
  public void setName(String name) throws IllegalProductArgumentException {
    if (name != null && name.length() > 0) {
      this.name = name;
    } else {
      throw new IllegalProductArgumentException(
          "Invalid Name " + name + " Must be at least one character.");
    }
  }

  /**
   * A method getName that would return a String.
   *
   * @return String, returns the name of the product.
   */
  public String getName() {
    return this.name;
  }

  /**
   * A method setManufacturer that would have one String parameter.
   *
   * @param manufacturer String, sets the product manufacturer to this String.
   * @throws IllegalProductArgumentException thrown if a parameter is not valid.
   */
  public void setManufacturer(String manufacturer) throws IllegalProductArgumentException {
    if (manufacturer != null && manufacturer.length() > 0) {
      this.manufacturer = manufacturer;
    } else {
      throw new IllegalProductArgumentException(
          "Invalid Manufacturer " + manufacturer + " Must be at least one character.");
    }
  }

  /**
   * A method getManufacturer that would return a String.
   *
   * @return String, returns the product's manufacturer.
   */
  public String getManufacturer() {
    return this.manufacturer;
  }

  /**
   * Gets the product's manufactured date.
   *
   * @return Date the manufactured date.
   */
  public Date getManDate() {
    Date date = new Date(manDate.getTime());
    return date;
  }

  /**
   * Sets the manufactured date of the product.
   *
   * @param date Date(), the date the product was created.
   */
  public void setManDate(Date date) {
    this.manDate = new Date(date.getTime());
  }

  /**
   * Gets the product's item type enum.
   *
   * @return ItemType, the product's ItemType enum.
   */
  public ItemType getItemTypeCode() {
    return this.type;
  }

  /**
   * Gets the product's ItemType code toString().
   *
   * @return String, the products ItemType code toString().
   */
  public String getType() {
    return type.toString();
  }

  /**
   * Sets the ItemType of the Product.
   *
   * @param type ItemType Enum. the Product's type.
   * @throws IllegalProductArgumentException thrown if a parameter is not valid.
   */
  public void setType(ItemType type) throws IllegalProductArgumentException {
    if (type != null) {
      this.type = type;
    } else {
      throw new IllegalProductArgumentException(
          "Invalid Set, Type Must be of type ItemType and not null.");
    }
  }

  /**
   * Add a constructor that will take in the name of the product and set this to the field variable
   * name.
   *
   * @param name String, name of product.
   * @throws IllegalProductArgumentException thrown if a parameter is not valid.
   */
  Product(String name) throws IllegalProductArgumentException {
    setName(name);
  }

  /**
   * Three argument Constructor.
   *
   * @param name String, the name of the product.
   * @param manufacturer String, the name of the manufacturer.
   * @param type ItemType, the enum ItemType that represents product's type.
   * @throws IllegalProductArgumentException thrown if a parameter is not valid.
   */
  Product(String name, String manufacturer, ItemType type) throws IllegalProductArgumentException {
    setName(name);
    setManufacturer(manufacturer);
    setType(type);
    setManDate(new Date());
  }

  /**
   * Four argument Constructor.
   *
   * @param id int, id of the Product.
   * @param name String, the name of the product.
   * @param manufacturer String, the name of the manufacturer.
   * @param type ItemType, the enum ItemType that represents product's type.
   * @throws IllegalProductArgumentException thrown if a parameter is not valid.
   */
  Product(int id, String name, String manufacturer, ItemType type)
      throws IllegalProductArgumentException {
    setId(id);
    setName(name);
    setManufacturer(manufacturer);
    setType(type);
    setManDate(new Date());
  }

  /**
   * Empty constructor for defaults.
   *
   * @throws IllegalProductArgumentException thrown if a parameter is not valid.
   */
  Product() throws IllegalProductArgumentException {
    setName("Default");
    setManufacturer("None");
    setType(ItemType.Audio);
  }

  /**
   * Add a toString method that will return the following: (example data shown).
   *
   * @return String, description of product in column view.
   */
  public String toString() {
    return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type + "\n";
  }

  /**
   * Second toString method specifically made for product log window formatting.
   *
   * @return String, row view for product log window.
   */
  public String productLogString() {
    return "Name: " + name + " Manufacturer: " + manufacturer + " Type: " + type + "\n";
  }
}
