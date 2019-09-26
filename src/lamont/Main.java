package lamont;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
  private Statement statement;
    @Override
    public void start(Stage primaryStage) throws Exception{
        connectToDB();
        Parent root = FXMLLoader.load(getClass().getResource("OOPProject.fxml"));
        primaryStage.setTitle("OOPProject");
        //primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

  private void connectToDB() {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/ProductionDB";
    final String USER = "";
    final String PASS = "";
    Connection conn = null;

    try {

      Class.forName(JDBC_DRIVER);

      conn = DriverManager.getConnection(DB_URL, USER, PASS);

    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    }catch (Exception ex) {
      ex.printStackTrace();
    }
  }


    public static void main(String[] args) {
        launch(args);
    }
}
