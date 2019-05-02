package sample;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyPreloader extends Preloader {

    private Stage preloaderStage;
    private Scene scene;
    public static int val = 0;

    @Override
    public void init() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("splash.fxml"));
        scene = new Scene(root);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;
        preloaderStage.setScene(scene);
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.show();
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info) {
        if (info instanceof  PreloaderNotification) {
            //NumberFormat numberFormat = new DecimalFormat("00.0");


            SplashController.label.setText("Loading... "+((ProgressNotification) info).getProgress()+"%");
            //SplashController.label.setText("Loading  "+(++val)+"%");
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        StateChangeNotification.Type type = info.getType();

        switch (type) {
            case BEFORE_START:
                System.out.println("Before Start");
                preloaderStage.hide();
                break;
        }
    }
}
