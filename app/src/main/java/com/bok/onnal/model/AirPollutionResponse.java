package com.bok.onnal.model;

import java.util.List;

public class AirPollutionResponse {
    public List<AirData> list;

    public static class AirData {
        public Main main;
        public Components components;
    }

    public static class Main {
        public int aqi;
    }

    public static class Components {
        public double pm10;
        public double pm2_5;
    }
}
