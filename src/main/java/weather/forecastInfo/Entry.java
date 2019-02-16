package main.java.weather.forecastInfo;

public class Entry {
    private static final String apiKey = "305be23140a9d5d08890247143be3227";
    private String cityName;
    private String countryCode;

    public Entry(String cityName, String countryCode) {
        setCityName(cityName);
        setCountryCode(countryCode);
    }

    public Entry(String combinedName) {
        String str[] = combinedName.split(",");
        setCityName(str[0]);
        setCountryCode(str[1]);
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return cityName + ',' +
                countryCode;
    }
}
