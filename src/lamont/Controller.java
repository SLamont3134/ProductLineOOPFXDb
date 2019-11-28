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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLOutput;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

/**
 * The controller class carries out the functions of the GUI.
 *
 * @author Sean Lamont
 */
public class Controller implements Initializable {

  private int audioCount;
  private int visualCount;
  private int audioMobileCount;
  private int visualMobileCount;
  private ArrayList<ProductionRecord> productionRecord = new ArrayList<>();
  ObservableList<Product> observableProductLine = FXCollections.observableArrayList();
  DatabaseManager database = new DatabaseManager();
  Employee currentEmployee;

  @FXML
  private Label productionErrorBox;
  @FXML
  private ImageView productImage;

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

  @FXML private Button addEmployeeBttn;

  @FXML private TextField employeeNameField;

  @FXML private TextField employeePasswordField;

  @FXML
  private TextArea employeeInfo;


  @FXML
  private Label productErrorWindow;

  public Controller() throws SQLException, IOException {}

  public static final String addNameError = "Length Must Be Greater Than Zero";
  public static final String addManufacturerError = "Must Be Longer Than 3 Letters";
  public static final String employeeNameError = "Must be two Words with a space";
  public static final String passwordError = "Password Must Have One UpperCase Letter and One Symbol";

  /**
   * The addButtonAction this method handle the event of the addButton being pressed.
   *
   * @return void
   * @param event sets the action event
   */
  @FXML
  void addButtonAction(ActionEvent event) throws SQLException {
    productErrorWindow.setText("");
    if (!(currentEmployee == null)) {
      manufacturerNameWindow.setStyle("-fx-text-fill: black; -fx-font-size: 16px;");
      productNameWindow.setStyle("-fx-text-fill: black; -fx-font-size: 16px;");
      // System.out.println((productNameWindow.getLength() > 0));
      // System.out.println((productNameWindow.getText() != null));
      // System.out.println(!(productNameWindow.getText().equals(addNameError)));
      if ((productNameWindow.getLength() > 0)
          && (productNameWindow.getText() != null)
          && !(productNameWindow.getText().equals(addNameError))) {
        if ((manufacturerNameWindow.getLength() > 3)
            && (manufacturerNameWindow.getText() != null)
            && !(manufacturerNameWindow.getText().equals(addManufacturerError))) {
          createProductObject();
          displayProductionRecordLog();
          setProductWindow();
          setProductLineTable();
          System.out.println("Add Button Pressed");
        } else {
          manufacturerNameWindow.setText(addManufacturerError);
          manufacturerNameWindow.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
        }
      } else {
        productNameWindow.setText(addNameError);
        productNameWindow.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
      }
    }
    else{
      productErrorWindow.setText("You Must Be Logged In To Do That");
    }
    }

  /**
   * The recordProductionButton this method handle the event of the productionButton being pressed.
   *
   * @return void
   * @param event this event handles the record product button
   */
  @FXML
  void recordProductionBttnAction(ActionEvent event) throws SQLException {
    productionErrorBox.setText("");


    if (!(currentEmployee == null)) {
      createProductionRecordObject();
      showProduction();
    }
    else{
      productionErrorBox.setText("You must be logged in");
    }

    System.out.println("Record Production Button Pressed");
  }

  @FXML
  void addEmployeeButtonAction(ActionEvent event) {
    employeeNameField.setStyle("-fx-text-fill: black; -fx-font-size: 16px;");
    employeeNameField.setStyle("-fx-text-fill: black; -fx-font-size: 16px;");
    employeePasswordField.setStyle("-fx-text-fill: black; -fx-font-size: 16px;");
    employeePasswordField.setStyle("-fx-text-fill: black; -fx-font-size: 16px;");
    if ((employeeNameField.getLength() > 0)
        && (employeeNameField.getText() != null)
        && !(employeeNameField.getText().equals(employeeNameError))) {
      Boolean hasSpace = false;
      for (int i = 0; i < employeeNameField.getText().length(); i++) {
        if (employeeNameField.getText().charAt(i) == ' ') {
          hasSpace = true;
        }
      }
      if (hasSpace) {
        // Insert all code here
        if ((employeePasswordField.getLength() > 0)
            && (employeePasswordField.getText() != null)
            && !(employeePasswordField.getText().equals(employeeNameError))) {
          creatEmployee();
          employeeInfo.setText("Current User Information: \n" + currentEmployee.secureToString());
          employeeInfo.setOpacity(1.0);
        }
        else {
          employeePasswordField.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
          employeePasswordField.setText(passwordError);
        }
        System.out.println("Add employee Button Pressed");
      } else {
        employeeNameField.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
        employeeNameField.setText("Name must contain a space.");
      }
    } else {
      employeeNameField.setText(employeeNameError);
      employeeNameField.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
    }
  }

  public void creatEmployee() {
    String name = employeeNameField.getText();
    String password = employeePasswordField.getText();
    currentEmployee = new Employee(name, password);
    System.out.println("Employee Set!");
    System.out.println(currentEmployee);
  }

  /** Called when a new product is made and needs to be inserted into the database. */
  public void createProductObject() throws SQLException {

    String[] product = new String[3];
    product[0] = productNameWindow.getText();
    product[1] = itemTypeCB.getValue().getCode();
    product[2] = manufacturerNameWindow.getText();
    database.insertProductIntoDB(product);
    observableProductLine = database.loadProductList();
    productNameWindow.clear();
    manufacturerNameWindow.clear();
  }

  /**
   * Displays the production log in the production log text area. Called every time the production
   * log is updated to update the window.
   */
  public void displayProductionRecordLog() throws SQLException {
    productionLogTextArea.clear();
    productionRecord = database.loadProductionRecordList(observableProductLine);
    audioCount = database.getAudioCount();
    visualCount = database.getVisualCount();
    //System.out.println(productionRecord);
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
    productNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
    productIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
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
   * showProduction should: populate the TextArea on the Production Log tab with the information
   * from the productionLog, replacing the productId with the product name, with one line for each
   * product produced
   */
  public void showProduction() {
    String tempString;
    productionLogTextArea.clear();
    for (ProductionRecord record : productionRecord) {
      tempString =
          "Prod. Name: "
              + record.getProduct().getName()
              + " Product ID: "
              + record.getProductID()
              + " Serial Num: "
              + record.getSerialNumber()
              + " Date: "
              + record.getProdDate()
              + "\n";
      productionLogTextArea.appendText(tempString);
    }
  }

  /**
   * Method to test serial number generation. Not currently used, will eventually be removed once
   * testing is complete.
   */
  public void testSerialNumber() {
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer moviePlayer1 =
        new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);

    ProductionRecord test1 = new ProductionRecord(moviePlayer1, 10);
    // test1.setSerialNum(test1.getProduct(), itemCount(test1.getProduct()));
    //System.out.println(test1);
    MoviePlayer moviePlayer2 =
        new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);

    ProductionRecord test2 = new ProductionRecord(moviePlayer2, 10);
    // test2.setSerialNum(test2.getProduct(), itemCount(test2.getProduct()));
    //System.out.println(test2);
  }

  /**
   * Called whenever a new production record is produced. Creates production record based on input
   * from the gui, then posts it to production log area.
   */
  public void createProductionRecordObject() throws SQLException {

    if (!(chooseProductWindow.getSelectionModel().isEmpty())){
    int qnty = Integer.parseInt(chooseQtyBox.getSelectionModel().getSelectedItem());

    for (int i = qnty; i > 0; i--) {
      Product tempProduct = chooseProductWindow.getSelectionModel().getSelectedItem();
      int tempInt = 0;
      if (tempProduct instanceof AudioPlayer) {
        tempInt = ++audioCount;
        //System.out.println("Audio Count is " + tempInt);
      }
      if (tempProduct instanceof MoviePlayer) {
        tempInt = ++visualCount;
        //System.out.println("Visual Count is " + tempInt);
      }
      ProductionRecord tempRecord = new ProductionRecord(tempProduct, tempInt);
      productionRecord.add(tempRecord);
      if (!(database.checkDBForProductionRecordName(tempRecord))) {
        String[] tempString = new String[3];
        tempString[0] = String.valueOf(0);
        tempString[1] = String.valueOf(tempProduct.getId());
        tempString[2] = tempRecord.getSerialNumber();
        database.addToProductionDBMethod(tempString);
        System.out.println("Production Record Added to Database");
      }
    }
    } else {
      productionErrorBox.setText("Please Select a Product");
      }
    productionErrorBox.setText("");

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

    try {
      observableProductLine = database.loadProductList();
      // System.out.println(productionRecord);
    } catch (SQLException e) {
      System.out.println("Couldn't Load Product List");
    }
    try {
      displayProductionRecordLog();
    } catch (SQLException e) {
      System.out.println("Couldn't Load Production Record");
    }

    setProductLineTable();
    setProductWindow();
    // loadProductionLog();
    showProduction();
  }

  public void print() {
    System.out.println(chooseProductWindow.getSelectionModel().getSelectedItem());
  }
}
