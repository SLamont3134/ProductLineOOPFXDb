/**
 * The DatabaseManager class the handles all of the database interactions.
 *
 * @author Sean Lamont
 * @brief This is the main database handler class which controls all interactions with the databse.
 * @date 10/20/19
 */
package lamont;
// Conflict between Google Format and CheckStyle.

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseManager {

  static final String JDBC_DRIVER = "org.h2.Driver";
  static final String DB_URL = "jdbc:h2:./rsc/ProductDB";
  private String user = "";
  private String pass = "";
  private Connection conn = null;
  private Statement statement;
  private PreparedStatement pstmt;
  private int audioCount;
  private int visualCount;
  private String tempSerial;

  /**
   * Default Constructor used to make the other methods reachable from outside of the
   * DatabaseManager class.
   */
  DatabaseManager() {}
  // There is a conflict here between CheckStyle and Google Formatting about the "}" being on a
  // separate line

  /**
   * Imports the encrypted password from the database that is then passed to reverseString method to
   * decrypt. Then connects to the database using the password.
   *
   * @author Sean Lamont
   */
  public void connectToDB() {

    try {
      Properties prop = new Properties();
      prop.load(new FileInputStream("rsc/properties"));
      String tempPassword = prop.getProperty("password");
      pass = reverseString(tempPassword);
    } catch (IOException e) {
      System.out.println("Couldn't Retrieve Database Password");
    }

    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, user, pass);
      statement = conn.createStatement();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println("Error Connecting to DB");

    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println("Error Connecting to DB");
    }
  }

  /**
   * Checks the Production Record table of the database for a Production Record matching the record
   * passed in.
   *
   * @param productionRecord The Production Record object to be compared to the database.
   * @return returns true if a matching record is found, false if a match is not found.
   */
  public boolean checkDbForProductionRecordName(ProductionRecord productionRecord) {
    connectToDB();
    ResultSet rs = null;
    int count = 1;
    try {
      statement = conn.createStatement();
      rs =
          statement.executeQuery(
              "SELECT COUNT(SERIAL_NUM) FROM PRODUCTIONRECORD WHERE SERIAL_NUM = \'"
                  + productionRecord.getSerialNumber()
                  + "\' ;");
      while (rs.next()) {
        count = rs.getInt(1);
      }

    } catch (SQLException e) {
      System.out.println("Couldn't Check database for production record");
    }
    disconnectFromDB();
    if (count > 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Inserts a new Product into the Product table using a Prepared statement.
   *
   * @param insertValues the values to be inserted.
   * @throws SQLException database error.
   */
  public void insertProductIntoDB(String[] insertValues) throws SQLException {
    connectToDB();
    String insertQuery =
        "INSERT INTO PRODUCT " + "(NAME, TYPE, MANUFACTURER)" + " VALUES (?, ?, ?)";
    pstmt = conn.prepareStatement(insertQuery);
    pstmt.setString(1, insertValues[0]);
    pstmt.setString(2, insertValues[1]);
    pstmt.setString(3, insertValues[2]);
    pstmt.executeUpdate();
    disconnectFromDB();
  }

  /**
   * Searches the database Product table for a product with a matching Id to the passed in Product.
   *
   * @param product The product to compare to the database.
   * @return returns the id of the matching product. Returns 0 if no matching product is found.
   */
  public int selectProductIdFromDb(Product product) {
    int id = 0;
    connectToDB();
    ResultSet rs = null;
    try {
      statement = conn.createStatement();
      rs =
          statement.executeQuery(
              "SELECT ID FROM PRODUCT WHERE NAME = \'" + product.getName() + "\';");

      while (rs.next()) {
        id = rs.getInt("ID");
      }

    } catch (SQLException e) {
      System.out.println("ERROR: QUERY FAILED!");
    }
    disconnectFromDB();
    return id;
  }

  /**
   * Reads each row from the Product Table importing each of them and creating the appropriate
   * Product.
   *
   * @return An ObservaleList of Products to be displayed by the GUI.
   * @throws SQLException database error.
   */
  public ObservableList loadProductList() throws SQLException {
    ObservableList<Product> observableProductLine = FXCollections.observableArrayList();
    connectToDB();
    String sql = "SELECT * FROM PRODUCT";
    ResultSet rs = statement.executeQuery(sql);
    while (rs.next()) {
      int id = rs.getInt(1);
      String name = rs.getString(2);
      String type = rs.getString(3);
      String manufacturer = rs.getString(4);
      switch (type) {
        case "AU":
          AudioPlayer tempObject1 =
              new AudioPlayer(
                  id,
                  name,
                  manufacturer,
                  "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC",
                  "M3U/PLS/WPL");
          observableProductLine.add(tempObject1);
          break;
        case "VI":
          Screen newScreen = new Screen("720x480", 40, 22);
          MoviePlayer tempObject2 =
              new MoviePlayer(id, name, manufacturer, newScreen, MonitorType.LCD);
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

  /**
   * Reads each Record in the Production Record table then imports them creating Production Record
   * objects for the GUI to display.
   *
   * @param observableList The list of Products the GUI has to be used to associate a Product Object
   *     with each Production Record.
   * @return ArrayList of Production Records to be used by the GUI.
   * @throws SQLException database error.
   */
  public ArrayList loadProductionRecordList(ObservableList<Product> observableList)
      throws SQLException {
    ArrayList<ProductionRecord> productionRecordList = new ArrayList<>();
    connectToDB();
    String sql = "SELECT * FROM PRODUCTIONRECORD";
    ResultSet rs = statement.executeQuery(sql);
    ArrayList<Product> tempList = new ArrayList<>(observableList);
    while (rs.next()) {
      int productionNumber = rs.getInt(1);
      int productID = rs.getInt(2);
      String serialNumber = rs.getString(3);
      Timestamp date = rs.getTimestamp(4);
      String employeeUsername = rs.getString(5);
      ProductionRecord tempRecord =
          new ProductionRecord(productionNumber, productID, serialNumber, new Date(date.getTime()));
      tempRecord.setEmployeeUsername(employeeUsername);
      for (Product product : tempList) {
        if (product.getId() == tempRecord.getProductID()) {
          tempRecord.setProduct(product);
        }
      }
      productionRecordList.add(tempRecord);
      tempSerial = tempRecord.getSerialNumber();
      tempSerial = tempSerial.substring(tempSerial.length() - 5, tempSerial.length());

      if (tempRecord.getProduct() instanceof AudioPlayer) {
        if (audioCount <= Integer.parseInt(tempSerial)) {
          audioCount = Integer.parseInt(tempSerial);
        }
      }
      if (tempRecord.getProduct() instanceof MoviePlayer) {
        if (visualCount <= Integer.parseInt(tempSerial)) {
          visualCount = Integer.parseInt(tempSerial);
        }
      }
    }
    disconnectFromDB();
    return productionRecordList;
  }

  /**
   * A method utilizing Prepared Statements to insert Production Records into the SQL database.
   *
   * @param insertValues The String[] values to be used for the P.S.
   * @throws SQLException database error.
   */
  public void addToProductionDbMethod(String[] insertValues) throws SQLException {
    connectToDB();
    String insertQuery =
        "INSERT INTO PRODUCTIONRECORD "
            + "(PRODUCTION_NUM, PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED, EMPLOYEE)"
            + " VALUES (?, ?, ?, ?,?)";
    pstmt = conn.prepareStatement(insertQuery);
    pstmt.setInt(1, Integer.parseInt(insertValues[0]));
    pstmt.setInt(2, Integer.parseInt(insertValues[1]));
    pstmt.setString(3, insertValues[2]);
    pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
    pstmt.setString(5, insertValues[3]);
    pstmt.executeUpdate();
    disconnectFromDB();
  }

  /** Called after every database interaction is concluded to close connection to the database. */
  public void disconnectFromDB() {
    try {
      if (!(conn == null)) {
        conn.close();
      }
      if (!(statement == null)) {
        statement.close();
      }
      if (!(pstmt == null)) {
        pstmt.close();
      }

    } catch (Exception ex) {
      System.out.println("Error Disconnecting From Database");
      ex.printStackTrace();
    }
  }

  /**
   * This method is used to decrypt the databse password that is pulled from the properties file.
   *
   * @param string Password to be decoded.
   * @return String decoded database password.
   */
  public String reverseString(String string) {
    if (string.isEmpty()) {
      return string;
    }
    return reverseString(string.substring(1)) + string.charAt(0);
  }

  /**
   * Used for the exporting of serial numbers of AudioPlayers to ensure their uniqueness.
   *
   * @return int, Audio Count used for Serial Numbers.
   */
  public int getAudioCount() {
    return audioCount;
  }

  /**
   * Used for the exporting of serial numbers of MoviePlayers to ensure their uniqueness.
   *
   * @return int, Audio Count used for Serial Numbers.
   */
  public int getVisualCount() {
    return visualCount;
  }
}
