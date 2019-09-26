package lamont;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Sean Lamont
 */

//be sure to do analyze inspect code

//also use findbugs
public class Main extends Application {

  /**
   * the start method is the starting point of a JavaFX program. The start method sets the title.
   * @brief
   * @param primaryStage the primary stage
   * @throws Exception  Any problems with code
   */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("OOPProject.fxml"));
        primaryStage.setTitle("OOPProject");
        //primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
