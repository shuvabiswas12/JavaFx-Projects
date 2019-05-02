package sample.assests;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class CustomDialogueBox {

    // material dialogue box implementation .... >>
    public void materialDialogue(String title, String description, StackPane stackPane) {

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(title));
        layout.setBody(new Text(description));
        JFXButton jfxButton = new JFXButton("OK");
        jfxButton.setStyle("-fx-background-color: #5dade2");
        layout.setActions(jfxButton);
        JFXDialog jfxDialog = new JFXDialog(stackPane, layout, JFXDialog.DialogTransition.CENTER);
        jfxButton.setOnAction(e -> {
            jfxDialog.close();
        });
        jfxDialog.setPrefWidth(100.0);
        jfxDialog.show();
    }
}
