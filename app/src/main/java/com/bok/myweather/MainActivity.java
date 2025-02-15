package com.bok.myweather;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final int MAP_REQUEST_CODE = 1001;
    private static final String API_KEY = "YOUR_OPENWEATHERMAP_API_KEY";

    private double latitude = 37.5665; // 기본 위치 (서울)
    private double longitude = 126.9780;

    private TextView weatherInfo;
    private Button btnSelectLocation, btnGetWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherInfo = findViewById(R.id.weatherInfo);
        btnSelectLocation = findViewById(R.id.btnSelectLocation);
        btnGetWeather = findViewById(R.id.btnGetWeather);

        // 지도에서 위치 선택
        btnSelectLocation.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivityForResult(intent, MAP_REQUEST_CODE);
        });

        // 날씨 조회
        btnGetWeather.setOnClickListener(v -> fetchWeatherData());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MAP_REQUEST_CODE && resultCode == RESULT_OK) {
            latitude = data.getDoubleExtra("latitude", latitude);
            longitude = data.getDoubleExtra("longitude", longitude);
        }
    }

    private void fetchWeatherData() {
        WeatherService weatherService = RetrofitClient.getRetrofitInstance().create(WeatherService.class);
        Call<WeatherResponse> call = weatherService.getWeather(latitude, longitude, API_KEY, "metric");

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weather = response.body();
                    String info = "온도: " + weather.getMain().getTemp() + "°C\n" +
                            "습도: " + weather.getMain().getHumidity() + "%\n" +
                            "풍속: " + weather.getWind().getSpeed() + " m/s";
                    weatherInfo.setText(info);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("Weather", "네트워크 오류: " + t.getMessage());
            }
        });
    }
}
