package sample.controller;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.animation.Shaker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController implements Initializable {

    private int user_Id;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label taskLabelID;

    @FXML
    private ImageView addItemBtnId;

    @FXML
    void initialize() {
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'addItem.fxml'.";
        assert taskLabelID != null : "fx:id=\"taskLabelID\" was not injected: check your FXML file 'addItem.fxml'.";
        assert addItemBtnId != null : "fx:id=\"addItemBtnId\" was not injected: check your FXML file 'addItem.fxml'.";

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addItemBtnId.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Shaker addItemShaker = new Shaker(addItemBtnId);
                addItemShaker.shake();
                System.out.println("button is clicked");


                /*FadeTransition fadeTransition  = new FadeTransition(Duration.millis(2000), addItemBtnId);
                FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(2000), taskLabelID);*/

                // remove
               /* addItemBtnId.relocate(0, 10);
                taskLabelID.relocate(0, 50);*/

                /*addItemBtnId.setOpacity(0);
                taskLabelID.setOpacity(0);

                fadeTransition.setFromValue(1);
                fadeTransition.setToValue(0);
                fadeTransition.setCycleCount(1);
                fadeTransition.setAutoReverse(false);
                fadeTransition.play();

                fadeTransition1.setFromValue(1);
                fadeTransition1.setToValue(0);
                fadeTransition1.setCycleCount(1);
                fadeTransition1.setAutoReverse(false);
                fadeTransition1.play();*/

                try {
                    AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/view/addItemForm.fxml"));

                    AddItemFormController addItemFormController = new AddItemFormController();
                    addItemFormController.setUserId(getUser_Id());



                    FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(2000), formPane);
                    fadeTransition2.setFromValue(0);
                    fadeTransition2.setToValue(1);
                    fadeTransition2.setCycleCount(1);
                    fadeTransition2.setAutoReverse(false);
                    fadeTransition2.play();


                    rootPane.getChildren().setAll(formPane);




                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
        System.out.println("user id = " + this.user_Id);
    }

    public int getUser_Id() {
        return user_Id;
    }
}
