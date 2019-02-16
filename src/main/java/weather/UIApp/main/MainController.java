package main.java.weather.UIApp.main;


import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.weather.UIApp.alert.AlertMaker;
import main.java.weather.forecastInfo.GetCurrentLocation;

public class MainController {

    @FXML
    ProgressIndicator progress;
    @FXML
    StackPane stackPane;
    @FXML
    AnchorPane anchorPane;
    Stage stage;
    Parent parent;

    @FXML
    private void initialize() {
        stackPane.setOnMouseEntered(event -> stage = (Stage) stackPane.getScene().getWindow());
        try {
            String c = new GetCurrentLocation().getCurrentEntry();
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        parent = FXMLLoader.load(getClass().getResource("../forecastApp/forecast.fxml"));
                        Thread.sleep(3000);
                        Platform.runLater(() -> loadMain());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    return null;
                }
            };
            new Thread(sleeper).start();
        } catch (Exception e) {
            anchorPane.getChildren().remove(progress);
            JFXButton button = new JFXButton("Retry");
            button.setOnMouseClicked(event -> {
                anchorPane.getChildren().add(progress);
                initialize();
            });
            AlertMaker.showMaterialDialog(stackPane, anchorPane, button, "Something went wrong, Please try again.");
        }
    }

    void loadMain() {
        stage.close();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("WEATHER");
        Scene scene = new Scene(parent, 640, 470);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
