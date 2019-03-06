package main.java.weather.UIApp.forecastApp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.java.weather.UIApp.alert.AlertMaker;
import main.java.weather.UIApp.label.LabelObj;
import main.java.weather.UIApp.label.LabelObjList;
import main.java.weather.forecastInfo.*;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

import java.text.SimpleDateFormat;
import java.util.List;

public class ForecastController {

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private Label currentTimeLbl;
    @FXML
    private Label cityNameLbl;
    @FXML
    private ImageView weatherIcon;
    @FXML
    private Label descriptionLbl;
    @FXML
    private Label temperatureLbl;
    @FXML
    private Label pressureLbl;
    @FXML
    private Label humidityLbl;
    @FXML
    private Label windLbl;
    @FXML
    private Label cloudsLbl;
    @FXML
    private ImageView windIcon;
    @FXML
    private ImageView pressureIcon;
    @FXML
    private ImageView humidityIcon;
    @FXML
    private ImageView cloudsIcon;
    @FXML
    private HBox daily;
    @FXML
    private Pane pane1;
    @FXML
    private Label dateLblDay1;
    @FXML
    private ImageView weatherIconDay1;
    @FXML
    private Pane pane2;
    @FXML
    private Label temperatureLblDay1;
    @FXML
    private Label dateLblDay2;
    @FXML
    private ImageView weatherIconDay2;
    @FXML
    private Label temperatureLblDay2;
    @FXML
    private Pane pane3;
    @FXML
    private Label dateLblDay3;
    @FXML
    private ImageView weatherIconDay3;
    @FXML
    private Label temperatureLblDay3;
    @FXML
    private Pane pane4;
    @FXML
    private Label dateLblDay4;
    @FXML
    private ImageView weatherIconDay4;
    @FXML
    private Label temperatureLblDay4;
    @FXML
    private Pane pane5;
    @FXML
    private Label dateLblDay5;
    @FXML
    private ImageView weatherIconDay5;
    @FXML
    private Label temperatureLblDay5;
    @FXML
    private Pane menuBar;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private Button closeBtn;

    private JFXSnackbar jfxSnackbar;
    private CustomTextField searchBar;
    private LabelObjList labelObjList;
    private List<LocationInfo> locationList;

    @FXML
    private void initialize() {
        String currentLocation = new GetCurrentLocation().getCurrentEntry();
        startApp(currentLocation);
    }

    void startApp(String currentLocation) {
        Init();
        setTextFieldProperties();

        if (locationList.stream().anyMatch(o -> o.toString().equals(currentLocation))) {
            try {
                Entry entry = new Entry(currentLocation);
                WeatherInfo weather = new WeatherInfo(entry);
                weather.getHttpRequest();
                List<ForecastValue> fList = weather.getDailyForecastList();
                List<LabelObj> LblList = labelObjList.getLabelObjList();
                cityNameLbl.setText(entry.toString());
                setCurrentWeatherLbl(fList.get(0));

                for (int i = 0; i < LblList.size(); i++) {
                    LblList.get(i).setLabelObj(fList.get(i));
                }

                searchBtn.setOnMouseClicked(event -> {
                    if (!menuBar.getChildren().contains(searchBar))
                        menuBar.getChildren().add(searchBar);
                    else
                        startApp(searchBar.getText());
                });

                refreshBtn.setOnMousePressed(event -> {
                    jfxSnackbar = new JFXSnackbar(rootAnchorPane);
                    jfxSnackbar.setPrefWidth(300);
                    jfxSnackbar.fireEvent(new SnackbarEvent("Updating..."));
                });

                refreshBtn.setOnMouseReleased(e -> {
                    jfxSnackbar = new JFXSnackbar(rootAnchorPane);
                    jfxSnackbar.setPrefWidth(300);
                    if (menuBar.getChildren().contains(searchBar))
                        menuBar.getChildren().remove(searchBar);
                    startApp(cityNameLbl.getText());
                    ImageView update = new ImageView(new Image("resources/verified.png"));
                    update.setLayoutY(22);
                    update.setLayoutX(95);
                    jfxSnackbar.getChildren().add(1, update);
                    jfxSnackbar.fireEvent(new SnackbarEvent("Updated Successfully"));
                });

                pane1.setOnMouseClicked(event -> action(pane1, fList));

                pane2.setOnMouseClicked(event -> action(pane2, fList));

                pane3.setOnMouseClicked(event -> action(pane3, fList));

                pane4.setOnMouseClicked(event -> action(pane4, fList));

                pane5.setOnMouseClicked(event -> action(pane5, fList));


            } catch (Exception e) {
                JFXButton button = new JFXButton("Retry");
                button.setOnMouseClicked(ee -> startApp(cityNameLbl.getText()));
                AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, button, "Something went wrong, Please try again.");
            }
        } else {
            JFXButton button = new JFXButton("Okay");
            AlertMaker.showMaterialDialog(rootPane, rootAnchorPane, button, "Please enter valid location. ");
        }
    }

    void Init() {
        refreshBtn.setGraphic(new ImageView((new Image("resources/refresh.png"))));
        refreshBtn.setStyle("-fx-background-color: #142747;");
        refreshBtn.setScaleY(0.5);
        refreshBtn.setScaleX(0.5);
        refreshBtn.setTooltip(new Tooltip("Refresh"));

        searchBtn.setGraphic(new ImageView(new Image("resources/magnifying-glass.png")));
        searchBtn.setStyle("-fx-background-color: #142747;");
        searchBtn.setScaleY(0.7);
        searchBtn.setScaleX(0.7);
        searchBtn.setTooltip(new Tooltip("Search"));

        closeBtn.setGraphic(new ImageView(new Image("resources/close.png")));
        closeBtn.setStyle("-fx-background-color: #142747;");
        closeBtn.setScaleY(0.8);
        closeBtn.setScaleX(0.8);
        closeBtn.setTooltip(new Tooltip("Close"));
        closeBtn.setOnMouseClicked(event -> {
            Stage stage = (Stage) closeBtn.getScene().getWindow();
            stage.close();
        });

        pressureIcon.setImage(new Image("resources/gauge.png"));
        humidityIcon.setImage(new Image("resources/humidity.png"));
        windIcon.setImage(new Image("resources/wind-sign.png"));
        cloudsIcon.setImage(new Image("resources/cloud.png"));
        labelObjList = new LabelObjList(dateLblDay1, weatherIconDay1, temperatureLblDay1, dateLblDay2, weatherIconDay2, temperatureLblDay2,
                dateLblDay3, weatherIconDay3, temperatureLblDay3, dateLblDay4, weatherIconDay4, temperatureLblDay4, dateLblDay5, weatherIconDay5, temperatureLblDay5);

        daily.getChildren().forEach(node -> {
            node.setStyle("-fx-background-color: transparent;");
            node.setEffect(null);
        });
        pane1.setEffect(new DropShadow());
        pane1.setStyle("-fx-background-color: rgb(255, 255, 255, 0.1);");
    }

    private void setCurrentWeatherLbl(ForecastValue f) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMMM d ");
        currentTimeLbl.setText(dateFormat.format(f.getDate()));
        weatherIcon.setImage(new Image(f.getWeatherIcon()));
        descriptionLbl.setText(f.getDescription());
        temperatureLbl.setText(String.valueOf(f.getTemperature()));
        pressureLbl.setText(f.getPressure());
        humidityLbl.setText(f.getHumidity());
        windLbl.setText(f.getWind_Speed());
        cloudsLbl.setText(f.getClouds());
    }


    void setTextFieldProperties() {
        locationList = new LocationList().getTarget();
        searchBar = new CustomTextField();
        ImageView pin = new ImageView(new Image("resources/placeholder.png"));

        Button clear = new Button();
        clear.setGraphic(new ImageView(new Image("resources/cancel.png")));
        clear.setStyle(" -fx-background-color: WHITE;");
        clear.setScaleY(0.8);
        clear.setScaleX(0.8);

        TextFields.bindAutoCompletion(searchBar, locationList);

        searchBar.setLeft(pin);
        searchBar.setRight(clear);
        searchBar.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER)
                startApp(searchBar.getText());
        });

        clear.setOnMouseClicked(event -> searchBar.setText(""));

        searchBar.setPrefSize(250, 20);
        searchBar.setLayoutX(285);
        searchBar.setLayoutY(12);
    }

    void action(Pane pane, List<ForecastValue> forecastList) {
        daily.getChildren().forEach(node -> {
            node.setStyle("-fx-background-color: transparent;");
            node.setEffect(null);
        });
        pane.setEffect(new DropShadow());
        pane.setStyle("-fx-background-color: rgb(255, 255, 255, 0.1);");
        String date = pane.getChildren().get(0).toString();
        for (ForecastValue f : forecastList) {
            if (date.contains(f.getDateString())) {
                setCurrentWeatherLbl(f);
            }
        }
    }
}
