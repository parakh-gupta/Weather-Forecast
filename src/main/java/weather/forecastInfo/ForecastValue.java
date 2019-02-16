package main.java.weather.forecastInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ForecastValue {
    private Date date;
    private int Temperature;
    private int MinTemperature;
    private int MaxTemperature;
    private String WeatherIcon;
    private String Description;
    private String Humidity;
    private String Pressure;
    private String Clouds;
    private String Wind_Speed;

    public ForecastValue() {
        date = null;
        Temperature = 0;
        MinTemperature = 0;
        MaxTemperature = 0;
        WeatherIcon = null;
        Description = null;
        Humidity = null;
        Pressure = null;
        Clouds = null;
        Wind_Speed = null;
    }

    public ForecastValue(Date date, int temperature, int minTemperature, int maxTemperature, String weatherIcon,
                         String description, String humidity, String pressure, String clouds, String wind_Speed) {
        this.date = date;
        Temperature = temperature;
        MinTemperature = minTemperature;
        MaxTemperature = maxTemperature;
        WeatherIcon = weatherIcon;
        Description = description;
        Humidity = humidity;
        Pressure = pressure;
        Clouds = clouds;
        Wind_Speed = wind_Speed;
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM d");
        return dateFormat.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTemperature() {
        return Temperature;
    }

    public void setTemperature(int temperature) {
        Temperature = temperature;
    }

    public int getMinTemperature() {
        return MinTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        MinTemperature = minTemperature;
    }

    public int getMaxTemperature() {
        return MaxTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        MaxTemperature = maxTemperature;
    }

    public String getWeatherIcon() {
        return WeatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        WeatherIcon = weatherIcon;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getPressure() {
        return Pressure;
    }

    public void setPressure(String pressure) {
        Pressure = pressure;
    }

    public String getClouds() {
        return Clouds;
    }

    public void setClouds(String clouds) {
        Clouds = clouds;
    }

    public String getWind_Speed() {
        return Wind_Speed;
    }

    public void setWind_Speed(String wind_Speed) {
        Wind_Speed = wind_Speed;
    }

    @Override
    public String toString() {
        return date + " ,"
                + Temperature + " ,"
                + MinTemperature + ","
                + MaxTemperature + ","
                + Description + " ,"
                + Humidity + " ,"
                + Pressure + " ,"
                + Clouds + " ,"
                + Wind_Speed + " ,"
                + WeatherIcon;
    }
}

