/**
 * Class designed to test the ProductionRecord class.
 *
 * @author Sean Lamont
 * @brief Tests the ProductionRecord class.
 * @date 11/5/19
 */
package lamont;
// conflict between CheckStyle and google format.

import java.util.Date;

public class TestProductionRecord {

  /**
   * Main runnable class to test ProductionRecord.
   *
   * @param args main arguments.
   * @throws IllegalProductArgumentException thrown if a parameter is invalid.
   */
  public static void main(String[] args) throws IllegalProductArgumentException{

    // test constructor used when creating production records from user interface
    Integer numProduced = 3; // this will come from the combobox in the UI

    for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
      ProductionRecord pr = new ProductionRecord(0);
      System.out.println(pr.toString());
    }

    // test constructor used when creating production records from reading database
    ProductionRecord pr = new ProductionRecord(0, 3, "1", new Date());
    System.out.println(pr.toString());

    // testing accessors and mutators
    pr.setProductionNum(1);
    System.out.println(pr.getProductionNum());

    pr.setProductID(4);
    System.out.println(pr.getProductID());

    pr.setSerialNum("2");
    System.out.println(pr.getSerialNumber());

    pr.setProdDate(new Date());
    System.out.println(pr.getProdDate());

    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer moviePlayer1 =
        new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);

    ProductionRecord test1 = new ProductionRecord(moviePlayer1, 10);

    System.out.println(moviePlayer1);
    System.out.println(test1);
  }
}
