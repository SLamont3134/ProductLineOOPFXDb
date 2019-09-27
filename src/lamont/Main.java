/**
 * @author Sean Lamont
 * @brief This is the main file which builds the environment and calls the controller
 * @date 9/28/19
 */
package lamont;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Sean Lamont
 * @desc The main method, establishes communication with the database.
 */
public class Main extends Application {

  /**
   * the start method is the starting point of a JavaFX program. The start method sets the title.
   *
   * @brief
   * @param primaryStage the primary stage
   * @throws Exception Any problems with code
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    // connectToDB();
    Parent root = FXMLLoader.load(getClass().getResource("OOPProject.fxml"));
    primaryStage.setTitle("OOPProject");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  /**
   * @brief The main method, simply launches the program.
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
