package main.java.weather.UIApp.alert;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class AlertMaker {

    public static void showMaterialDialog(StackPane root, Node nodeToBeBlurred, JFXButton button, String body) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setBody(new Text(body));
        content.setPrefSize(300, 20);
        JFXDialog jfxDialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);
        button.setStyle("-fx-background-color: #F39C12; -fx-text-fill: white;");
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setOnAction(mouseEvent -> jfxDialog.close());
        content.setActions(button);
        jfxDialog.show();
        jfxDialog.setOnDialogClosed((JFXDialogEvent event1) ->
                nodeToBeBlurred.setEffect(null)
        );
         nodeToBeBlurred.setEffect(new GaussianBlur());
    }

}
