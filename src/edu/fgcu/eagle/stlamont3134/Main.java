package edu.fgcu.eagle.stlamont3134;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main Class that contains the Main method and initializes the stage.
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
        primaryStage.setTitle("Production Wizard");
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
