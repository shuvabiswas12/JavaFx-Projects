package sample.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import sample.assests.AlertBox;
import sample.assests.StageLoader;
import sample.database.Const;
import sample.database.DatabaseHandler;
import sample.model.Issue;

public class HomeController {

    @FXML
    private Tab book_Issue_ID;

    @FXML
    private TextField bookInput;

    @FXML
    private TextField memberInputid;


    @FXML
    private Text bookName;

    @FXML
    private Text author;

    @FXML
    private Text bookStatus;

    @FXML
    private Text memberName;

    @FXML
    private Text contact;

    @FXML
    private HBox button_info;

    @FXML
    private Button issuesBtn;

    @FXML
    private Tab submission_ID;

    @FXML
    private JFXTextField bookID;

    @FXML
    private ListView<String> listView;

    private StageLoader stageLoader;
    private DatabaseHandler databaseHandler;
    private ObservableList<String> observableList;
    private boolean readyForSubmission = false;

    @FXML
    void aboutAction(ActionEvent event) throws IOException {

        stageLoader.open("/sample/view/about.fxml", "About");

    }

    @FXML
    void addBooksAction(ActionEvent event) {
        String url = "/sample/view/add_book_window.fxml";
        try {
            stageLoader.open(url, "Add Books");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addMembersAction(ActionEvent event) {
        String url = "/sample/view/membar_add_window.fxml";
        try {
            stageLoader.open(url, "Add Member");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void preferencesAction(ActionEvent event) {
        try {
            stageLoader.open("/sample/view/setting_window.fxml", "Settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void viewBookActions(ActionEvent event) {
        String url = "/sample/view/book_list.fxml";
        try {
            stageLoader.open(url, "Books");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void viewMembersAction(ActionEvent event) {
        String url = "/sample/view/membar_list.fxml";
        try {
            stageLoader.open(url, "Members");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void addBooks(ActionEvent event) throws IOException {
        String url = "/sample/view/add_book_window.fxml";
        stageLoader.open(url, "Add Books");

    }

    @FXML
    void addMembers(ActionEvent event) throws IOException {
        String url = "/sample/view/membar_add_window.fxml";
        stageLoader.open(url, "Add Member");
    }

    @FXML
    void settings(ActionEvent event) throws IOException {

        stageLoader.open("/sample/view/setting_window.fxml", "Settings");

    }

    @FXML
    void viewBooks(ActionEvent event) throws IOException {
        String url = "/sample/view/book_list.fxml";
        stageLoader.open(url, "Books");

    }

    @FXML
    void viewMembers(ActionEvent event) throws IOException {
        String url = "/sample/view/membar_list.fxml";
        stageLoader.open(url, "Members");


    }

    @FXML
    void loadBookInfo(ActionEvent event) throws SQLException {

        String id = bookInput.getText().toString().trim();
        String query = "SELECT * FROM "+ Const.BOOK_TABLE+" WHERE "+Const.ID+" = '"+id+"' ";

        ResultSet resultSet = databaseHandler.exeQuery(query);

        Boolean flag = false;

        while (resultSet.next()) {

            String bName = resultSet.getString(Const.TITLE);
            String bAuthor = resultSet.getString(Const.AUTHOR);
            boolean bStatus = resultSet.getBoolean(Const.IS_AVAIL);

            bookName.setOpacity(1);
            author.setOpacity(1);

            bookName.setText(bName);
            author.setText(bAuthor);
            String status = (bStatus)?"Available" : "Not Available";

            bookStatus.setText(status);

            flag = true;

        }

        if (!flag) {
            bookName.setOpacity(0);
            author.setOpacity(0);
            bookStatus.setText("Not Available");
        }


    }

    @FXML
    void loadMemberInfo(ActionEvent event) throws SQLException {

        contact.setOpacity(1);

        String mId = memberInputid.getText().toString().trim();
        String query = "SELECT * FROM "+Const.MEMBAR_TABLE+" WHERE "+Const.ID+" = '"+mId+"' ";
        ResultSet resultSet = databaseHandler.exeQuery(query);

        boolean flag = false;

        while(resultSet.next()) {
            String mName = resultSet.getString(Const.NAME);
            String mContact = resultSet.getString(Const.MOBILE);

            memberName.setText(mName);
            contact.setText(mContact);

            flag = true;

        }

        if (!flag) {
            memberName.setText("Not Available");
            contact.setOpacity(0);
        }

    }

    @FXML
    void submissionBtnAction(ActionEvent event) {
        String id = bookID.getText().toString().trim();

       if (id.equals("")) {
           AlertBox.errorAlert("Please, enter a book first ... ");
       } else {
           if (!readyForSubmission) {
               AlertBox.errorAlert("Please, Load book info first ... ");
               return;
           } else {



               String contentText = "Are you sure want to return the book ?";


               // alert box button confirmation process here .......

               Alert alert = AlertBox.confirmationAlert(contentText);
               Optional<ButtonType> response = alert.showAndWait();

               if (response.get() == ButtonType.OK) {



                   String query = "DELETE FROM "+Const.ISSUE_TABLE+" WHERE "+Const.BOOK_ID+" = '"+id+"' ";
                   String query2 = "UPDATE "+Const.BOOK_TABLE+" SET "+Const.IS_AVAIL+" = true WHERE "+Const.ID+" = '"+id+"' ";

                   if (databaseHandler.exeAction(query) && databaseHandler.exeAction(query2)) {
                       AlertBox.successAlert("Book has been submitted");
                       listView.getItems().clear();
                       bookID.clear();
                   } else {
                       AlertBox.errorAlert("Submission has Failed! ");
                   }




               } else {
                   AlertBox.successAlert("Canceled");
               }









           }
       }
    }

    @FXML
    void rewnewBtnAction(ActionEvent event) {

        String id = bookID.getText().toString().trim();

        if (id.equals("")) {
            AlertBox.errorAlert("Please, enter a book first ... ");
        } else {
            if (!readyForSubmission) {
                AlertBox.errorAlert("Please, Load book info first ... ");
                return;
            } else {



                String contentText = "Are you sure want to renew the book ?";


                // alert box button confirmation process here .......

                Alert alert = AlertBox.confirmationAlert(contentText);
                Optional<ButtonType> response = alert.showAndWait();

                if (response.get() == ButtonType.OK) {



                    String query = "UPDATE "+Const.ISSUE_TABLE+" SET "+Const.ISSUES_TIME+" = CURRENT_TIMESTAMP, "+Const.RENEW_COUNT+" = "+Const.RENEW_COUNT+" + 1 WHERE "+Const.BOOK_ID+" = '"+bookID.getText().toString().trim()+"' ";
                    System.out.println(query);

                    if (databaseHandler.exeAction(query)) {
                        AlertBox.successAlert("Book has been renewed");
                    } else {
                        AlertBox.errorAlert("Renewing Failed! ");
                    }




                } else {
                    AlertBox.successAlert("Canceled");
                }









            }
        }

    }


    @FXML
    void loadBookInfo2(ActionEvent event) throws SQLException {

        readyForSubmission = false;

        observableList = FXCollections.observableArrayList();

        String id = bookID.getText().toString().trim();
        String query = "SELECT * FROM "+Const.ISSUE_TABLE+" WHERE "+Const.BOOK_ID+" = '"+id+"' ";

        ResultSet resultSet = databaseHandler.exeQuery(query);
        while(resultSet.next()) {
            String mBookId = id;
            String mMemberId = resultSet.getString(Const.MEMBAR_ID);
            Timestamp timestamp = resultSet.getTimestamp(Const.ISSUES_TIME);
            int mRenewCount = resultSet.getInt(Const.RENEW_COUNT);

            observableList.add("Issues Date and Time : "+timestamp.toGMTString());
            observableList.add("Renew Count : "+mRenewCount);
            observableList.add(" ");

            observableList.add("Book Information :- ");

            query = "SELECT * FROM "+Const.BOOK_TABLE+" WHERE "+Const.ID+" = '"+mBookId+"' ";
            ResultSet resultSet1 = databaseHandler.exeQuery(query);
            while(resultSet1.next()) {
                observableList.add("\tBook Name : "+resultSet1.getString(Const.TITLE));
                observableList.add("\tBook Id : "+resultSet1.getString(Const.ID));
                observableList.add("\tBook Author : "+resultSet1.getString(Const.AUTHOR));
                observableList.add("\tBook Publisher : "+resultSet1.getString(Const.PUBLISHER));
            }

            query = "SELECT * FROM "+Const.MEMBAR_TABLE+" WHERE "+Const.ID+" = '"+mMemberId+"' ";
            System.out.println(query);
            ResultSet resultSet2 = databaseHandler.exeQuery(query);

            observableList.add(" ");

            observableList.add("Member Information :- ");

            while(resultSet2.next()) {
                observableList.add("\tName : "+resultSet2.getString(Const.NAME));
                observableList.add("\tMobile : "+resultSet2.getString(Const.MOBILE));
                observableList.add("\tEmail : "+resultSet2.getString(Const.EMAIL));
            }

            readyForSubmission = true;
        }

        listView.getItems().setAll(observableList);

    }

    @FXML
    void initialize() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        stageLoader = new StageLoader();
        databaseHandler = DatabaseHandler.getDbInstance();
        // JFXDepthManager.setDepth(button_info, 1);






        issuesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String bookId = bookInput.getText().toString().trim();
                String memberId = memberInputid.getText().toString().trim();

                if (!bookId.equals("") && !memberId.equals("")) {
                    String contentText = "Are you sure want to issue the book "+bookName.getText()+
                            "\n to "+memberName.getText() + " ?";


                    // alert box button confirmation process here .......

                    Alert alert = AlertBox.confirmationAlert(contentText);
                    Optional<ButtonType> resonse = alert.showAndWait();

                    if (resonse.get() == ButtonType.OK) {

                        String query = Const.insertIssue(new Issue(bookId, memberId));
                        String updateQuery = Const.updateBook(bookId);
                        //System.out.println(updateQuery);
                        //System.out.println(query);

                        if (databaseHandler.exeAction(query) && databaseHandler.exeAction(updateQuery)) {
                            AlertBox.successAlert("Book issues complete");
                        } else {
                            AlertBox.errorAlert("Can't issued book");
                        }

                    } else {
                        AlertBox.successAlert("Canceled");
                    }
                } else {
                    AlertBox.errorAlert("Please enter BOOK ID & MEMBER ID first");
                }


            }
        });

    }
}
