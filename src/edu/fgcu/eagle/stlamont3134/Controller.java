package edu.fgcu.eagle.stlamont3134;

import java.net.URL;
import java.sql.SQLException;
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

/**
 * The Controller class where all of the code will be stored, carries out the functions of the GUI.
 *
 * @author Sean Lamont
 */
public class Controller implements Initializable {

    private int audioCount;
    private int visualCount;
    private ArrayList<ProductionRecord> productionRecord = new ArrayList<>();
    ObservableList<Product> observableProductLine = FXCollections.observableArrayList();
    DatabaseManager database = new DatabaseManager();
    Employee currentEmployee;

    @FXML
    private Label productionErrorBox;
    @FXML
    private ImageView productImage;
    @FXML
    private TextField productNameWindow;
    @FXML
    private TextField manufacturerNameWindow;
    @FXML
    private ChoiceBox<ItemType> itemTypeCB;
    @FXML
    private Button addButton;
    @FXML
    private TableView<Product> existingProductWindow;
    @FXML
    private ComboBox<String> chooseQtyBox;
    @FXML
    private Button recordProductionBttn;
    @FXML
    private TextArea productionLogTextArea;
    @FXML
    private TableColumn<?, ?> productNameColumn;
    @FXML
    private TableColumn<?, ?> productTypeColumn;
    @FXML
    private ListView<Product> chooseProductWindow;
    @FXML
    private TableColumn<?, ?> productManufacturerColumn;
    @FXML
    private TableColumn<?, ?> productIdColumn;
    @FXML
    private Button addEmployeeBttn;
    @FXML
    private TextField employeeNameField;
    @FXML
    private TextField employeePasswordField;
    @FXML
    private Label employeeInfo;
    @FXML
    private Label productErrorWindow;
    @FXML
    private Label employeeErrorBox;
    @FXML
    private Label productEmplBox;
    @FXML
    private Label productionEmplBox;
    @FXML
    private Label productionLogEmplBox;

    public Controller() {
        // There is a conflict here between CheckStyle and Google Formatting about the "}"
        // being on a separate line. Which is why these comments are here.
    }

    public static final String addNameError = "Name Length Must Be Greater Than Zero";
    public static final String addManufacturerError = "Manufacturer Must Be Longer Than 3 Letters";
    public static final String employeeNameError = "Name Must be two Words with a space";
    public static final String passwordError =
            "Password Must Have One UpperCase Letter and One Symbol";
    public static final String employeeError = "You Must Login";
    public static final String blankMessage = "";

    /**
     * The addButtonAction this method handle the event of the addButton being pressed.
     *
     * @param event sets the action event
     * @return void
     */
    @FXML
    void addButtonAction(ActionEvent event) throws SQLException {
        // System.out.println("Add Button Pressed");
        if (verifyProduct()) {
            createProductObject();
            displayProductionRecordLog();
            initializeProductWindow();
            initializeProductLineTable();
            clearProductLineTab();
        }
    }

    /**
     * The recordProductionButton this method handle the event of the productionButton being pressed.
     *
     * @param event this event handles the record product button
     * @return void
     */
    @FXML
    void recordProductionBttnAction(ActionEvent event) throws SQLException {
        // System.out.println("Record Production Button Pressed");
        if (verifyProductionRecord()) {
            createProductionRecordObject();
            showProduction();
            clearProductTab();
        }
    }

    @FXML
    void addEmployeeButtonAction(ActionEvent event) {
        // System.out.println("Add employee Button Pressed");
        if (!(isLoggedIn())) {
            if (verifyEmployee()) {
                creatEmployee();
                loginEmployee(currentEmployee);
                showProduction();
                clearEmployeeTab();
            }
        } else {
            logOutEmployee();
        }
    }

    /**
     * Creates a new employee object that allows access to other functions of the program.
     */
    public void creatEmployee() {
        currentEmployee = new Employee(employeeNameField.getText(),
                employeePasswordField.getText());
    }

    /**
     * Populates several fields to indicate the current employee.
     *
     * @param employee currently logged in employee
     */
    public void loginEmployee(Employee employee) {
        employeeInfo.setText("Current User Information: \n" + employee.secureToString());
        employeeInfo.setOpacity(1);
        existingProductWindow.setOpacity(1);
        chooseProductWindow.setOpacity(1);
        productEmplBox.setText("Logged In As: " + employee.getUsername());
        productionEmplBox.setText("Logged In As: " + employee.getUsername());
        productionLogEmplBox.setText("Logged In As: " + employee.getUsername());
        // System.out.println("Employee Set!");
    }

    /**
     * Called when the log out button is pressed. Used to set the GUI back to the logged out state.
     */
    public void logOutEmployee() {
        currentEmployee = new Employee();
        clearEmployeeInformation();
        // System.out.println("Logged Out");
    }

    public void clearEmployeeInformation() {
        employeeInfo.setOpacity(0);
        chooseProductWindow.setOpacity(0);

        clearEmployeeProductPage();//Extracted Method

        clearEmployeeInformationProductionPage();//Extracted Method
    }

    public void clearEmployeeProductPage() {
        productEmplBox.setText("Not Logged In");
        productionEmplBox.setText("Not Logged In");
        productionLogEmplBox.setText("Not Logged In");
    }

    public void clearEmployeeInformationProductionPage() {
        addEmployeeBttn.setText("Login");
        productionLogTextArea.clear();
        productionLogTextArea.setText(employeeError);
        existingProductWindow.setOpacity(0);
    }

    /**
     * Called when a new product is made and needs to be inserted into the database.
     *
     * @throws java.sql.SQLException thrown if there is an SQL error.
     */
    public void createProductObject() throws SQLException {

        String[] product = new String[3];
        product[0] = productNameWindow.getText();
        product[1] = itemTypeCB.getValue().getCode();
        product[2] = manufacturerNameWindow.getText();

        database.insertProductIntoDB(product);

        updateProductLine();

        clearProductPage();
    }

    /**
     * Reloads the database information for Product into the observable list
     */
    public void updateProductLine() throws SQLException {
        observableProductLine = database.loadProductList();
    }

    /**
     * Clears the Product Page fields
     */
    public void clearProductPage() {
        productNameWindow.clear();
        manufacturerNameWindow.clear();
    }

    /**
     * Displays the lamont.production log in the lamont.production log text area. Called every time
     * the lamont.production log is updated to update the window.
     *
     * @throws java.sql.SQLException thrown if there is an SQL error.
     */
    public void displayProductionRecordLog() throws SQLException {
        productionLogTextArea.clear();
        productionRecord = database.loadProductionRecordList(observableProductLine);
        audioCount = database.getAudioCount();
        visualCount = database.getVisualCount();
        for (ProductionRecord product : productionRecord) {
            productionLogTextArea.appendText(product.toString());
        }
    }

    /**
     * Sets up the boxes, called once at initialization.
     */
    public void initializeBoxes() {

        initializeItemTypeComboBox();

        initializeProductionRecordQuantityBox();

    }

    /**
     * Initializes the Product Page item type combo box at startup
     */
    public void initializeItemTypeComboBox() {
        itemTypeCB.getItems().setAll(ItemType.values());
        itemTypeCB.getSelectionModel().selectFirst();
    }

    /**
     * Initializes the Production Record Choose Quantity Box at start-up
     */
    public void initializeProductionRecordQuantityBox() {
        chooseQtyBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        chooseQtyBox.setEditable(true);
        chooseQtyBox.getSelectionModel().selectFirst();
    }

    /**
     * Sets up the product line table as well as the observable list. Called once at initialization,
     * currently starts with test data, will be replaced once database is running.
     */
    public void initializeProductLineTable() {
        productNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        productIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
        productTypeColumn.setCellValueFactory(new PropertyValueFactory("type"));
        productManufacturerColumn.setCellValueFactory(new PropertyValueFactory("manufacturer"));
        existingProductWindow.setItems(observableProductLine);
    }

    /**
     * Sets the product window to display the observable list. Called once at initialize then.
     * automatically updated with observable list.
     */
    public void initializeProductWindow() {
        chooseProductWindow.getItems().clear();
        chooseProductWindow.getItems().addAll(observableProductLine);
    }

    /**
     * Loads the Production Log Text area with the information from the ArrayList productionRecord.
     * showProduction should: populate the TextArea on the Production Log tab with the information
     * from the productionLog, replacing the productId with the product name, with one line for each
     * product produced
     */
    public void showProduction() {
        productionLogTextArea.clear();
        if (isLoggedIn()) {
            for (ProductionRecord record : productionRecord) {
                productionLogTextArea.appendText(formatProdRecForTextArea(record));
            }
        } else {
            productionLogTextArea.appendText(employeeError);
        }
    }

    /**
     * Takes a production record and formats it into a string to display nicely in the production
     * record log area
     *
     * @param record the record to be displayed
     * @return the formatted production record string.
     */
    public String formatProdRecForTextArea(ProductionRecord record) {
        return
                "Prod. Name: "
                        + record.getProduct().getName()
                        + " Product ID: "
                        + record.getProductID()
                        + " Serial Num: "
                        + record.getSerialNumber()
                        + " Date: "
                        + record.getProdDate()
                        + " UsrNme: "
                        + record.getEmployeeUsername()
                        + "\n";
    }

    /**
     * Called whenever a new production record is produced. Creates production record based on input
     * from the gui, then posts it to production log area.
     *
     * @throws java.sql.SQLException thrown if there is an SQL error.
     */
    public void createProductionRecordObject() throws SQLException {
        int qnty = Integer.parseInt(chooseQtyBox.getSelectionModel().getSelectedItem());
        for (int i = qnty; i > 0; i--) {
            Product tempProduct = chooseProductWindow.getSelectionModel().getSelectedItem();

            try {
                ProductionRecord tempRecord =
                        new ProductionRecord(tempProduct, currentEmployee.getUsername());

                productionRecord.add(tempRecord);

                insertProductionRecordIntoDB(tempRecord);//Extracted Method

            } catch (IllegalProductionRecordArgumentException e) {
                productionErrorBox.setText("Invalid Parameter");
            }
        }
    }

    /**
     * Inserts a new production record into the database if it does not already exist
     */
    void insertProductionRecordIntoDB(ProductionRecord record) throws SQLException {
        if (!(database.checkDbForProductionRecordName(record))) {
            String[] tempString = new String[4];
            tempString[0] = String.valueOf(0);
            tempString[1] = String.valueOf(record.getProduct().getId());
            tempString[2] = record.getSerialNumber();
            tempString[3] = currentEmployee.getUsername();
            database.addToProductionDbMethod(tempString);
            // System.out.println("Production Record Added to Database");
        }
    }

    /**
     * Called every time the program starts for the first time. Sets the tables buttons etc.
     *
     * @param location  Location.
     * @param resources ResourceBundle.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        currentEmployee = new Employee();//Refactored Method to create null instance

        initializeBoxes();

        try {
            observableProductLine = database.loadProductList();
        } catch (SQLException e) {
            System.out.println("Couldn't Load Product List");
        }
        try {
            displayProductionRecordLog();
        } catch (SQLException e) {
            System.out.println("Couldn't Load Production Record");
        }

        initializeProductLineTable();
        initializeProductWindow();
        showProduction();
    }

    /**
     * Verifies to make sure all of the necessary information to create the Product are present.
     *
     * @return Returns true if all of the parameters are met and returns false if any are not met.
     */
    public boolean verifyProduct() {
        productErrorWindow.setText(blankMessage);
        if (isLoggedIn()) {
            if (productNameContentsIsValid()) {
                if (manufacturerNameContentsIsValid()) {
                    return true;
                } else {
                    productErrorWindow.setText(addManufacturerError);
                }
            } else {
                productErrorWindow.setText(addNameError);
            }
        } else {
            productErrorWindow.setText(employeeError);
        }
        return false;
    }

    /**
     * Verifies the contents of the product name window and returns it's state.
     *
     * @return bool
     */
    public boolean productNameContentsIsValid() {
        return ((productNameWindow.getLength() > 0)
                && (productNameWindow.getText() != null)
                && !(productNameWindow.getText().equals(addNameError)));
    }

    /**
     * Verifies the contents of the manufacturer name window and returns it's state.
     */
    public boolean manufacturerNameContentsIsValid() {
        return ((manufacturerNameWindow.getLength() > 3)
                && (manufacturerNameWindow.getText() != null)
                && !(manufacturerNameWindow.getText().equals(addManufacturerError)));
    }

    /**
     * Verifies all necessary input and employee information needed to create a Production Record.
     * Then sets the errors and warning dependant on the missing information.
     *
     * @return returns true if all parameters are correct, false if any of the parameters are wrong.
     */
    public boolean verifyProductionRecord() {
        productionErrorBox.setText(blankMessage);
        if (isLoggedIn()) {
            if (verifyProductWindowContents()) {
                productionErrorBox.setText(blankMessage);
                try {
                    Integer.parseInt(chooseQtyBox.getSelectionModel().getSelectedItem());
                    return true;
                } catch (Exception e) {
                    productionErrorBox.setText("Please Enter a Valid Integer");
                }
            } else {
                productionErrorBox.setText("Please Select a Product");
            }
        } else {
            productionErrorBox.setText(employeeError);
        }
        return false;
    }

    /**
     * Verifies the product window contents and returns their status.
     */
    public boolean verifyProductWindowContents() {
        return (!(chooseProductWindow.getSelectionModel().isEmpty()));
    }

    /**
     * Verifies all necessary information needed to create an Employee. Then sets the errors and
     * warning dependant on the missing information.
     *
     * @return returns true if all parameters are correct, false if any of the parameters are wrong.
     */
    public boolean verifyEmployee() {
        employeeErrorBox.setText(blankMessage);
        if (verifyEmployeeNameContents()) {
            if (verifyEmployeePasswordContents()) {
                employeeErrorBox.setText(blankMessage);
                return true;
            } else {
                employeeErrorBox.setText(passwordError);
            }
        } else {
            employeeErrorBox.setText(employeeNameError);
        }
        return false;
    }

    /**
     * Verifies the employee page's name field
     *
     * @return bool
     */
    public boolean verifyEmployeeNameContents() {
        if ((employeeNameField.getLength() > 0)
                && (employeeNameField.getText() != null)
                && !(employeeNameField.getText().equals(employeeNameError))) {
            Boolean hasSpace = false;
            for (int i = 0; i < employeeNameField.getText().length(); i++) {
                if (employeeNameField.getText().charAt(i) == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifies the employee page's password field
     *
     * @return bool
     */
    public boolean verifyEmployeePasswordContents() {
        return ((employeePasswordField.getLength() > 0)
                && (employeePasswordField.getText() != null)
                && !(employeePasswordField.getText().equals(employeeNameError)));
    }

    /**
     * Verifies if there is an Employee Currently logged into the system.
     *
     * @return Returns true if there is an Employee logged in, false if there is not.
     */
    public boolean isLoggedIn() {
        if (!currentEmployee.isNullEmployee()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Clears all fields in the Product Line tab when called.
     */
    public void clearProductLineTab() {
        productNameWindow.clear();
        manufacturerNameWindow.clear();
        itemTypeCB.getSelectionModel().selectFirst();
    }

    /**
     * Clears all fields in the Product Tab when called.
     */
    public void clearProductTab() {
        chooseQtyBox.getSelectionModel().selectFirst();
        chooseProductWindow.getSelectionModel().clearSelection();
    }

    /**
     * Clears all fields in the Employee Tab when cleared.
     */
    public void clearEmployeeTab() {
        productErrorWindow.setText(blankMessage);
        productionErrorBox.setText(blankMessage);
        employeeNameField.clear();
        employeePasswordField.clear();
        addEmployeeBttn.setText("Log Out");
    }
}
