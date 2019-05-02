package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;

import java.sql.ResultSet;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("view/addItem.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/sample/view/list.fxml"));
        primaryStage.setTitle("2DO");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        /*DatabaseHandler handler = new DatabaseHandler();
        ResultSet resultSet = handler.getTaskByUser(5);

        while (resultSet.next()) {
            System.out.print(resultSet.getString("tasks") + " ");
            System.out.println(resultSet.getString("description"));
        }*/


    }


    public static void main(String[] args) {
        launch(args);
    }
}
