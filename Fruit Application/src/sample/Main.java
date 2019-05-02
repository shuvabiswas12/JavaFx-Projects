package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler {

    private CheckBox bananaCB;
    private CheckBox papayaCB;
    private CheckBox coconutCB;
    private CheckBox mangoCB;
    private Label myText;
    private Label respose;
    private Label selected;
    private String fruit;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Fruit Application");

        myText = new Label();
        myText.setText("Which fruits do you like?");

        respose = new Label();
        selected = new Label();


        bananaCB = new CheckBox();
        bananaCB.setText("Banana");
        papayaCB = new CheckBox();
        papayaCB.setText("Papaya");
        coconutCB = new CheckBox();
        coconutCB.setText("Coconut");
        mangoCB = new CheckBox();
        mangoCB.setText("mango");

        // register of all our checkbox
        bananaCB.setOnAction(this);
        papayaCB.setOnAction(this);
        coconutCB.setOnAction(this);
        mangoCB.setOnAction(this);


        FlowPane myFlowPaneroot = new FlowPane(Orientation.VERTICAL, 5, 5);
        myFlowPaneroot.setAlignment(Pos.CENTER);
        myFlowPaneroot.getChildren().add(myText);
        myFlowPaneroot.getChildren().addAll(bananaCB, papayaCB, coconutCB, mangoCB, respose, selected);



        Scene scene = new Scene(myFlowPaneroot, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        showAll();

    }

    private void showAll() {
        fruit = "";

        if (bananaCB.isSelected()) fruit = " Banana";
        if (coconutCB.isSelected()) fruit += " Coconut";
        if (mangoCB.isSelected()) fruit +=" Mango";
        if (papayaCB.isSelected()) fruit +=" Papaya";

        selected.setText("Selected fruit is " + fruit);
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(Event event) {



        Object fruitChecked = event.getSource();

        if (bananaCB.equals(fruitChecked)) {
            if (bananaCB.isSelected()) {
                respose.setText("Banana is selected");
            }
            else {
                respose.setText("Banana is cleared!");
            }
            showAll();
        }

        if (mangoCB.equals(fruitChecked)) {
            if (mangoCB.isSelected()) {
                respose.setText("Mango is Selected");
            }
            else {
                respose.setText("Mango is cleared");
            }
            showAll();
        }
        if (papayaCB.equals(fruitChecked)) {
            if (papayaCB.isSelected()) {
                respose.setText("Papaya is selected");
            }
            else {
                respose.setText("Papaya is cleared!");
            }
            showAll();
        }

        if (coconutCB.equals(fruitChecked)) {
            if (coconutCB.isSelected()) {
                respose.setText("Coconut is Selected");
            }
            else {
                respose.setText("Coconut is cleared");
            }
            showAll();
        }
    }
}
