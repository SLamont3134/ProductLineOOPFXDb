/**
 * @author Sean Lamont
 */

package lamont;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;


/**
 * @author Sean Lamont
 * @brief The Controller class, Controls the GUI and calls all methods.
 * @throws Exception ex, ClassNotFoundException
 */
public class Controller implements Initializable {

  static final String JDBC_DRIVER = "org.h2.Driver";
  static final String DB_URL = "jdbc:h2:./rsc/ProductDB";
  static final String USER = "";
  static final String PASS = "";
  private Connection conn = null;
  private Statement statement;

  @FXML private Button addButton;

  @FXML private TableView<?> existingProductWindow;

  @FXML private ComboBox<Integer> chooseQtyBox;

  @FXML private Button recordProductionBttn;

  @FXML private TextArea productionLogTextArea;


  /**
   * @brief The addButtonAction this method handle the event of the addButton being pressed.
   * @return void
   * @param event
   */
  @FXML
  void addButtonAction(ActionEvent event) {

    try {
      String sql =
          "INSERT INTO Product(type, manufacturer, name) VALUES " + "( 'AUDIO', 'Apple', 'iPod' );";
      //String sql = "SELECT * FROM PRODUCT";
      statement.execute(sql);

    } catch (Exception ex) {
      ex.printStackTrace();
    }


    System.out.println("Add Button Pressed");


  }

  /**
   * @brief The recordProductionButton this method handle the event of the
   * productionButton being pressed.
   * @return void
   * @param event
   */
  @FXML
  void recordProductionBttnAction(ActionEvent event) {
    System.out.println("Record Production Button Pressed");
  }

  /**
   * @brief The addButtonAction this method handle the event of the addButton being pressed.
   * @return void
   * @param location, resources
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      // Main.connectToDB();

      Class.forName(JDBC_DRIVER);

      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = conn.createStatement();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    chooseQtyBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    chooseQtyBox.setEditable(true);
    chooseQtyBox.getSelectionModel().selectFirst();
  }
}
