package sample.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import sample.database.Const;
import sample.database.DatabaseHandler;
import sample.model.Task;

public class ListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXListView<Task> listViewId;

    @FXML
    private JFXTextField taskNameId;

    @FXML
    private JFXTextField descriptionId;

    @FXML
    private ImageView refreshId;

    @FXML
    private JFXButton saveTaskButtonId;

    private ObservableList<Task> taskObservableList;
    private ObservableList<Task> refreshList;
    private DatabaseHandler databaseHandler;
    private List<Task> taskList;

    @FXML
    void initialize() throws SQLException {
        System.out.println("Initialized called");

       viewDataIntoListView();

       saveTaskButtonId.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               try {
                   addNewTask();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
       });
       refreshId.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               try {
                   refreshListView();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
       });

    }

    private void viewDataIntoListView() throws SQLException {

        taskObservableList = FXCollections.<Task>observableArrayList();
        databaseHandler = new DatabaseHandler();
        Task task = null;

        ResultSet resultSet = databaseHandler.getTaskByUser(AddItemFormController.userId);

        while(resultSet.next()) {
            task = new Task();
            task.setTaskId(resultSet.getInt(Const.TASK_ID));
            task.setTask(resultSet.getString(Const.TASK));
            task.setDescription(resultSet.getString(Const.DESCRIPTION));
            task.setDateCreated(resultSet.getTimestamp(Const.TASK_DATE_CREATED));

            taskObservableList.addAll(task);

        }

        listViewId.setItems(taskObservableList);
        listViewId.setCellFactory(CellController -> new CellController());

    }

    public void refreshListView() throws SQLException {

        refreshList = FXCollections.<Task>observableArrayList();
        databaseHandler = new DatabaseHandler();
        Task task = null;

        ResultSet updateResultSet = databaseHandler.getTaskByUser(AddItemFormController.userId);

        while(updateResultSet.next()) {
            task = new Task();
            task.setTaskId(updateResultSet.getInt(Const.TASK_ID));
            task.setTask(updateResultSet.getString(Const.TASK));
            task.setDescription(updateResultSet.getString(Const.DESCRIPTION));
            task.setDateCreated(updateResultSet.getTimestamp(Const.TASK_DATE_CREATED));
            refreshList.addAll(task);
        }

        listViewId.setItems(refreshList);
        listViewId.setCellFactory(CellController -> new CellController());
    }

    private void addNewTask() throws SQLException {
        if (!taskNameId.getText().toString().equals("") && !descriptionId.getText().toString().equals("")) {

            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());

            Task newTask = new Task();
            newTask.setUserId(AddItemFormController.userId);
            newTask.setTask(taskNameId.getText().toString());
            newTask.setDescription(descriptionId.getText().toString());
            newTask.setDateCreated(timestamp);

            databaseHandler = new DatabaseHandler();
            databaseHandler.insertTask(newTask);

            taskNameId.setText("");
            descriptionId.setText("");

            viewDataIntoListView();

        } else {

        }

    }
}
