package main.java.weather.forecastInfo;

import net.aksingh.owmjapis.AbstractWeather;
import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;

import java.util.ArrayList;
import java.util.List;

public class WeatherInfo {

    private static final byte count = 6;
    private Entry entry;
    private List<ForecastValue> DailyForecastList;

    public WeatherInfo(Entry entry) {
        DailyForecastList = new ArrayList<>();
        this.entry = entry;
    }

    public void getHttpRequest() {
        OpenWeatherMap.Units units = OpenWeatherMap.Units.METRIC;
        OpenWeatherMap owm = new OpenWeatherMap(units, entry.getApiKey());
        try {
            DailyForecast d = owm.dailyForecastByCityName(entry.getCityName(), entry.getCountryCode(), count);
            System.out.println(d.getRawResponse());
            int n = d.getForecastCount();
            for (int i = 0; i <= n; i++) {
                DailyForecast.Forecast forecast = d.getForecastInstance(i);
                AbstractWeather.Weather weather = forecast.getWeatherInstance(0);
                ForecastValue f = initializeForecastValue(forecast, weather);
                DailyForecastList.add(f);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<ForecastValue> getDailyForecastList() {
        return DailyForecastList;
    }


    private ForecastValue initializeForecastValue(DailyForecast.Forecast forecast, AbstractWeather.Weather weather) {
        ForecastValue f = new ForecastValue();
        f.setDate(forecast.getDateTime());
        f.setTemperature((int) forecast.getTemperatureInstance().getDayTemperature());
        f.setMinTemperature((int) forecast.getTemperatureInstance().getMinimumTemperature());
        f.setMaxTemperature((int) forecast.getTemperatureInstance().getMaximumTemperature());
        f.setDescription(String.valueOf(weather.getWeatherDescription()));
        f.setWeatherIcon(findWeatherIcon(String.valueOf(weather.getWeatherDescription())));
        f.setHumidity(String.valueOf(forecast.getHumidity() + "%"));
        f.setPressure(String.valueOf(forecast.getPressure() + " mbar "));
        f.setClouds(String.valueOf(forecast.getPercentageOfClouds() + "%"));
        f.setWind_Speed(String.valueOf(forecast.getWindSpeed() + " m/s"));
        return f;
    }

    public String findWeatherIcon(String description) {
        if (description.contains(WeatherDescription.Clouds)) {
            return WeatherDescription.CloudIcon;
        } else if (description.contains(WeatherDescription.Sleet)) {
            return WeatherDescription.SleetIcon;
        } else if (description.contains(WeatherDescription.Snow)) {
            return WeatherDescription.SnowIcon;
        } else if (description.contains(WeatherDescription.Storm)) {
            return WeatherDescription.StormIcon;
        } else if (description.contains(WeatherDescription.Rain) || description.contains(WeatherDescription.Drizzle)) {
            return WeatherDescription.RainIcon;
        } else if (description.contains(WeatherDescription.Clear)) {
            return WeatherDescription.SunIcon;
        } else {
            return WeatherDescription.HazeIcon;
        }
    }

}
