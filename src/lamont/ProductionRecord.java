/**
 * The Production Record class will align with items in the database.
 *
 * @author Sean Lamont
 * @brief The Production Record class will align with items in the database.
 * @date 10/15/19
 */
package lamont;
//conflict between google format and CheckStyle.

import java.util.Date;

/**
 * Create a ProductionRecord class with int fields for productionNumber (this will be unique for
 * every item produced and get auto incremented by the database), an int field for productID (to
 * correspond to the productID from the Product table / class), String field for serialNumber, and a
 * field for the dateProduced that is type Date(from java.util).
 */
public class ProductionRecord {

  // int fields for productionNumber
  // (this will be unique for every item produced and get auto incremented by the database),
  private int productionNumber;

  // an int field for productID (to correspond to the productID from the Product table / class),
  private int productID;

  // String field for serialNumber,
  private String serialNumber;

  // and a field for the dateProduced that is type Date(from java.util).
  private Date dateProduced;

  private int count;

  private Product product;

  /**
   * Make one constructor that just has a parameter for the productID. This will be the constructor
   * called when the user records production from the user interface.
   *
   * @param productID int, the id of the product.
   */
  ProductionRecord(int productID) {
    // In this constructor,
    this.productID = productID;
    // Set the productionNumber to 0 (because the database will end up auto-incrementing).
    this.productionNumber = 0;
    // Set the serialNumber to "0" for now.
    this.serialNumber = "0";
    // Set the date to the current date using new Date().
    this.dateProduced = new Date();
    this.count = 1;
    // setSerialNum();
  }
  //
  //

  /**
   * Create an overloaded constructor to use when creating ProductionRecord objects from the
   * database. This constructor needs parameters for all fields.
   *
   * @param productionNumber int, the production number.
   * @param productID int, the product id.
   * @param serialNumber String, individual serial number.
   * @param dateProduced Date, the date produced.
   */
  ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    // this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
    this.count = 1;
    setSerialNum(serialNumber);
  }

  /**
   * Overload the ProductionRecord constructor to accept a Product and an int which holds the count
   * of the number of items of its type that have been created. (You can write the code to generate
   * the count later.)
   *
   * @param newProduct Product, the product that has been produced.
   * @param count int, the quantity of products produced.
   */
  ProductionRecord(Product newProduct, int count) {
    this.product = newProduct;
    this.productID = newProduct.getId();
    this.productionNumber = 0;
    this.dateProduced = new Date();
    this.count = count;
  }

  // Create accessors and mutators for all fields.

  /**
   * Get production number.
   *
   * @return int, the production number.
   */
  public int getProductionNum() {
    return productionNumber;
  }

  /**
   * Sets the production number.
   *
   * @param productionNumber int the number to be set as the production number.
   */
  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  /**
   * Gets the product id.
   *
   * @return int, product ID.
   */
  public int getProductID() {
    return productID;
  }

  /**
   * Sets product ID to parameter passed.
   *
   * @param productID int, sets the product ID to this value.
   */
  public void setProductID(int productID) {
    this.productID = productID;
  }

  /**
   * Gets the product the production record is referring to.
   *
   * @return Product, returns the actual product the record is of.
   */
  public Product getProduct() {
    return product;
  }

  /**
   * Set the serialNumber to start with the first three letters of the Manufacturer name, then the
   * two letter ItemType code, then five digits (with leading 0s if necessary) that are unique and
   * sequential for the item type. The entire Serial Number should be programmatically created and
   * assigned.
   *
   * @param newProduct Product, the product that is to have it's serial number set.
   * @param count int, the int being incremented by the controller to be added to the serial number.
   */
  public void setSerialNum(Product newProduct, int count) {
    String tempString = "";
    String countString = Integer.toString(count);
    while (countString.length() < 5) {
      countString = "0" + countString;
    }
    tempString = newProduct.getManufacturer().substring(0, 3);
    tempString = tempString.concat(newProduct.getItemTypeCode().code);
    // need to change next line for itemtype
    tempString = tempString.concat(countString);
    this.serialNumber = tempString;
  }

  /**
   * Overloaded method to manually set serial number.
   *
   * @param serialNumber String, serial number to be manually set.
   */
  public void setSerialNum(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  /**
   * Gets serial number.
   *
   * @return String, the product's serial number.
   */
  public String getSerialNumber() {
    return serialNumber;
  }

  /**
   * Sets the quantity of products being produced.
   *
   * @param count int, the amount of items produced.
   */
  public void setCount(int count) {
    this.count = count;
  }

  /**
   * Gets the count of items produced.
   *
   * @return int, the number of items produced in this production record.
   */
  public int getCount() {
    return count;
  }

  /**
   * Gets production date.
   *
   * @return Date, the date the production occurred on.
   */
  public Date getProdDate() {
    Date newDate = new Date(dateProduced.getTime());
    return newDate;
  }

  /**
   * Sets the date the products were produced.
   *
   * @param dateProduced Date, the date of production.
   */
  public void setProdDate(Date dateProduced) {
    this.dateProduced = new Date(dateProduced.getTime());
  }

  /**
   * Override toString to return a string in the format "Production Num:. 0 Product ID: 0 Serial
   * Num:. 0 Date: Mon Oct 14 10:29:48 UTC 2019"
   *
   * @return String, the production record description in String format.
   */
  @Override
  public String toString() {
    return "Prod. Num: "
        + productionNumber
        + " Product ID: "
        + productID
        + " Serial Num: "
        + serialNumber
        + " Date: "
        + dateProduced
        + " Quantity: "
        + count
        + "\n";
  }


}
