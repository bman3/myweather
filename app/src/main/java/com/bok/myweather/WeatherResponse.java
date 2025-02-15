package com.bok.myweather;

public class WeatherResponse {
    private Main main;
    private Wind wind;

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public class Main {
        private float temp;
        private int humidity;

        public float getTemp() {
            return temp;
        }

        public int getHumidity() {
            return humidity;
        }
    }

    public class Wind {
        private float speed;

        public float getSpeed() {
            return speed;
        }
    }
}
