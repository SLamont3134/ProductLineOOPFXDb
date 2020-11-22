package edu.fgcu.eagle.stlamont3134;

public class NullProduct extends Product{

  NullProduct(String name) throws IllegalProductArgumentException {
    super(name);
  }

  NullProduct() throws IllegalProductArgumentException{
    this("Null");
  }

}
