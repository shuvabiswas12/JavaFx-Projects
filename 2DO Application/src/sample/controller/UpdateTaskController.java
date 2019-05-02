package sample.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import sample.database.DatabaseHandler;


public class UpdateTaskController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField updateTaskId;

    @FXML
    private JFXTextField updateDescriptionId;

    @FXML
    public JFXButton updateTaskButton;

    DatabaseHandler databaseHandler;

    public void setUpdateTaskId(String text) {
        this.updateTaskId.setText(text);
    }

    public void setUpdateDescriptionId(String text) {
        this.updateDescriptionId.setText(text);
    }

    public String getUpdateTask() {
        return updateTaskId.getText().trim();
    }

    public String getDescription() {
        return updateDescriptionId.getText().trim();
    }

    @FXML
    void initialize() {

    }
}
