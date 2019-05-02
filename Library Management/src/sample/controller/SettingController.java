package sample.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.assests.AlertBox;
import sample.assests.Shaker;
import sample.model.Settings;

public class SettingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField no_of_days_ET;

    @FXML
    private JFXTextField fine_Per_Day_ET;

    @FXML
    private JFXTextField userName_ET;

    @FXML
    private JFXPasswordField password_ET;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;
    private Shaker shaker;
    private Settings settings;

    @FXML
    void saveShortKey(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER ) {
            save();
        }

    }

    private void save() {
        shaker = new Shaker(userName_ET, password_ET, no_of_days_ET, fine_Per_Day_ET);

        if (!userName_ET.equals("") && !password_ET.equals("") && !no_of_days_ET.equals("") && !fine_Per_Day_ET.equals("")) {

            String uName = userName_ET.getText().toString().trim();
            String uPass = password_ET.getText().toString().trim();
            int uNoDays = Integer.parseInt(no_of_days_ET.getText().toString().trim());
            float uFine = Float.parseFloat(fine_Per_Day_ET.getText().toString().trim());

            settings.setNo_of_days(uNoDays);
            settings.setuFine(uFine);
            settings.setuName(uName);
            settings.setuPassword(uPass);

            Settings.setConfig(settings);

            System.out.println(settings.getuName() + settings.getuPassword());

            AlertBox.confirmationAlert("Settings Successfully updated");
            saveBtn.getScene().getWindow().hide();

            } else {
            shaker.shake();

        }

    }

    @FXML
    void initialize() {

        settings = Settings.getSettings();

        userName_ET.setText(settings.getuName());
        password_ET.setText(settings.getuPassword());
        no_of_days_ET.setText(String.valueOf(settings.getNo_of_days()));
        fine_Per_Day_ET.setText(String.valueOf(settings.getuFine()));



        saveBtn.setOnAction(e -> {
            save();
        });


        cancelBtn.setOnAction(e ->{
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        });



    }
}
