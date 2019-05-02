package sample.assests;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageLoader {

    private FXMLLoader loader;
    private Parent root;

    public void open(String url, String title) throws IOException {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(url));
        root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.showAndWait();
    }
}
