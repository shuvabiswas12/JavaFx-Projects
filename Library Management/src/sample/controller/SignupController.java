package sample.controller;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;

import javafx.scene.layout.StackPane;

import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sample.assests.CustomDialogueBox;
import sample.model.Settings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupController {

    @FXML
    private StackPane stackpane;

    @FXML
    private JFXTextField nameId;

    @FXML
    private JFXPasswordField passwordId;

    @FXML
    private JFXTextField emailId;

    @FXML
    private JFXTextField locationId;

    private Gson gson;
    private CustomDialogueBox customDialogueBox;
    private Settings settings;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^(.+)@(.+)$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    @FXML
    void signupBtnAction(ActionEvent event) {

        String name = nameId.getText().toString().trim();
        String pass = passwordId.getText().toString().trim();
        String loc = locationId.getText().toString().trim();
        String email = emailId.getText().toString().trim();

        if (!name.isEmpty() && !pass.isEmpty() && !loc.isEmpty() && !email.isEmpty()) {

            if (validate(email)) {

                settings.setuName(name);
                settings.setuPassword(pass);
                settings.setEmail(email);
                settings.setLocation(loc);

                Settings.setConfig(settings);

                Notifications notifications = Notifications.create()
                        .title("Confirmation massage!")
                        .text("Generate Successful !")
                        .position(Pos.BOTTOM_RIGHT)
                        .hideAfter(Duration.seconds(5))
                        .graphic(null);

                notifications.darkStyle();
                notifications.showConfirm();


            } else {
                customDialogueBox.materialDialogue("Email Error!", "Correct your email format", stackpane);
            }

        } else {
            customDialogueBox.materialDialogue("Error!", "Field can't be empty.", stackpane);
        }


    }

    @FXML
    void initialize() {

        gson = new Gson();
        customDialogueBox = new CustomDialogueBox();
        settings = new Settings();

    }
}
