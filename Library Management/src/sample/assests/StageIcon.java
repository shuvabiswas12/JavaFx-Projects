package sample.assests;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StageIcon {
    private static final String IMAGE_LOC = "sample/assests/web_hi_res_512.png";

    public static void addIcon(Stage stage) {
        stage.getIcons().add(new Image(IMAGE_LOC));
        return;
    }
}
