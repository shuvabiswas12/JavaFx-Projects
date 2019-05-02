package sample.controller;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import sample.assests.AlertBox;
import sample.assests.StageLoader;
import sample.database.Const;
import sample.database.DatabaseHandler;
import sample.model.BookInformation;

public class BookListController {



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<BookInformation> tableView;

    @FXML
    private TableColumn<BookInformation, String> bookTitle_CLM;

    @FXML
    private TableColumn<BookInformation, String> bookId_CLM;

    @FXML
    private TableColumn<BookInformation, String> bookPublisher_CLM;

    @FXML
    private TableColumn<BookInformation, String> bookAuthor_CLM;

    @FXML
    private TableColumn<BookInformation, String> isAvail_CLM;


    private ObservableList<BookInformation> bookInformationObservableList;
    private DatabaseHandler databaseHandler;
    private ResultSet resultSet;

    @FXML
    void initialize() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        databaseHandler = DatabaseHandler.getDbInstance();
        bookInformationObservableList = FXCollections.observableArrayList();



        setDataIntoBookTable();

    }



    public void setDataIntoBookTable() throws SQLException {


        bookTitle_CLM.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("bookTitle"));
        bookId_CLM.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("bookId"));
        bookAuthor_CLM.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("bookAuthor"));
        bookPublisher_CLM.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("bookPublisher"));
        isAvail_CLM.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("val"));


        allBooksDetails();

    }

    private void allBooksDetails() throws SQLException {

        tableView.getItems().clear();

        String query = "SELECT * FROM "+ Const.BOOK_TABLE;


        resultSet = databaseHandler.exeQuery(query);
        while(resultSet.next()) {
            bookInformationObservableList.add(new BookInformation(resultSet.getString(Const.TITLE),
                    resultSet.getString(Const.ID),
                    resultSet.getString(Const.AUTHOR),
                    resultSet.getString(Const.PUBLISHER),
                    resultSet.getBoolean(Const.IS_AVAIL)));

        }

        tableView.setItems(null);
        tableView.setItems(bookInformationObservableList);
    }


    @FXML
    void deleteSeletedBooks(ActionEvent event) throws SQLException {

        BookInformation book = tableView.getSelectionModel().getSelectedItem();

        if (book == null) {
            AlertBox.errorAlert("Please, select a row ... ");
            return;
        }

        Alert alert = AlertBox.confirmationAlert("Are you want to sure to delete the book "+book.getBookTitle()+" ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            // progress here
            Boolean result = databaseHandler.deleteBook(book);

            if (result) {
                allBooksDetails();
                AlertBox.successAlert("Deletion successful !");
            }
        } else {
            AlertBox.errorAlert("Canceled deleting !");
        }


    }


    @FXML
    void editSeletedBooks(ActionEvent event) throws IOException {
        BookInformation bookInformation = tableView.getSelectionModel().getSelectedItem();
        if (bookInformation == null) {
            AlertBox.errorAlert("Please, select a row first");
            return;
        }


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/add_book_window.fxml"));
        Parent root = loader.load();


        AddBookController controller = loader.getController();
        controller.inflateUI(bookInformation);

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root));
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    refresh_menu_item(new ActionEvent());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @FXML
    void refresh_menu_item(ActionEvent event) throws SQLException {
        allBooksDetails();
    }
}
