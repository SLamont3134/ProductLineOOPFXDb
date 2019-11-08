/**
 * The Controller class where all of the code will be stored.
 *
 * @author Sean Lamont
 * @brief This is the main controller file which handles all of the operations of the GUI
 * @date 9/28/19
 */
package lamont;
// Conflict between Google Format and CheckStyle.

// There is a conflict above "package lamont;" between line formatting (ctrl+alt+L) and CheckStyle,
// they keep adding or taking away a gap respectively.

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * The controller class carries out the functions of the GUI.
 *
 * @author Sean Lamont
 */
public class Controller implements Initializable {

  static final String JDBC_DRIVER = "org.h2.Driver";
  static final String DB_URL = "jdbc:h2:./rsc/ProductDB";
  static final String USER = "";
  static final String PASS = "";
  private Connection conn = null;
  private Statement statement;
  private int audioCount;
  private int visualCount;
  private int audioMobileCount;
  private int visualMobileCount;
  private ArrayList<ProductionRecord> productionRecord = new ArrayList<>();
  ObservableList<Product> observableProductLine = FXCollections.observableArrayList();

  @FXML private TextField productNameWindow;

  @FXML private TextField manufacturerNameWindow;

  @FXML private ChoiceBox<ItemType> itemTypeCB;

  @FXML private Button addButton;

  @FXML private TableView<Product> existingProductWindow;

  @FXML private ComboBox<String> chooseQtyBox;

  @FXML private Button recordProductionBttn;

  @FXML private TextArea productionLogTextArea;

  @FXML private TableColumn<?, ?> productNameColumn;

  @FXML private TableColumn<?, ?> productTypeColumn;

  @FXML private ListView<Product> chooseProductWindow;

  @FXML private TableColumn<?, ?> productManufacturerColumn;

  @FXML private TableColumn<?, ?> productIdColumn;

  /**
   * The addButtonAction this method handle the event of the addButton being pressed.
   *
   * @return void
   * @param event sets the action event
   */
  @FXML
  void addButtonAction(ActionEvent event) {

    createProductObject();
    displayProductionRecordLog();
    setProductWindow();
    System.out.println("Add Button Pressed");
  }

  /**
   * The recordProductionButton this method handle the event of the productionButton being pressed.
   *
   * @return void
   * @param event this event handles the record product button
   */
  @FXML
  void recordProductionBttnAction(ActionEvent event) {
    createProductionRecordObject();
    System.out.println("Record Production Button Pressed");
  }

  /**
   * Establishes connection to database.
   *
   * @author Sean Lamont
   */
  public void connectToDB() {
    try {
      Class.forName(JDBC_DRIVER);

      /*For this following line of code I receive a empty database password flag on FindBugs
       * this issue will be addressed in later versions if a password is to be implemented
       */
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = conn.createStatement();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /** Called when a new product is made and needs to be inserted into the database. */
  public void createProductObject() {
    ItemType type = itemTypeCB.getValue();
    String brand = manufacturerNameWindow.getText();
    String name = productNameWindow.getText();
    switch (type) {
      case Audio:
        observableProductLine.add(
            new AudioPlayer(
                name, brand, "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL"));
        break;
      case Visual:
        Screen newScreen = new Screen("720x480", 40, 22);
        observableProductLine.add(new MoviePlayer(name, brand, newScreen, MonitorType.LCD));
        break;
      case AudioMobile:
        break;
      case VisualMobile:
        break;
      default:
        break;
    }
    return;
  }

  /** Called after every database interaction is concluded to close connection to the database. */
  public void disconnectFromDB() {
    try {
      conn.close();
      statement.close();

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /** Test class for multimedia, not usually used. */
  public void testMultimedia() {
    int serialNumber = 00001;
    AudioPlayer newAudioProduct =
        new AudioPlayer(
            "DP-X1A", "Onkyo", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    newAudioProduct.setID(serialNumber);
    // serialNumber++;
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct =
        new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);
    newMovieProduct.setID(serialNumber);
    ProductionRecord newRecord = new ProductionRecord(serialNumber);
    System.out.println(newRecord.getSerialNum());
    ArrayList<MultimediaControl> productLine = new ArrayList<MultimediaControl>();
    productLine.add(newAudioProduct);
    productLine.add(newMovieProduct);
    productionLogTextArea.appendText(productLine.toString());
    // System.out.println(newRecord.findProductMatch(productLine));

    for (MultimediaControl p : productLine) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();
    }
  }

  /**
   * Displays the production log in the production log text area. Called every time the production
   * log is updated to update the window.
   */
  public void displayProductionRecordLog() {
    productionLogTextArea.clear();
    for (ProductionRecord product : productionRecord) {
      productionLogTextArea.appendText(product.toString());
    }
  }

  /** Sets up the boxes, called once at initialization. */
  public void setUpBoxes() {
    itemTypeCB.getItems().setAll(ItemType.values());
    itemTypeCB.getSelectionModel().selectFirst();
    chooseQtyBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    chooseQtyBox.setEditable(true);
    chooseQtyBox.getSelectionModel().selectFirst();
  }

  /**
   * Keeps track of incremental portion of serial number. Will probably be replaced by database
   * incrementation.
   *
   * @param product Product, used to determine which count to return and increment.
   * @return int, the number to be appended to the serial number.
   */
  public int itemCount(Product product) {
    int tempInt;
    switch (product.getItemTypeCode()) {
      case Audio:
        tempInt = ++audioCount;
        break;
      case Visual:
        tempInt = ++visualCount;
        break;
      case AudioMobile:
        tempInt = ++audioMobileCount;
        break;
      case VisualMobile:
        tempInt = ++visualMobileCount;
        break;
      default:
        tempInt = 00000;
        break;
    }
    return tempInt;
  }

  /**
   * Sets up the product line table as well as the observable list. Called once at initialization,
   * currently starts with test data, will be replaced once database is running.
   */
  public void setProductLineTable() {
    // observableProductLine.clear();
    observableProductLine = FXCollections.observableArrayList();
    observableProductLine.add(
        new AudioPlayer(
            "Demo", "Product", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL"));
    productNameColumn.setCellValueFactory(new PropertyValueFactory("id"));
    productNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
    productTypeColumn.setCellValueFactory(new PropertyValueFactory("type"));
    productManufacturerColumn.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    existingProductWindow.setItems(observableProductLine);
  }

  /**
   * Sets the product window to display the observable list. Called once at initialize then
   * automatically updated with observable list.
   */
  public void setProductWindow() {
    chooseProductWindow.getItems().clear();
    chooseProductWindow.getItems().addAll(observableProductLine);
  }

  /**
   * Method to test serial number generation. Not currently used, will eventually be removed once
   * testing is complete.
   */
  public void testSerialNumber() {
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer moviePlayer1 =
        new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);

    ProductionRecord test1 = new ProductionRecord(moviePlayer1, 10, itemCount(moviePlayer1));
    test1.setSerialNum(test1.getProduct(), itemCount(test1.getProduct()));
    System.out.println(test1);
    MoviePlayer moviePlayer2 =
        new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);

    ProductionRecord test2 = new ProductionRecord(moviePlayer2, 10, itemCount(moviePlayer2));
    test2.setSerialNum(test2.getProduct(), itemCount(test2.getProduct()));
    System.out.println(test2);
  }

  /**
   * Called whenever a new production record is produced. Creates production record based on input
   * from the gui, then posts it to production log area.
   */
  public void createProductionRecordObject() {
    int qnty = Integer.parseInt(chooseQtyBox.getSelectionModel().getSelectedItem());
    for (int i = 0; i < qnty; i++) {

      productionRecord.add(
          new ProductionRecord(
              chooseProductWindow.getSelectionModel().getSelectedItem(),
              qnty,
              itemCount(chooseProductWindow.getSelectionModel().getSelectedItem())));
    }
    productionLogTextArea.clear();
    productionLogTextArea.appendText(productionRecord.toString());
  }

  /** Sets the initial production record window at initialize. */
  public void setProductionRecordWindow() {
    productionLogTextArea.clear();
    productionLogTextArea.appendText(productionRecord.toString());
  }

  /**
   * Called every time the program starts for the first time. Sets the tables buttons etc.
   *
   * @param location Location.
   * @param resources ResourceBundle.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    setUpBoxes();
    setProductLineTable();
    displayProductionRecordLog();
    setProductWindow();
    setProductionRecordWindow();
  }
}
