package com.bok.onnal.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ForecastResponse {
    @SerializedName("list")
    private List<ForecastItem> list;

    public List<ForecastItem> getList() {
        return list;
    }

    public static class ForecastItem {
        @SerializedName("dt_txt")
        private String date;

        @SerializedName("main")
        private Main main;

        @SerializedName("weather")
        private List<WeatherResponse.Weather> weather;

        public String getDate() {
            return date;
        }

        public Main getMain() {
            return main;
        }

        public List<WeatherResponse.Weather> getWeather() {
            return weather;
        }
    }

    public static class Main {
        @SerializedName("temp")
        private float temp;

        public float getTemp() {
            return temp;
        }
    }
}
