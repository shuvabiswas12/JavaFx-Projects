package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.model.Task;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddItemFormController implements Initializable {

    public static int userId;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField taskID;

    @FXML
    private JFXTextField descriptionID;

    @FXML
    private JFXButton saveTaskButtonID;

    @FXML
    private Label successTask;

    @FXML
    private JFXButton todoTask;

    @FXML
    void initialize() {
        assert taskID != null : "fx:id=\"taskID\" was not injected: check your FXML file 'addItemForm.fxml'.";
        assert descriptionID != null : "fx:id=\"descriptionID\" was not injected: check your FXML file 'addItemForm.fxml'.";
        assert saveTaskButtonID != null : "fx:id=\"saveTaskButtonID\" was not injected: check your FXML file 'addItemForm.fxml'.";
        assert successTask != null : "fx:id=\"successTask\" was not injected: check your FXML file 'addItemForm.fxml'.";
        assert todoTask != null : "fx:id=\"todoTask\" was not injected: check your FXML file 'addItemForm.fxml'.";

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        saveTaskButtonID.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                DatabaseHandler databaseHandler = new DatabaseHandler();

                Calendar calendar = Calendar.getInstance();
                java.sql.Timestamp dateCreated = new java.sql.Timestamp(calendar.getTimeInMillis());

                String taskString = taskID.getText().toString();
                String description = descriptionID.getText().toString();

                Task task = new Task(dateCreated, description, taskString);
                task.setUserId(getUserId());

                databaseHandler.insertTask(task);


                successTask.setVisible(true);
                todoTask.setVisible(true);
                int taskNumber = 0;
                try {
                    taskNumber = databaseHandler.getAllTask(userId);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                todoTask.setText("2Do's (" + String.valueOf(taskNumber) +")");

                taskID.setText("");
                descriptionID.setText("");
            }
        });

        todoTask.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                saveTaskButtonID.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/list.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
        });

    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("userId = " +this.userId);
    }

    public static int getUserId() {
        return userId;
    }
}
