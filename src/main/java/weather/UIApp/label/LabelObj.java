package main.java.weather.UIApp.label;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.java.weather.forecastInfo.ForecastValue;

public class LabelObj {
    private static final String DegreeCelsius = "\u00b0C";
    private Label dateLbl;
    private ImageView iconLbl;
    private Label tempLbl;

    public LabelObj(Label dateLbl, ImageView iconLbl, Label tempLbl) {
        this.dateLbl = dateLbl;
        this.iconLbl = iconLbl;
        this.tempLbl = tempLbl;
    }

    private void setDateLbl(String date) {
        this.dateLbl.setText(date);
    }

    private void setIconLbl(String description) {
        this.iconLbl.setImage(new Image(description));
    }

    private void setTempLbl(String temperature) {
        this.tempLbl.setText(temperature);
    }

    public void setLabelObj(ForecastValue f) {
        setDateLbl(f.getDateString());
        setIconLbl(f.getWeatherIcon());
        setTempLbl(String.valueOf(f.getMinTemperature() + DegreeCelsius + " / " + f.getMaxTemperature() + DegreeCelsius));
    }

}
