package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.FlowPane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler {

    private Button rotateButton;
    private Button blurButon;
    private Button scaleButton;
    private Label reflect;
    private Rotate rotate;
    private double angle;
    private double blurValue;
    private BoxBlur blur;
    private Scale scale;
    private double scaleFector;
    private Reflection reflection;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Effects and Transformation");

        rotateButton = new Button("Rotate");
        blurButon = new Button("Blur");
        scaleButton = new Button("Scale");

        reflect = new Label("Reflection adds Visual Sparkle");

        // set up rotation
        rotate = new Rotate();
        rotateButton.getTransforms().add(rotate);

        // set up scale
        scale = new Scale();
        scaleButton.getTransforms().add(scale);


        // set up blur
        blur = new BoxBlur(1.0,1.0, 1);

        // reflection
        reflection = new Reflection();
        reflection.setTopOpacity(0.7);
        reflection.setBottomOpacity(0.3);
        reflect.setEffect(reflection);



        angle = 0.0;
        blurValue = 1.0;
        scaleFector = 0.4;

        FlowPane myFlowPaneroot = new FlowPane(Orientation.HORIZONTAL, 15, 10);
        myFlowPaneroot.setAlignment(Pos.CENTER);
        myFlowPaneroot.getChildren().addAll(rotateButton, blurButon, scaleButton, reflect);

        Scene scene = new Scene(myFlowPaneroot, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();

        // listener
        rotateButton.setOnAction(this);
        blurButon.setOnAction(this);
        scaleButton.setOnAction(this);


    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(Event event) {

        // rotate button
        if (event.getSource().equals(rotateButton)) {
            //System.out.println("Rotate button is clicked");

            angle += 15.0;
            rotate.setAngle(angle);
            rotate.setPivotX(rotateButton.getWidth() / 2);
            rotate.setPivotY(rotateButton.getHeight() / 2);
        }


        // blur button
        if (event.getSource().equals(blurButon)) {
            if (blurValue == 10.0) {
                blurValue = 1.0;
                blurButon.setEffect(blur);
                blurButon.setText("Blur On");
            }
            else {
                blurValue++;
                blurButon.setEffect(blur);
                blurButon.setText("Blur On");
            }

            blur.setWidth(blurValue);
            blur.setHeight(blurValue);
        }



        // scale button
        if (event.getSource().equals(scaleButton)) {

            scaleFector += 0.1;

            if (scaleFector > 2.0) scaleFector = 0.4;

            scale.setX(scaleFector);
            scale.setY(scaleFector);
        }
    }
}
