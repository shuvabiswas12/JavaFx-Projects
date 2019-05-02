package sample;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


   /*

        // Flow Pane -->> //

        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10, 20, 15, 10));
        flowPane.setHgap(5);
        flowPane.setVgap(5);

        flowPane.getChildren().addAll(new Label("First Name "), new TextField(), new Label("MI: "));
        TextField textField = new TextField();
        textField.setPrefColumnCount(1);
        flowPane.getChildren().addAll(textField, new Label("Last Name "), new TextField());

        Scene scene = new Scene(flowPane, 200, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sample");
        primaryStage.show();


     */


    /*
        // Grid Pane -->> //

        GridPane gridPane = new GridPane();
        Stage stage1 = new Stage();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10, 20, 15, 10));
        gridPane.setAlignment(Pos.CENTER);


        gridPane.add(new Label("First Name "), 0, 0);
        gridPane.add(new TextField(), 1, 0);
        gridPane.add(new Label("Last Name "), 0, 1);
        gridPane.add(new TextField(), 1, 1);
        gridPane.add(new Label("MI: "), 0, 2);
        gridPane.add(new TextField(), 1, 2);
        Button btn = new Button("OK");
        gridPane.add(btn, 1, 3);
        GridPane.setHalignment(btn, HPos.RIGHT);

        Scene scene = new Scene(gridPane, 250, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Grid Pane");
        primaryStage.show();

        */

        Stage stage3 = new Stage();
        BorderPane borderPane = new BorderPane();

        borderPane.setTop(new CustomPane("Top"));
        borderPane.setBottom(new CustomPane("Bottom"));
        borderPane.setCenter(new CustomPane("Center"));
        borderPane.setRight(new CustomPane("Right"));
        borderPane.setLeft(new CustomPane("Left"));

        Scene scene3 = new Scene(borderPane, 250, 400);
        primaryStage.setTitle("Border Pane");
        primaryStage.setScene(scene3);
        primaryStage.show();


    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


// border pane
class CustomPane extends StackPane {
    public CustomPane(String title) {
        getChildren().add(new Label(title));
        setStyle("-fx-border-color: red");
        setPadding(new Insets(10, 20, 10, 20));
    }
}
