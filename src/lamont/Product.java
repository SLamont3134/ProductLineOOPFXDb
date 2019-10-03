package lamont;

// Create an abstract type called Product that will implement the Item interface
public abstract class Product implements Item {

  // Product will implement the basic functionality that all items on a production line should have.
  // Add the following fields to Product

  //    int id
  private int id;
  //    String type
  private String type;
  //    String manufacturer
  private String manufacturer;
  //    String name
  private String name;

  //Complete the methods from the interface Item.
  //A method getId that would return an int
  public int getID(){
    return this.id;
  }

  //A method setName that would have one String parameter
  public void setName(String name){
    this.name = name;
  }

  //A method getName that would return a String
  public String getName(){
    return this.name;
  }
  //A method setManufacturer that would have one String parameter
  public void setManufacturer(String manufacturer){
    this.manufacturer = manufacturer;
  }

  //A method getManufacturer that would return a String
  public String getManufacturer(){
    return this.manufacturer;
  }

  //Add a constructor that will take in the name of the product and set this to the
  // field variable name.
  Product(){}

  Product(String name, String manufacturer, String type){
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  //Add a toString method that will return the following: (example data shown).
  public String toString(){
    return "Name: " + name +
        "\nManufacturer: " + manufacturer +
        "\nType: " + type;
  }


}
