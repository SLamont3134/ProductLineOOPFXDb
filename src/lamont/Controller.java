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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

  @FXML
  private TextField productNameWindow;
  @FXML
  private TextField manufacturerNameWindow;
  @FXML
  private ChoiceBox<ItemType> itemTypeCB;

  @FXML private Button addButton;

  @FXML private TableView<?> existingProductWindow;

  @FXML private ComboBox<Integer> chooseQtyBox;

  @FXML private Button recordProductionBttn;

  @FXML private TextArea productionLogTextArea;

  /**
   * The addButtonAction this method handle the event of the addButton being pressed.
   *
   * @return void
   * @param event sets the action event
   */
  @FXML
  void addButtonAction(ActionEvent event) {

    try {

      String sql =
          "INSERT INTO Product(type, manufacturer, name) VALUES " + "( 'AUDIO', 'Apple', 'iPod' );";
      // String sql = "SELECT * FROM PRODUCT";
      statement.execute(sql);

    } catch (Exception ex) {
      ex.printStackTrace();
    }

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
    System.out.println("Record Production Button Pressed");
  }

  /** The addButtonAction this method handle the event of the addButton being pressed. */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // connects to database */
    connectToDB();
    // sets a few options on startup for chooseQtyBox */
    chooseQtyBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    chooseQtyBox.setEditable(true);
    chooseQtyBox.getSelectionModel().selectFirst();
    itemTypeCB.getItems().setAll(ItemType.values());
    itemTypeCB.getSelectionModel().selectFirst();
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
}
