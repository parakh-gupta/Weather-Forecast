package main.java.weather.UIApp.main;


import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.weather.UIApp.alert.AlertMaker;


public class MainController {

    @FXML
    StackPane stackPane;
    @FXML
    AnchorPane anchorPane;
    @FXML
    private Button closeBtn;
    @FXML
    private ImageView start;

    StackPane parent;

    @FXML
    private void initialize() {
        start.setImage(new Image("resources/cloudy.png"));
        if (!MainLauncher.isSplashLoaded) {
            loadSplashScreen();
        }
        closeBtn.setGraphic(new ImageView(new Image("resources/close.png")));
        closeBtn.setStyle("-fx-background-color: #142747;");
        closeBtn.setScaleY(0.8);
        closeBtn.setScaleX(0.8);
        closeBtn.setTooltip(new Tooltip("Close"));
        closeBtn.setOnMouseClicked(event -> {
            Stage stage = (Stage) closeBtn.getScene().getWindow();
            stage.close();
        });
    }

    private void loadSplashScreen() {
        try {
            MainLauncher.isSplashLoaded = true;

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), start);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), start);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0.5);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                try {
                    parent = FXMLLoader.load(getClass().getResource(("../forecastApp/forecast.fxml")));
                    fadeOut.play();
                } catch (Exception ex) {
                    alertBox();
                }
            });
            fadeOut.setOnFinished((event) ->
                    anchorPane.getChildren().setAll(parent)
            );

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            alertBox();
        }
    }

    void alertBox() {
        JFXButton button = new JFXButton("Retry");
        button.setOnMouseClicked(event -> {
            MainLauncher.isSplashLoaded = false;
            initialize();
        });
        AlertMaker.showMaterialDialog(stackPane, anchorPane, button, "Something went wrong, Please try again.");
    }
}
