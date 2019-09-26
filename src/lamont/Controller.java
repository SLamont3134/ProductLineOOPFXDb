package lamont;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class Controller {

  @FXML
  private Button addButton;

  @FXML
  private TableView<?> existingProductWindow;

  @FXML
  private ComboBox<?> chooseQtyBox;

  @FXML
  private Button recordProductionBttn;

  @FXML
  private TextArea productionLogTextArea;

  @FXML
  void addButtonAction(ActionEvent event) {
    System.out.println("Add Button Pressed");
  }

  @FXML
  void recordProductionBttnAction(ActionEvent event) {
    System.out.println("Record Production Button Pressed");

  }

}
