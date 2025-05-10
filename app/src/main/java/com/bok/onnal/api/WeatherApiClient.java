package com.bok.onnal.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiClient {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static WeatherApiService apiService;

    public static WeatherApiService getInstance() {
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            apiService = retrofit.create(WeatherApiService.class);
        }
        return apiService;
    }
}
