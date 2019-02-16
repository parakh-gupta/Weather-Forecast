package main.java.weather.forecastInfo;

public class LocationInfo {
    private int id;
    private String name;
    private String country;
    private Coord coord;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Coord getCoordInstance() {
        return coord;
    }

    @Override
    public String toString() {
        return name + "," + country;
    }

    private class Coord {
        private double lon;
        private double lat;

        public double getLon() {
            return lon;
        }

        public double getLat() {
            return lat;
        }
    }
}
