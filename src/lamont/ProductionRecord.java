/**
 * The Production Record class will align with items in the database.
 *
 * @author Sean Lamont
 * @brief The Production Record class will align with items in the database.
 * @date 10/15/19
 */
package lamont;


import java.util.Date;

// Create a  ProductionRecord class with
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

  // Make one constructor that just has a parameter for the productID. This will be
  // the constructor called when the user records production from the user interface.
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
    //setSerialNum();
  }
  // Create an overloaded constructor to use when creating ProductionRecord objects from
  // the database. This constructor needs parameters for all fields.
  ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    //this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
    this.count = 1;
    setSerialNum(serialNumber);
  }

  //Overload the ProductionRecord constructor to accept a Product and an int which holds the
  // count of the number of items of its type that have been created. (You can write the code
  // to generate the count later.)
  ProductionRecord(Product newProduct, int count, int serialNumber){
    this.product = newProduct;
    this.productID = newProduct.getID();
    this.productionNumber =0;
    this.dateProduced = new Date();
    this.count = count;
    setSerialNum(newProduct, serialNumber);
  }

  // Create accessors and mutators for all fields.
  public int getProductionNum() {
    return productionNumber;
  }

  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public int getProductID() {
    return productID;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public String getSerialNum() {
    return serialNumber;
  }

  public Product getProduct(){
    return product;
  }

  //Set the serialNumber to start with the first three letters of the Manufacturer name,
  // then the two letter ItemType code, then five digits (with leading 0s if necessary)
  // that are unique and sequential for the item type. The entire Serial Number should
  // be programmatically created and assigned.


  public void setSerialNum(Product newProduct, int count)
  { String tempString = "";
  String countString = Integer.toString(count);
  while(countString.length() < 5){
    countString = "0" + countString;
  }
  tempString = newProduct.getManufacturer().substring(0,3);
  tempString = tempString.concat(newProduct.getItemTypeCode().code);
  //need to change next line for itemtype
  tempString = tempString.concat(countString);
  this.serialNumber = tempString;
  }


  public void setSerialNum(String serialNumber){
    this.serialNumber = serialNumber;
  }

  public String getSerialNumber(){
    return serialNumber;
  }

  public void setCount(int count){
    this.count = count;
  }

  public int getCount(){
    return count;
  }

  public Date getProdDate() {
    return dateProduced;
  }

  public void setProdDate(Date dateProduced) {
    this.dateProduced = new Date(dateProduced.getTime());
  }

  // Override toString to return a string in the format "Production Num: 0 Product ID: 0
  // Serial Num: 0 Date: Mon Oct 14 10:29:48 UTC 2019"
  @Override
  public String toString() {
    return "Prod. Num: "
        + productionNumber
        + " Product ID: "
        + productID
        + " Serial Num: "
        + serialNumber
        + " Date: "
        + dateProduced + "\n";
  }

  /*
  public Product findProductMatch(ArrayList<MultimediaControl> list){
    Product tempProduct = new AudioPlayer();
    for (MultimediaControl product : list) {
      if (list instanceof Product) {
        if (this.productID == product.getID()) {
          tempProduct = product;
        }
      }
      }
    return tempProduct;
    }
   */


  }

