package com.bok.myweather;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private TextView weatherTextView;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherTextView = findViewById(R.id.weatherText);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        requestQueue = Volley.newRequestQueue(this);

        getLocationAndFetchWeather();
    }

    private void getLocationAndFetchWeather() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null) {
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                fetchWeather(lat, lon);
                fetchAirQuality(lat, lon);
            }
        });
    }

    private void fetchWeather(double lat, double lon) {
        String apiKey = "4867cb9492c3efadbfb0ee355451eac5";
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&units=metric&appid=" + apiKey;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        String city = response.getString("name");
                        JSONObject main = response.getJSONObject("main");
                        double temp = main.getDouble("temp");
                        weatherTextView.append("\n[날씨] " + city + ": " + temp + "°C");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> weatherTextView.append("\n날씨 데이터를 불러오지 못했습니다.")
        );

        requestQueue.add(request);
    }

    private void fetchAirQuality(double lat, double lon) {
        String apiKey = "fec1ee23-7e76-4ac0-b35b-f55ab8293a9d";
        String url = "https://api.airvisual.com/v2/nearest_city?lat=" + lat + "&lon=" + lon + "&key=" + apiKey;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONObject data = response.getJSONObject("data");
                        JSONObject current = data.getJSONObject("current");
                        JSONObject pollution = current.getJSONObject("pollution");
                        int aqi = pollution.getInt("aqius");
                        weatherTextView.append("\n[미세먼지] AQI: " + aqi);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> weatherTextView.append("\n미세먼지 데이터를 불러오지 못했습니다.")
        );

        requestQueue.add(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLocationAndFetchWeather();
        } else {
            weatherTextView.setText("위치 권한이 필요합니다.");
        }
    }
}
