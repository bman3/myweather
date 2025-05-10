package com.bok.onnal.model;

import java.util.List;

public class WeatherResponse {
    public Main main;
    public List<Weather> weather;
    public Wind wind;
    public String name;
    public List<ForecastItem> list;
    public int dt;

    public static class Main {
        public float temp;
        public float temp_min;
        public float temp_max;
        public int humidity;
    }

    public static class Weather {
        public String main;
        public String description;
        public String icon;
    }

    public static class Wind {
        public float speed;
    }

    public static class ForecastItem {
        public int dt;
        public Main main;
        public List<Weather> weather;
    }
}
