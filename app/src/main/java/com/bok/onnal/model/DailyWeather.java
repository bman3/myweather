package com.bok.onnal.model;

public class DailyWeather {
    private final String dateLabel;
    private final int minTemp;
    private final int maxTemp;
    private final String iconCode;

    public DailyWeather(String dateLabel, int minTemp, int maxTemp, String iconCode) {
        this.dateLabel = dateLabel;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.iconCode = iconCode;
    }

    public String getDateLabel() { return dateLabel; }
    public int getMinTemp() { return minTemp; }
    public int getMaxTemp() { return maxTemp; }
    public String getIconCode() { return iconCode; }
}
