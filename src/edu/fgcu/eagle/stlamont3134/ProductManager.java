package edu.fgcu.eagle.stlamont3134;

public class ProductManager {

    public ProductManager() {
    }

    public Product createProduct(int id, String name, String manufacturer, String type)
            throws IllegalProductArgumentException {

        switch (type) {
            case "AU":
                try {
                    AudioPlayer tempObject1 = new AudioPlayer(id, name, manufacturer);
                    return tempObject1;
                } catch (IllegalProductArgumentException e) {
                    System.out.println(e);
                }
                break;
            case "VI":
                try {
                    MoviePlayer tempObject2 = new MoviePlayer(id, name, manufacturer);
                    return tempObject2;
                } catch (IllegalProductArgumentException e) {
                    System.out.println(e);
                }
                break;
            case "AM":
                System.out.println("Feature Coming Soon");
                break;
            case "VM":
                System.out.println("Feature Coming Soonish");
                break;
            default:
                break;
        }
        return new NullProduct();
    }
}

