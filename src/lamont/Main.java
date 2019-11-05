/**
 * The Main Class that contains the Main method and initializes the stage.
 *
 * @author Sean Lamont
 * @brief This is the main file which builds the environment and calls the controller
 * @date 9/28/19
 */
package lamont;

// There is a conflict above "package lamont;" between line formatting (cntrl+alt+L) and CheckStyle,
// they keep adding or taking away a gap respectively.

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main class, sets the stage of the GUI, calls all other methods.
 *
 * @author Sean Lamont
 */
public class Main extends Application {

  /**
   * the start method is the starting point of a JavaFX program. The start method sets the title.
   *
   * @param primaryStage the primary stage
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    // connectToDB();
    Parent root = FXMLLoader.load(getClass().getResource("lamont/OOPProject.fxml"));
    primaryStage.setTitle("OOPProject");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  /**
   * The main method, simply launches the program.
   *
   * @param args Arguments passed into main method to launch()
   */
  public static void main(String[] args) {
    launch(args);
  }
}
