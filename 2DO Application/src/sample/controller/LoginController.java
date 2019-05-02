package sample.controller;


import com.jfoenix.controls.JFXButton;
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

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField userNameId;

    @FXML
    private JFXPasswordField passwordId;

    @FXML
    private JFXButton loginBtnId;

    @FXML
    private JFXButton signUpBtnId;

    private DatabaseHandler databaseHandler;

    private int user_id;

    @FXML
    void initialize() {
        assert userNameId != null : "fx:id=\"userNameId\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordId != null : "fx:id=\"passwordId\" was not injected: check your FXML file 'login.fxml'.";
        assert loginBtnId != null : "fx:id=\"loginBtnId\" was not injected: check your FXML file 'login.fxml'.";
        assert signUpBtnId != null : "fx:id=\"signUpBtnId\" was not injected: check your FXML file 'login.fxml'.";

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        signUpBtnId.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // it takes to signup screen and hide the login screen

                signUpBtnId.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/signUp.fxml"));
                try {
                    Parent root = loader.load();  // we can write here as loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Sign Up");
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });


        databaseHandler = new DatabaseHandler();

        loginBtnId.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             if (!userNameId.getText().equals("") && !passwordId.getText().equals("")) {
                 User user = new User();
                 String userNameLogIn = userNameId.getText().toString().trim();
                 String passwordLogIn = passwordId.getText().toString().trim();

                 user.setUserName(userNameLogIn);
                 user.setPassword(passwordLogIn);

                 ResultSet userRow = databaseHandler.getUser(user);

                 int counter = 0;
                 try {
                     while(userRow.next()) {
                         counter++;
                         user_id = userRow.getInt("user_id");
                     }

                     if (counter == 1) {
                         loginBtnId.getScene().getWindow().hide();
                         FXMLLoader loader = new FXMLLoader();
                         loader.setLocation(getClass().getResource("/sample/view/addItem.fxml"));
                         try {

                             Parent root = loader.load();
                             Stage stage = new Stage();
                             stage.setScene(new Scene(root));
                             stage.setTitle("Add Items");

                             stage.show();


                             AddItemController addItemController = loader.getController();
                             addItemController.setUser_Id(user_id);

                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                     }
                     else {
                         Shaker userNameShaker = new Shaker(userNameId);
                         Shaker passwordShaker = new Shaker(passwordId);
                         userNameShaker.shake();
                         passwordShaker.shake();
                     }

                 } catch (SQLException e) {
                     e.printStackTrace();
                 }

             }
             else {
                 Shaker shaker = new Shaker(loginBtnId);
                 shaker.shake();
             }
            }
        });

    }
}
