package sample.controller;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListCell;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.model.Task;

public class CellController extends ListCell<Task>  /*JFXListCell<Task>*/ {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private Label taskNameLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ImageView deleteButton;

    @FXML
    private ImageView updateId;

    @FXML
    private Label dateTimeLabel;

    private FXMLLoader loader;

    @FXML
    void initialize() {


    }

    @Override
    protected void updateItem(Task taskItem, boolean empty) {
        super.updateItem(taskItem, empty);

        DatabaseHandler databaseHandler = new DatabaseHandler();

        if(taskItem == null || empty == true) {
            setText(null);
            setGraphic(null);
        } else {

                loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/cell.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                taskNameLabel.setText(taskItem.getTask());
                descriptionLabel.setText(taskItem.getDescription());
                dateTimeLabel.setText(String.valueOf(taskItem.getDateCreated()));


                deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        databaseHandler.deleteTask(AddItemFormController.userId, taskItem.getTaskIdI());
                        getListView().getItems().remove(getItem());
                    }
                });

                setText(null);
                setGraphic(rootAnchorPane);

                updateId.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/sample/view/update.fxml"));
                        try {
                            Parent root = loader.load();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.setTitle("Update Task");
                            stage.setMaxWidth(400);
                            stage.setResizable(false);
                            stage.setX(600);
                            stage.setY(210);

                            UpdateTaskController updateTaskController = loader.getController();
                            updateTaskController.setUpdateTaskId(taskItem.getTask());
                            updateTaskController.setUpdateDescriptionId(taskItem.getDescription());

                            updateTaskController.updateTaskButton.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    String task = updateTaskController.getUpdateTask().toString();
                                    String description = updateTaskController.getDescription().toString();
                                    Calendar calendar = Calendar.getInstance();
                                    java.sql.Timestamp date = new java.sql.Timestamp(calendar.getTimeInMillis());
                                    int task_id = taskItem.getTaskIdI();
                                    try {
                                        databaseHandler.updateTask(date,description, task, task_id);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                            stage.showAndWait();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }




                    }
                });
            }
        }



}
