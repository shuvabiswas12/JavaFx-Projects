package sample;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.controller.SplashScreenController;

public class Main extends Application {

    SplashScreenController splashScreenController = new SplashScreenController();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/demo.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("Application");
        primaryStage.setScene(scene);
        splashScreenController.showWindow();



       PauseTransition splashScreenDelay = new PauseTransition(Duration.seconds(1.7));
        splashScreenDelay.setOnFinished(f -> {
            primaryStage.show();
            splashScreenController.hideWindow();
        });
        splashScreenDelay.playFromStart();


    }




    public static void main(String[] args) {
        launch(args);
    }
}
