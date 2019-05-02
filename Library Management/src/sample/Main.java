package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.assests.StageIcon;
import sample.database.DatabaseHandler;
import sample.model.Settings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class Main extends Application {

   /* static {

        try {
            DatabaseHandler.getDbInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
*/
    @Override
    public void start(Stage primaryStage) throws IOException {

        Settings settings = new Settings();

        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        primaryStage.setTitle("Home");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        StageIcon.addIcon(primaryStage);
        primaryStage.show();

        Path path = Paths.get("config.txt");

        if (Files.exists(path)) {
            Settings.getSettings();
        }

        if (Files.notExists(path)) {
            Settings.initConfig();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DatabaseHandler.getDbInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
