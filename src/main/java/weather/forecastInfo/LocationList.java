package main.java.weather.forecastInfo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationList {
    public static final String fileName = "cityList.json";
    List<LocationInfo> target;

    public LocationList() {
        target = new ArrayList<>();
        try (FileReader reader = new FileReader(fileName)) {
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(reader);
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            target = Arrays.asList(gson.fromJson(jsonArray, LocationInfo[].class));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<LocationInfo> getTarget() {
        return target;
    }

}



