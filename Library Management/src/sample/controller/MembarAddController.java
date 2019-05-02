package sample.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.assests.AlertBox;
import sample.assests.Shaker;
import sample.database.Const;
import sample.database.DatabaseHandler;
import sample.model.Membar;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MembarAddController implements Initializable{

    @FXML
    private JFXTextField membar_name_ET;

    @FXML
    private JFXTextField membar_ID_ET;

    @FXML
    private JFXTextField mobile_ET;

    @FXML
    private JFXTextField email_ET;

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    private Membar membar;
    private DatabaseHandler databaseHandler;
    private ResultSet resultSet;



    /**
     *
     * checking the validation of email address
     *
     * */



//    String another_pattern: "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,6}$";

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^(.+)@(.+)$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    @FXML
    void saveShortKey(KeyEvent event) throws SQLException {

        if (event.getCode() == KeyCode.ENTER) {
            add();
        }

    }

    private void add() throws SQLException {

        String name = membar_name_ET.getText().toString().trim();
        String id = membar_ID_ET.getText().toString().trim();
        String mobile = mobile_ET.getText().toString().trim();
        String email = email_ET.getText().toString().trim();



        if (!name.equals("") && !id.equals("") && !mobile.equals("") && !email.equals("")) {



            /**
             *
             * Essentially what it does is:-

            1. From first character position ^
            2. Look ahead and see if the first character is a 0 or not (?=(?:[0-0]){1})
            3. Then see if there are a total of 11 digits (?=[0-9]{11})
            4. If the above conditions is a match then mark this as matched.*

             **/

            if (mobile.matches("^(?=(?:[0-0]){1})(?=[0-9]{11}).*")) {  /// Checking mobile is validate or not ...

                //System.out.println("Valid phone number!");

                Boolean value = validate(email);

                if (value) {


                    membar = new Membar(name, id, mobile, email);

                    String query = Const.insertMembar(membar);
                    System.out.println(query);

                    resultSet = databaseHandler.exeQuery("SELECT * FROM "+Const.MEMBAR_TABLE+" WHERE "+Const.ID+" = '"+id+"'");
                    if (resultSet.next()) {
                        AlertBox.errorAlert("Duplicate Id Not Accepted");
                    } else {
                        if (databaseHandler.exeAction(query)) {

                            membar_name_ET.clear();
                            membar_ID_ET.clear();
                            mobile_ET.clear();
                            email_ET.clear();
                        }
                    }

                } else {
                    AlertBox.errorAlert("Correct the email address.");
                }




            }
            else {
                AlertBox.errorAlert("Correct your mobile number");
            }

        } else {
            new Shaker(membar_name_ET).shake();
            new Shaker(membar_ID_ET).shake();
            new Shaker(mobile_ET).shake();
            new Shaker(email_ET).shake();
        }

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            databaseHandler = DatabaseHandler.getDbInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) cancelBtn.getScene().getWindow();
                stage.close();
            }
        });

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    add();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
