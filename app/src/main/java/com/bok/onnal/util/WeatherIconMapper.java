package com.bok.onnal.util;

import com.bok.onnal.R;

public class WeatherIconMapper {
    public static int getIconRes(String iconCode) {
        switch (iconCode) {
            case "01d": return R.drawable.ic_sunny;
            case "02d": return R.drawable.ic_partly_cloudy;
            case "03d": return R.drawable.ic_cloudy;
            case "09d": return R.drawable.ic_rain;
            default: return R.drawable.ic_unknown;
        }
    }
}
