package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.animation.Shaker;
import sample.database.DatabaseHandler;
import sample.model.User;
import sun.security.provider.SHA;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField firstNameSignUpId;

    @FXML
    private JFXTextField lastNameSignUpId;

    @FXML
    private JFXTextField userNameSignUpId;

    @FXML
    private JFXPasswordField passwordSignUpId;


    @FXML
    private JFXCheckBox maleCbId;

    @FXML
    private JFXCheckBox femaleCbId;

    @FXML
    private JFXButton signUpButtonId;

    @FXML
    private JFXTextField locationSignUpId;

    @FXML
    void initialize() {
        assert firstNameSignUpId != null : "fx:id=\"firstNameSignUpId\" was not injected: check your FXML file 'signUp.fxml'.";
        assert lastNameSignUpId != null : "fx:id=\"lastNameSignUpId\" was not injected: check your FXML file 'signUp.fxml'.";
        assert userNameSignUpId != null : "fx:id=\"userNameSignUpId\" was not injected: check your FXML file 'signUp.fxml'.";
        assert passwordSignUpId != null : "fx:id=\"passwordSignUpId\" was not injected: check your FXML file 'signUp.fxml'.";
        assert maleCbId != null : "fx:id=\"maleCbId\" was not injected: check your FXML file 'signUp.fxml'.";
        assert femaleCbId != null : "fx:id=\"femaleCbId\" was not injected: check your FXML file 'signUp.fxml'.";
        assert signUpButtonId != null : "fx:id=\"signUpButtonId\" was not injected: check your FXML file 'signUp.fxml'.";
        assert locationSignUpId != null : "fx:id=\"locationSignUpId\" was not injected: check your FXML file 'signUp.fxml'.";

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        signUpButtonId.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

               if (!firstNameSignUpId.getText().isEmpty() && !lastNameSignUpId.getText().isEmpty() && !userNameSignUpId.getText().isEmpty() &&
                        !passwordSignUpId.getText().isEmpty() && !locationSignUpId.getText().isEmpty() || (maleCbId.isSelected() || femaleCbId.isSelected())) {
                   try {
                       createUser();
                   } catch (SQLException e) {
                       e.printStackTrace();
                   } catch (ClassNotFoundException e) {
                       e.printStackTrace();
                   }

                   signUpButtonId.getScene().getWindow().hide();
                   FXMLLoader loader = new FXMLLoader();
                   loader.setLocation(getClass().getResource("/sample/view/login.fxml"));
                   try {
                       Parent root = loader.load();
                       Stage stage = new Stage();
                       stage.setScene(new Scene(root));
                       stage.setTitle("log In");
                       stage.show();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
               else {
                   Shaker shaker = new Shaker(signUpButtonId);
                   shaker.shake();
               }
            }
        });

    }

    public void createUser() throws SQLException, ClassNotFoundException {

        DatabaseHandler handler = new DatabaseHandler();

        String firstName = firstNameSignUpId.getText();
        String lastName = lastNameSignUpId.getText();
        String password = passwordSignUpId.getText();
        String location = locationSignUpId.getText();
        String userName = userNameSignUpId.getText();

        String gender;

        if (maleCbId.isSelected()) {
            gender = "male";
        }
        else {
            gender = "female";
        }

        User newUser = new User(firstName, lastName, userName, password, location, gender);
        handler.signUpUser(newUser);

    }
}
