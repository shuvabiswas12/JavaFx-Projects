package sample.assests;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shaker {


    private TranslateTransition translateTransition;


    public Shaker(Node node) {

         translateTransition = new TranslateTransition(Duration.millis(100), node);
         translateTransition.setFromX(0f);
         translateTransition.setByX(10f);
         translateTransition.setCycleCount(4);
         translateTransition.setAutoReverse(true);
    }


    public Shaker(Node ... node) {

        for (Node new_node : node) {

            translateTransition = new TranslateTransition(Duration.millis(100), new_node);
            translateTransition.setFromX(0f);
            translateTransition.setByX(10f);
            translateTransition.setCycleCount(4);
            translateTransition.setAutoReverse(true);

            shake();
        }
    }


    public void shake() {
        translateTransition.playFromStart();
    }

}
