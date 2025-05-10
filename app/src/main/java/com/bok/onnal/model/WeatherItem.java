package com.bok.onnal.model;

public class WeatherItem {
    private final String hourLabel;
    private final int temp;
    private final String iconCode;

    public WeatherItem(String hourLabel, int temp, String iconCode) {
        this.hourLabel = hourLabel;
        this.temp = temp;
        this.iconCode = iconCode;
    }

    public String getHourLabel() { return hourLabel; }
    public int getTemp() { return temp; }
    public String getIconCode() { return iconCode; }
}
