package com.bok.onnal.api;

import com.bok.onnal.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("data/2.5/weather")
    Call<WeatherResponse> getCurrentWeather(
        @Query("lat") double lat,
        @Query("lon") double lon,
        @Query("appid") String apiKey,
        @Query("units") String units,
        @Query("lang") String lang
    );

    @GET("data/2.5/forecast")
    Call<WeatherResponse> getForecast(
        @Query("lat") double lat,
        @Query("lon") double lon,
        @Query("appid") String apiKey,
        @Query("units") String units,
        @Query("lang") String lang
    );
}
