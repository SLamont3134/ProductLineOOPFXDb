/**
 * The Controller class where all of the code will be stored.
 *
 * @author Sean Lamont
 * @brief This is the main controller file which handles all of the operations of the GUI
 * @date 9/28/19
 */
package lamont;

// There is a conflict above "package lamont;" between line formatting (cntrl+alt+L) and CheckStyle,
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
  private ArrayList<Product> productLine = new ArrayList<>();
  private ArrayList<ProductionRecord> productionRecord = new ArrayList<>();
  ObservableList<Product> observableProductLine = FXCollections.observableArrayList(productLine);

  @FXML private TextField productNameWindow;
  @FXML private TextField manufacturerNameWindow;
  @FXML private ChoiceBox<ItemType> itemTypeCB;

  @FXML private Button addButton;

  @FXML private TableView<Product> existingProductWindow;

  @FXML private ComboBox<String> chooseQtyBox;

  @FXML private Button recordProductionBttn;

  @FXML private TextArea productionLogTextArea;

  @FXML
  private TableColumn<?, ?> productNameColumn;

  @FXML
  private TableColumn<?, ?> productTypeColumn;

  @FXML
  private ListView<Product> chooseProductWindow;

  @FXML
  private TableColumn<?, ?> productManufacturerColumn;



  /**
   * The addButtonAction this method handle the event of the addButton being pressed.
   *
   * @return void
   * @param event sets the action event
   */
  @FXML
  void addButtonAction(ActionEvent event) {

    createProductObject();
    updateObservableList();
    displayProductionRecordLog();
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

  public void disconnectFromDB() {
    try {
      conn.close();
      statement.close();

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

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

  public void displayProductionRecordLog() {
    productionLogTextArea.clear();
    for(Product product: productLine){
      productionLogTextArea.appendText(product.productLogString());
    }
    //productionLogTextArea.appendText(productLine.productLogString());
  }


  public void setUpBoxes() {
    itemTypeCB.getItems().setAll(ItemType.values());
    itemTypeCB.getSelectionModel().selectFirst();
    chooseQtyBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    chooseQtyBox.setEditable(true);
    chooseQtyBox.getSelectionModel().selectFirst();
    // System.out.println(itemTypeCB.getValue());
  }

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

  public void createProductObject() {
    ItemType type = itemTypeCB.getValue();
    String brand = manufacturerNameWindow.getText();
    String name = productNameWindow.getText();
    switch (type) {
      case Audio:
        productLine.add(
            new AudioPlayer(
                name, brand, "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC",
                "M3U/PLS/WPL"));
        break;
      case Visual:
        Screen newScreen = new Screen("720x480", 40, 22);
        productLine.add(new MoviePlayer(name, brand, newScreen,
            MonitorType.LCD));
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

  public void createProductDB() {
    try {
      String type = itemTypeCB.getValue().code;
      String brand = manufacturerNameWindow.getText();
      String name = productNameWindow.getText();
      String sql =
          "INSERT INTO Product(type, manufacturer, name) VALUES( '"
              + type
              + "', '"
              + brand
              + "', '"
              + name
              + "' );";
      // String sql = "SELECT * FROM PRODUCT";
      statement.execute(sql);

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    System.out.println("Add Button Pressed");
  }


    public void setProductLineTable() {
    observableProductLine.clear();
      productLine.add(new AudioPlayer(
          "ted", "brand", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC",
          "M3U/PLS/WPL"));
      observableProductLine = FXCollections.observableArrayList(productLine);
      productNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
      productTypeColumn.setCellValueFactory(new PropertyValueFactory("type.toString()"));
      productManufacturerColumn.setCellValueFactory(new PropertyValueFactory("manufacturer"));
      existingProductWindow.setItems(observableProductLine);
    }

  public void updateObservableList() {
    observableProductLine.clear();
    observableProductLine = FXCollections.observableArrayList(productLine);
    productNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
    productTypeColumn.setCellValueFactory(new PropertyValueFactory("type"));
    productManufacturerColumn.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    existingProductWindow.setItems(observableProductLine);
  }

  public void setProductWindow(){
    chooseProductWindow.getItems().clear();
    chooseProductWindow.getItems().addAll(productLine);
  }

  public void testSerialNumber(){
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer moviePlayer1 = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen,
        MonitorType.LCD);

    ProductionRecord test1 = new ProductionRecord(moviePlayer1, 10, itemCount(moviePlayer1));
    test1.setSerialNum(test1.getProduct(), itemCount(test1.getProduct()));
    System.out.println(test1);
    MoviePlayer moviePlayer2 = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen,
        MonitorType.LCD);

    ProductionRecord test2 = new ProductionRecord(moviePlayer2, 10, itemCount(moviePlayer2));
    test2.setSerialNum(test2.getProduct(), itemCount(test2.getProduct()));
    System.out.println(test2);
  }

  public void createProductionRecordObject(){
    //System.out.println(Integer.parseInt(chooseQtyBox.getSelectionModel().getSelectedItem()));
    int qnty = Integer.parseInt(chooseQtyBox.getSelectionModel().getSelectedItem());
    for(int i=0; i<qnty; i++){

    productionRecord.add(new ProductionRecord(chooseProductWindow.getSelectionModel().getSelectedItem(),
            qnty, itemCount(chooseProductWindow.getSelectionModel().getSelectedItem())));
    }
    productionLogTextArea.clear();
    productionLogTextArea.appendText(productionRecord.toString());
  }
  public void setProductionRecordWindow(){
    productionLogTextArea.clear();
    productionLogTextArea.appendText(productionRecord.toString());
  }


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    connectToDB();
    // testMultimedia();
    setUpBoxes();
    setProductLineTable();
    displayProductionRecordLog();
    //updateWindows();
    disconnectFromDB();
    setProductWindow();
    setProductionRecordWindow();
  }
}
