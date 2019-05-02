package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.assests.Shaker;
import sample.assests.StageLoader;
import sample.database.Const;
import sample.database.DatabaseHandler;
import sample.model.Membar;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MembarListController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Membar> tableView;

    @FXML
    private TableColumn<Membar, String> membar_CLM;

    @FXML
    private TableColumn<Membar, String> id_CLM;

    @FXML
    private TableColumn<Membar, String> mobile_CLM;

    @FXML
    private TableColumn<Membar, String> email_CLM;

    private ResultSet resultSet;
    private DatabaseHandler databaseHandler;
    private ObservableList<Membar> membarObservableList, selectedMember, allMember;

    @FXML
    void initialize() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        databaseHandler = DatabaseHandler.getDbInstance();
        membarObservableList = FXCollections.observableArrayList();
        allMember = FXCollections.observableArrayList();
        selectedMember = FXCollections.observableArrayList();

        setDataIntoMembarTable();

    }

    private void setDataIntoMembarTable() throws SQLException {

        membar_CLM.setCellValueFactory(new PropertyValueFactory<Membar, String>("name"));
        id_CLM.setCellValueFactory(new PropertyValueFactory<Membar, String>("id"));
        mobile_CLM.setCellValueFactory(new PropertyValueFactory<Membar, String>("mobile"));
        email_CLM.setCellValueFactory(new PropertyValueFactory<Membar, String>("email"));


        String query = "SELECT * FROM "+ Const.MEMBAR_TABLE;


        resultSet = databaseHandler.exeQuery(query);
        while(resultSet.next()) {

            membarObservableList.add(new Membar(resultSet.getString(Const.NAME),
                    resultSet.getString(Const.ID),
                    resultSet.getString(Const.MOBILE),
                    resultSet.getString(Const.EMAIL)));

        }



        tableView.setItems(null);
        tableView.setItems(membarObservableList);

    }








}
