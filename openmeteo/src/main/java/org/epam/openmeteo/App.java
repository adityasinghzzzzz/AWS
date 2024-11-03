package org.epam.openmeteo;

import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
public class App 
{

    private static final String BASE_URL="https://api.open-meteo.com/v1/forecast";
    private final double latitude;
    private final double longitude;
    private final OkHttpClient client;
    private final Gson gson;
    private final JsonObject jsonObject;

    public App(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.client = new OkHttpClient();
        this.gson = new Gson();
        this.jsonObject=new JsonObject();
    }
    public JsonObject getWeatherForecast() throws IOException {
        String url = String.format("%s?latitude=%f&longitude=%f&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m&current_weather=true", BASE_URL, latitude, longitude);
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return gson.fromJson(response.body().string(), JsonObject.class);
        }
    }
    public static void main( String[] args )
    {
        try {
            App client = new App(50.4375, 30.5);
            JsonObject weatherData = client.getWeatherForecast();
            System.out.println(weatherData.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
