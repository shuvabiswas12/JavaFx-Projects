package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        System.out.println("Starts begins");

        primaryStage.setTitle("Life Cycle");

        StackPane paneRoot = new StackPane();
        Scene scene = new Scene(paneRoot, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("InIt begins");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Stop invoked");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
