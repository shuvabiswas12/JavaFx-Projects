package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashScreenController /*extends StackPane*/ {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label rootAnchorPane;


    @FXML
    private ImageView imgViewId;

    //private Logger logger = Logger.getLogger(getClass().getName());

    private Stage stage = new Stage();

    public SplashScreenController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/splash.fxml"));
        loader.setController(this);
        //loader.setRoot(this);
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Splash Screen");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));

    }

    public void showWindow() {
        stage.show();
    }

    public void hideWindow() {
        stage.hide();
    }



    @FXML
    void initialize() {
        imgViewId.setFitHeight(getScreenwidth() / 2.5);
    }

    public static double getScreenwidth() {
        return Screen.getPrimary().getBounds().getWidth();
    }

    public static double getScreenHeight() {
        return Screen.getPrimary().getBounds().getHeight();
    }

    public static double getVisualScreenWidth() {
        return Screen.getPrimary().getVisualBounds().getWidth();
    }

    public static double getVisualScreenHeight() {
        return Screen.getPrimary().getVisualBounds().getHeight();
    }


}
