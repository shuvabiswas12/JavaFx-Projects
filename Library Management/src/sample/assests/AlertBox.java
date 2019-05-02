package sample.assests;

import javafx.scene.control.Alert;

public class AlertBox {

    private static Alert alert;

    private AlertBox () {

    }

    public static void successAlert(String contextText) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    public static Alert confirmationAlert(String contextText) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText(contextText);

        return alert;
    }

    public static void errorAlert(String contextText) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    public static Alert errorAlert2(String contextText) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
        return alert;
    }
}
