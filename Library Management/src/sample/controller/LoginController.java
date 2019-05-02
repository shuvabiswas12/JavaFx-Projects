package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.assests.AlertBox;
import sample.assests.Shaker;
import sample.assests.StageLoader;
import sample.model.Settings;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField userName_ID;

    @FXML
    private JFXPasswordField password_Id;

    @FXML
    private JFXButton login_Btn;

    @FXML
    private JFXButton cancel_Btn;

    private StageLoader stageLoader;


    @FXML
    void shortKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            login();
        }
    }


    @FXML
    void signUpBtnAction(ActionEvent event) {
        try {
            stageLoader.open("/sample/view/signUp.fxml", "Signup");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void initialize() {

        stageLoader = new StageLoader();



        login_Btn.setOnAction(e -> {

           login();


        });


        cancel_Btn.setOnAction(e -> {
            Stage stage = (Stage) cancel_Btn.getScene().getWindow();
            stage.close();
        });

    }

    private void login() {

        Settings settings = Settings.getSettings();

        String uName = userName_ID.getText().toString().trim();
        String pass = password_Id.getText().toString().trim();

        if (uName.isEmpty() && pass.isEmpty()) {

            Shaker shaker = new Shaker(userName_ID, password_Id);
            shaker.shake();

        } else {
            if (uName.equals(settings.getuName()) && pass.equals(settings.getuPassword())) {
                try {
                    stageLoader.open("/sample/view/home.fxml", "Home");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            } else {

                AlertBox.errorAlert("Username & Password doesn't match. \n");
                userName_ID.clear();
                password_Id.clear();

            }
        }

    }
}
