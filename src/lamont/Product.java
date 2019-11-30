/**
 * The Abstract Product class that establishes the framework for all of it's child classes .
 *
 * @author Sean Lamont
 * @brief The Abstract Product class that establishes the framework for all of it's child classes .
 * @date 10/8/19
 */
package lamont;
// conflict between CheckStyle and google format.

import java.util.Date;

/**
 * Create an abstract type called Product that will implement the Item interface Product will
 * implement the basic functionality that all items on a production line should have.
 */
public abstract class Product implements Item {

  // Product will implement the basic functionality that all items on a production line should have.
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
   * @param newID int, sets product ID to this value.
   */
  public void setId(int newID) {
    this.id = newID;
  }

  /**
   * A method setName that would have one String parameter.
   *
   * @param name String, the value fo this String will be set to product name.
   */
  public void setName(String name) {
    this.name = name;
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
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
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
   * Add a constructor that will take in the name of the product and set this to the field variable
   * name.
   *
   * @param name String, name of product.
   */
  Product(String name) {
    this.name = name;
  }

  /**
   * Three argument Constructor.
   *
   * @param name String, the name of the product.
   * @param manufacturer String, the name of the manufacturer.
   * @param type ItemType, the enum ItemType that represents product's type.
   */
  Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
    setManDate(new Date());
  }

  /**
   * Four argument Constructor.
   *
   * @param id int, id of the Product.
   * @param name String, the name of the product.
   * @param manufacturer String, the name of the manufacturer.
   * @param type ItemType, the enum ItemType that represents product's type.
   */
  Product(int id, String name, String manufacturer, ItemType type) {
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
    setManDate(new Date());
  }

  /** Empty constructor for defaults. */
  Product() {
    this.name = "Default";
    this.manufacturer = "None";
    this.type = ItemType.Audio;
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
