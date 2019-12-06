package edu.fgcu.eagle.stlamont3134;

import java.util.Date;

/**
 * The Production Record class will align with items in the database. Used to keep track of
 * production runs of products produced.
 *
 * @author Sean Lamont
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

  private String employeeUsername;

  /**
   * Make one constructor that just has a parameter for the productID. This will be the constructor
   * called when the user records lamont.production from the user interface.
   *
   * @param productID int, the id of the product.
   * @throws IllegalProductArgumentException thrown if illegal parameter.
   */
  ProductionRecord(int productID) throws IllegalProductionRecordArgumentException {
    // In this constructor,
    setProductID(productID);
    // Set the productionNumber to 0 (because the database will end up auto-incrementing).
    setProductionNumber(0);
    // Set the serialNumber to "0" for now.
    setSerialNum(0);
    // Set the date to the current date using new Date().
    setDateProduced(new Date());
    setCount(1);
  }

  /**
   * Create an overloaded constructor to use when creating ProductionRecord objects from the
   * database. This constructor needs parameters for all fields.
   *
   * @param productionNumber int, the lamont.production number.
   * @param productID int, the product id.
   * @param serialNumber String, individual serial number.
   * @param dateProduced Date, the date produced.
   * @throws IllegalProductArgumentException thrown if illegal parameter.
   */
  ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced)
      throws IllegalProductionRecordArgumentException {
    setProductionNumber(productionNumber);
    setProductID(productID);
    // this.serialNumber = serialNumber;
    setDateProduced(dateProduced);
    setCount(1);
    setSerialNum(serialNumber);
  }

  /**
   * Overload the ProductionRecord constructor to accept a Product and an int which holds the count
   * of the number of items of its type that have been created. (You can write the code to generate
   * the count later.)
   *
   * @param newProduct Product, the product that has been produced.
   * @param count int, the quantity of products produced.
   * @throws IllegalProductArgumentException thrown if illegal parameter.
   */
  ProductionRecord(Product newProduct, int count) throws IllegalProductionRecordArgumentException {
    setProduct(newProduct);
    setProductID(newProduct.getId());
    setProductionNum(0);
    setDateProduced(new Date());
    setSerialNum(count);
  }

  /**
   * The constructor used by the Controller class to create a lamont.production record.
   *
   * @param newProduct Product, the product that has been produced.
   * @param count int, the quantity of products produced.
   * @param employeeUsername the username of the employee who created the lamont.production record.
   * @throws IllegalProductArgumentException thrown if illegal parameter.
   */
  ProductionRecord(Product newProduct, int count, String employeeUsername)
      throws IllegalProductionRecordArgumentException {
    setProduct(newProduct);
    setProductID(newProduct.getId());
    setProductionNum(1);
    setDateProduced(new Date());
    setSerialNum(count);
    setEmployeeUsername(employeeUsername);
  }

  // Create accessors and mutators for all fields.

  /**
   * Get lamont.production number.
   *
   * @return int, the lamont.production number.
   */
  public int getProductionNum() {
    return productionNumber;
  }

  /**
   * Sets the lamont.production number.
   *
   * @param productionNumber int the number to be set as the lamont.production number.
   * @throws IllegalProductionRecordArgumentException thrown if parameter is invalid.
   */
  public void setProductionNum(int productionNumber)
      throws IllegalProductionRecordArgumentException {
    if (productionNumber > 0) {
      this.productionNumber = productionNumber;
    } else {
      throw new IllegalProductionRecordArgumentException(
          "Illegal Production Number " + productionNumber + " Must be a number greater than 0.");
    }
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
   * @throws IllegalProductionRecordArgumentException thrown if a parameter is invalid.
   */
  public void setProductID(int productID) throws IllegalProductionRecordArgumentException {
    if (productID > 0) {
      this.productID = productID;
    } else {
      throw new IllegalProductionRecordArgumentException(
          "Illegal Product ID " + productID + " Must be a number greater than 0.");
    }
  }

  /**
   * Gets the product the lamont.production record is referring to.
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
   * @param count int, the number that is to be used in the Serial Number generation.
   * @throws IllegalProductionRecordArgumentException thrown if a parameter is invalid.
   */
  public void setSerialNum(int count) throws IllegalProductionRecordArgumentException {
    if (count > 0) {
      String tempString = "";
      String countString = Integer.toString(count);
      while (countString.length() < 5) {
        countString = "0" + countString;
      }
      if (this.getProduct().getManufacturer().length() > 3) {
        tempString = this.getProduct().getManufacturer().substring(0, 3);
      } else {
        tempString = this.getProduct().getManufacturer();
      }
      tempString = tempString.concat(this.getProduct().getItemTypeCode().getCode());
      // need to change next line for itemtype
      tempString = tempString.concat(countString);
      this.serialNumber = tempString;
    } else {
      throw new IllegalProductionRecordArgumentException(
          "Illegal Number for Serial Number " + count + " Must be a number greater than 0.");
    }
  }

  /**
   * Overloaded method to manually set serial number.
   *
   * @param serialNumber String, serial number to be manually set.
   * @throws IllegalProductionRecordArgumentException thrown if a parameter is invalid.
   */
  public void setSerialNum(String serialNumber) throws IllegalProductionRecordArgumentException {
    if (serialNumber != null && serialNumber.length() > 0) {
      this.serialNumber = serialNumber;
    } else {
      throw new IllegalProductionRecordArgumentException(
          "Invalid Serial Number " + serialNumber + " Must be at least one character.");
    }
  }

  /**
   * Sets the Product associated with the Production Record.
   *
   * @param product the product to be set.
   * @throws IllegalProductionRecordArgumentException thrown if a parameter is invalid.
   */
  public void setProduct(Product product) throws IllegalProductionRecordArgumentException {
    if (product != null) {
      this.product = product;
    } else {
      throw new IllegalProductionRecordArgumentException(
          "Invalid Product Must be of type Product.");
    }
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
   * @throws IllegalProductionRecordArgumentException thrown if a parameter is invalid.
   */
  public void setCount(int count) throws IllegalProductionRecordArgumentException {
    if (count > 0) {
      this.count = count;
    } else {
      throw new IllegalProductionRecordArgumentException(
          "Illegal Count " + count + " Must be a number greater than 0.");
    }
  }

  /**
   * Gets the count of items produced.
   *
   * @return int, the number of items produced in this lamont.production record.
   */
  public int getCount() {
    return count;
  }

  /**
   * Gets lamont.production date.
   *
   * @return Date, the date the lamont.production occurred on.
   */
  public Date getProdDate() {
    Date newDate = new Date(dateProduced.getTime());
    return newDate;
  }

  /**
   * Sets the date the products were produced.
   *
   * @param dateProduced Date, the date of lamont.production.
   * @throws IllegalProductionRecordArgumentException thrown if a parameter is invalid.
   */
  public void setProdDate(Date dateProduced) throws IllegalProductionRecordArgumentException {
    if (dateProduced != null) {
      this.dateProduced = new Date(dateProduced.getTime());
    } else {
      throw new IllegalProductionRecordArgumentException(
          "Illegal Production Date, must be a valid date");
    }
  }

  /**
   * Override toString to return a string in the format "Production Num:. 0 Product ID: 0 Serial
   * Num:. 0 Date: Mon Oct 14 10:29:48 UTC 2019"
   *
   * @return String, the lamont.production record description in String format.
   */
  public int getProductionNumber() {
    return productionNumber;
  }

  /**
   * Sets the lamont.production number of the lamont.production record.
   *
   * @param productionNumber int, unique id to identify the record.
   * @throws IllegalProductionRecordArgumentException thrown if a parameter is invalid.
   */
  public void setProductionNumber(int productionNumber)
      throws IllegalProductionRecordArgumentException {
    if (productionNumber > 0) {
      this.productionNumber = productionNumber;
    } else {
      throw new IllegalProductionRecordArgumentException(
          "Illegal Production Number " + productionNumber + " Must be a number greater than 0.");
    }
  }

  /**
   * Returns the ProductionRecord's dateProduced when called.
   *
   * @return Date, dateProduced.
   */
  public Date getDateProduced() {
    return new Date(dateProduced.getTime());
  }

  /**
   * Sets the ProductionRecord's dateProduced.
   *
   * @param dateProduced the Date() to be set.
   * @throws IllegalProductionRecordArgumentException thrown if invalid parameter is found.
   */
  public void setDateProduced(Date dateProduced) throws IllegalProductionRecordArgumentException {
    if (dateProduced != null) {
      this.dateProduced = new Date(dateProduced.getTime());
    } else {
      throw new IllegalProductionRecordArgumentException(
          "Illegal Production Date, must be a valid date");
    }
  }

  /**
   * Returns the ProductionRecord's employeeUsername.
   *
   * @return String, employeeUsername.
   */
  public String getEmployeeUsername() {
    return employeeUsername;
  }

  /**
   * Sets the ProductionRecord's employeeUsername to the value passed.
   *
   * @param employeeUsername String, the value to be set.
   * @throws IllegalProductionRecordArgumentException thrown if invalid parameter.
   */
  public void setEmployeeUsername(String employeeUsername)
      throws IllegalProductionRecordArgumentException {
    if (employeeUsername != null && employeeUsername.length() > 0) {
      this.employeeUsername = employeeUsername;
    } else {
      throw new IllegalProductionRecordArgumentException(
          "Invalid Name " + employeeUsername + " Must be at least one character.");
    }
  }

  /**
   * toString method to format to a readable list ProductionRecord's output.
   *
   * @return String, the formatted values of ProductionRecord.
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
