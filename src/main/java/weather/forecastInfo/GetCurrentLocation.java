package main.java.weather.forecastInfo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetCurrentLocation {

    private static final String requestAddress = "https://api.ipdata.co?api-key=";
    private static final String ApiKey = "5d5dd1d2d939daf5ff24031fb1b1ecc8b81518c72a5ba4bc16422920";

    private String getResponse() {
        URL request;
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        String tmpStr;
        String response = "";
        try {
            request = new URL(requestAddress + ApiKey);
            connection = (HttpURLConnection) request.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((tmpStr = reader.readLine()) != null) {
                        response += tmpStr;
                    }
                } catch (IOException e) {
                    System.err.println("Error: " + e.getMessage());
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                    }
                }
            } else {
                System.out.println("Response Code: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            response = null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response;
    }

    public String getCurrentEntry() {
        String response = getResponse();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response);
        JsonObject obj = element.getAsJsonObject();
        String City = obj.get("city").getAsString();
        String Country = obj.get("country_code").getAsString();
        return City + "," + Country;
    }

}

