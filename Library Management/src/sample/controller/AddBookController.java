package sample.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.assests.AlertBox;
import sample.assests.Shaker;
import sample.database.Const;
import sample.database.DatabaseHandler;
import sample.model.BookInformation;


public class AddBookController {




    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField title_ET;

    @FXML
    private TextField ID_ET;

    @FXML
    private TextField author_ET;

    @FXML
    private TextField publisher_ET;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;


    private DatabaseHandler databaseHandler;
    private ResultSet resultSet;
    private BookInformation bookInformation;
    private Boolean isInEditMode = Boolean.FALSE;


    @FXML
    void saveShortKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                if (isInEditMode) {
                    try {
                        editOperationHandle();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    save();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void initialize() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        databaseHandler = DatabaseHandler.getDbInstance();

        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (isInEditMode) {
                        System.out.println("In operation mode");
                        editOperationHandle();
                    } else {
                        System.out.println("In save mode");
                        save();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage cancelStage = (Stage) cancelBtn.getScene().getWindow();
                cancelStage.close();
            }
        });

    }


    private void save() throws SQLException {


        String bookTitle = title_ET.getText().toString().trim();
        String id = ID_ET.getText().toString().trim();
        String author = author_ET.getText().toString().trim();
        String publisher = publisher_ET.getText().toString().trim();

        if (!bookTitle.equals("") && !id.equals("") && !author.equals("") && !publisher.equals("")) {

            bookInformation = new BookInformation(bookTitle, id, author, publisher, true);

            String query = Const.insertBook(bookInformation);
            System.out.println(query);

            String searchQuery = "SELECT * FROM BOOK WHERE "+ Const.ID+" = '"+id+"' " ;
            resultSet = databaseHandler.exeQuery(searchQuery);


            if (resultSet.next()) {
                AlertBox.errorAlert("Duplicate Id Not Accepted");

            } else {

                if (databaseHandler.exeAction(query)) {
                    title_ET.clear();
                    ID_ET.clear();
                    author_ET.clear();
                    publisher_ET.clear();

                    AlertBox.successAlert("Success");
                } else {
                    AlertBox.errorAlert("Failed");
                }
            }

        } else {
            new Shaker(title_ET, ID_ET, author_ET, publisher_ET);

        }

    }

    public void inflateUI(BookInformation bookInformation) {
        title_ET.setText(bookInformation.getBookTitle());
        ID_ET.setText(bookInformation.getBookId());
        author_ET.setText(bookInformation.getBookAuthor());
        publisher_ET.setText(bookInformation.getBookPublisher());

        saveBtn.setText("Update");

        ID_ET.setEditable(false);
        isInEditMode = Boolean.TRUE;
    }

    private void editOperationHandle() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String bookTitle = title_ET.getText().toString().trim();
        String id = ID_ET.getText().toString().trim();
        String author = author_ET.getText().toString().trim();
        String publisher = publisher_ET.getText().toString().trim();

        if (!bookTitle.equals("") && !author.equals("") && !publisher.equals("")) {

            bookInformation = new BookInformation(bookTitle, id, author, publisher, true);

            boolean count = databaseHandler.updateBook(bookInformation);

            AlertBox.successAlert("Successfully Updated");

        }
    }
}
