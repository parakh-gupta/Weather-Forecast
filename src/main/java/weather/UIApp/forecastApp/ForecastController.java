package main.java.weather.UIApp.forecastApp;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import main.java.weather.UIApp.alert.AlertMaker;
import main.java.weather.UIApp.label.LabelObj;
import main.java.weather.UIApp.label.LabelObjList;
import main.java.weather.forecastInfo.*;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

import java.util.List;

public class ForecastController {

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane rootAnchorPane;
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
    private Label cloudLbl;
    @FXML
    private ImageView windIcon;
    @FXML
    private ImageView pressureIcon;
    @FXML
    private ImageView humidityIcon;
    @FXML
    private ImageView cloudIcon;
    @FXML
    private Label dateLblDay1;
    @FXML
    private ImageView weatherIconDay1;
    @FXML
    private Label temperatureLblDay1;
    @FXML
    private Label dateLblDay2;
    @FXML
    private ImageView weatherIconDay2;
    @FXML
    private Label temperatureLblDay2;
    @FXML
    private Label dateLblDay3;
    @FXML
    private ImageView weatherIconDay3;
    @FXML
    private Label temperatureLblDay3;
    @FXML
    private Label dateLblDay4;
    @FXML
    private ImageView weatherIconDay4;
    @FXML
    private Label temperatureLblDay4;
    @FXML
    private Label dateLblDay5;
    @FXML
    private ImageView weatherIconDay5;
    @FXML
    private Label temperatureLblDay5;
    @FXML
    private Pane menuBar;
    @FXML
    private ImageView refreshBtn;
    @FXML
    private ImageView searchIcon;
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
        if (locationList.stream().filter(o -> o.toString().equals(currentLocation)).findFirst().isPresent()) {
            try {
                Entry entry = new Entry(currentLocation);
                WeatherInfo weather = new WeatherInfo(entry);
                weather.getHttpRequest();
                List<ForecastValue> fList = weather.getDailyForecastList();
                List<LabelObj> LblList = labelObjList.getLabelObjList();
                cityNameLbl.setText(entry.toString());
                setCurrentWeatherLbl(fList.get(0));
                for (int i = 0; i < LblList.size(); i++) {
                    LblList.get(i).setLabelObj(fList.get(i + 1));
                }
                searchIcon.setOnMouseClicked(event -> {
                    if (!menuBar.getChildren().contains(searchBar))
                        menuBar.getChildren().add(searchBar);
                    else
                        startApp(searchBar.getText());

                });
                refreshBtn.setOnMouseClicked(event -> {
                    if (menuBar.getChildren().contains(searchBar))
                        menuBar.getChildren().remove(searchBar);
                    startApp(cityNameLbl.getText());
                });
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
        refreshBtn.setImage(new Image("resources/refresh.png"));
        searchIcon.setImage(new Image("resources/magnifying-glass.png"));
        pressureIcon.setImage(new Image("resources/gauge.png"));
        humidityIcon.setImage(new Image("resources/humidity.png"));
        windIcon.setImage(new Image("resources/wind-sign.png"));
        cloudIcon.setImage(new Image("resources/cloud.png"));
        labelObjList = new LabelObjList(dateLblDay1, weatherIconDay1, temperatureLblDay1, dateLblDay2, weatherIconDay2, temperatureLblDay2,
                dateLblDay3, weatherIconDay3, temperatureLblDay3, dateLblDay4, weatherIconDay4, temperatureLblDay4, dateLblDay5, weatherIconDay5, temperatureLblDay5);

    }

    private void setCurrentWeatherLbl(ForecastValue f) {
        weatherIcon.setImage(new Image(f.getWeatherIcon()));
        descriptionLbl.setText(f.getDescription());
        temperatureLbl.setText(String.valueOf(f.getTemperature()));
        pressureLbl.setText(f.getPressure());
        humidityLbl.setText(f.getHumidity());
        windLbl.setText(f.getWind_Speed());
        cloudLbl.setText(f.getClouds());
    }


    void setTextFieldProperties() {
        locationList = new LocationList().getTarget();
        searchBar = new CustomTextField();
        ImageView pin = new ImageView(new Image("resources/placeholder.png"));
        ImageView clear = new ImageView(new Image("resources/cancel.png"));
        TextFields.bindAutoCompletion(searchBar, locationList);
        searchBar.setLeft(pin);
        searchBar.setRight(clear);
        searchBar.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER)
                startApp(searchBar.getText());
        });
        clear.setOnMouseClicked(event -> searchBar.clear());
        searchBar.setPrefSize(250, 25);
        searchBar.setLayoutX(320);
        searchBar.setLayoutY(5);
    }


}
