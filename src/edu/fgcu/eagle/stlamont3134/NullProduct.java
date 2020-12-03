package edu.fgcu.eagle.stlamont3134;

public class NullProduct extends Product{

  NullProduct(String name)  throws IllegalProductArgumentException {
    super(name);
    this.setType(ItemType.Null);
  }

  NullProduct() throws IllegalProductArgumentException{
    this("Null");
    this.setType(ItemType.Null);
  }

}
