package lamont;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseManager {

  static final String JDBC_DRIVER = "org.h2.Driver";
  static final String DB_URL = "jdbc:h2:./rsc/ProductDB";
  private  String USER = new String("");
  private  String PASS = new String("");
  private Connection conn = null;
  private Statement statement;
  private int audioCount;
  private int visualCount;
  private String tempSerial;

  DatabaseManager() throws SQLException, FileNotFoundException, IOException {}

  /**
   * Establishes connection to database.
   *
   * @author Sean Lamont
   */
  public void connectToDB() {

    try{
      Properties prop = new Properties();
      prop.load(new FileInputStream("rsc/properties"));
      PASS = prop.getProperty("password");
    } catch (IOException e) {
      System.out.println(e);
    }

    try {
      Class.forName(JDBC_DRIVER);

      /*For this following line of code I receive a empty database password flag on FindBugs
       * this issue will be addressed in later versions if a password is to be implemented
       */
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = conn.createStatement();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println("Error Connecting to DB");

    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println("Error Connecting to DB");
    }
  }

  public boolean checkDBForProductionRecordName(ProductionRecord productionRecord){
    connectToDB();
    ResultSet rs = null;
    int count =1;
    try{
      Statement stmt = conn.createStatement();
      rs =stmt.executeQuery("SELECT COUNT(SERIAL_NUM) FROM PRODUCTIONRECORD WHERE SERIAL_NUM = \'" + productionRecord.getSerialNumber() + "\' ;");
    while(rs.next())
      count = rs.getInt(1);

    } catch (SQLException e) {
      System.out.println("Couldn't Check database for production record");
    }
    if (count > 0){
      return true;
    }
    else{
      return false;
    }
  }

  public void insertProductIntoDB(String[] insertValues) throws SQLException {
    connectToDB();
    String insertQuery =
        "INSERT INTO PRODUCT " + "(NAME, TYPE, MANUFACTURER)" + " VALUES (?, ?, ?)";
    PreparedStatement pstmt = conn.prepareStatement(insertQuery);
    pstmt.setString(1, insertValues[0]);
    pstmt.setString(2, insertValues[1]);
    pstmt.setString(3, insertValues[2]);
    pstmt.executeUpdate();
    disconnectFromDB();
  }

  public int selectProductIDFromDB(Product product) {
    int id = 0;
    connectToDB();
    ResultSet rs = null;
    // System.out.println("SELECT ID FROM PRODUCT WHERE NAME = \'" + product.getName() + "\';");
    try {
      Statement stmt = conn.createStatement();
      rs = stmt.executeQuery("SELECT ID FROM PRODUCT WHERE NAME = \'" + product.getName() + "\';");

      while (rs.next()) {
        id = rs.getInt("ID");
      }

    } catch (SQLException e) {
      System.out.println("ERROR: QUERY FAILED!");
    }
    disconnectFromDB();
    return id;
  }

  public ObservableList loadProductList() throws SQLException {
    ObservableList<Product> observableProductLine = FXCollections.observableArrayList();
    connectToDB();
    String sql = "SELECT * FROM PRODUCT";
    ResultSet rs = statement.executeQuery(sql);
    while (rs.next()) {
      Integer id = rs.getInt(1);
      String name = rs.getString(2);
      String type = rs.getString(3);
      String manufacturer = rs.getString(4);
      switch (type) {
        case "AU":
          AudioPlayer tempObject1 =
              new AudioPlayer(
                  name,
                  manufacturer,
                  "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC",
                  "M3U/PLS/WPL");
          tempObject1.setId(id);
          observableProductLine.add(tempObject1);
          break;
        case "VI":
          Screen newScreen = new Screen("720x480", 40, 22);
          MoviePlayer tempObject2 = new MoviePlayer(name, manufacturer, newScreen, MonitorType.LCD);
          tempObject2.setId(id);
          observableProductLine.add(tempObject2);
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
    }
    disconnectFromDB();
    return observableProductLine;
  }

  public ArrayList loadProductionRecordList(ObservableList<Product> observableList) throws SQLException {
    ArrayList<ProductionRecord> productionRecordList = new ArrayList<>();
    connectToDB();
    String sql = "SELECT * FROM PRODUCTIONRECORD";
    ResultSet rs = statement.executeQuery(sql);
    ArrayList<Product> tempList = new ArrayList<>(observableList);
    while (rs.next()) {
      Integer productionNumber = rs.getInt(1);
      Integer productID = rs.getInt(2);
      String serialNumber = rs.getString(3);
      String date = rs.getString(4);
      ProductionRecord tempRecord =
          new ProductionRecord(productionNumber, productID, serialNumber, new Date());
      for (Product product : tempList) {
        if (product.getId() == tempRecord.getProductID()) {
          tempRecord.setProduct(product);
        }
      }
      productionRecordList.add(tempRecord);
      tempSerial = tempRecord.getSerialNumber();
      tempSerial = tempSerial.substring(tempSerial.length() - 5, tempSerial.length());
      //System.out.println(tempSerial);

      if (tempRecord.getProduct() instanceof AudioPlayer) {
        // System.out.println("AudioPlayer Production Created");
        if (audioCount <= Integer.parseInt(tempSerial)) {
          audioCount = Integer.parseInt(tempSerial) ;
          //System.out.println(visualCount);
        }
      }
      if (tempRecord.getProduct() instanceof MoviePlayer) {
        // System.out.println("MoviePlayer Production Created");
        if (visualCount <= Integer.parseInt(tempSerial)) {
          visualCount = Integer.parseInt(tempSerial);
          //System.out.println(visualCount);
        }
      }
      }
    disconnectFromDB();
    return productionRecordList;
  }

  public void addToProductionDBMethod(String[] insertValues) throws SQLException {
    connectToDB();
    //String sql = "SELECT COUNT(SERIAL_NUM) FROM PRODUCTIONRECORD WHERE SERIAL_NUM = \'" + insertValues[2] + "\'" ;
    //ResultSet rs = statement.executeQuery(sql);
    //if (rs == null) {
      String insertQuery =
          "INSERT INTO PRODUCTIONRECORD "
              + "(PRODUCTION_NUM, PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED)"
              + " VALUES (?, ?, ?, ?)";
      PreparedStatement pstmt = conn.prepareStatement(insertQuery);
      pstmt.setInt(1, Integer.parseInt(insertValues[0]));
      pstmt.setInt(2, Integer.parseInt(insertValues[1]));
      pstmt.setString(3, insertValues[2]);
      pstmt.setDate(4, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
      pstmt.executeUpdate();
      disconnectFromDB();
    }
   // else{
      //System.out.println("Production Record already exists");
    //}
  //}

  /** Called after every database interaction is concluded to close connection to the database. */
  public void disconnectFromDB() {
    try {
      conn.close();
      statement.close();

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  public int getAudioCount(){
    return audioCount;
  }
  public int getVisualCount(){
    return visualCount;
  }
}
