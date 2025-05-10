package com.bok.onnal.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bok.onnal.BuildConfig;
import com.bok.onnal.R;
import com.bok.onnal.api.WeatherApiClient;
import com.bok.onnal.api.WeatherResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    private final Map<String, LatLng> cityLocations = new HashMap<String, LatLng>() {{
        put("Seoul", new LatLng(37.5665, 126.9780));
        put("Busan", new LatLng(35.1796, 129.0756));
        put("Daegu", new LatLng(35.8714, 128.6014));
        put("Daejeon", new LatLng(36.3504, 127.3845));
        put("Gwangju", new LatLng(35.1595, 126.8526));
    }};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.5, 127.5), 6f)); // 전국 중심

        for (Map.Entry<String, LatLng> entry : cityLocations.entrySet()) {
            fetchAndAddWeatherMarker(entry.getKey(), entry.getValue());
        }
    }

    private void fetchAndAddWeatherMarker(String city, LatLng location) {
        WeatherApiClient.getInstance().getWeather(city, "metric", BuildConfig.WEATHER_API_KEY)
            .enqueue(new Callback<WeatherResponse>() {
                @Override
                public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        WeatherResponse weather = response.body();
                        String info = String.format("%s: %.1f°C, %s",
                            weather.getName(),
                            weather.getMain().getTemp(),
                            weather.getWeather().get(0).getDescription()
                        );
                        mMap.addMarker(new MarkerOptions()
                            .position(location)
                            .title(city)
                            .snippet(info));
                    }
                }

                @Override
                public void onFailure(Call<WeatherResponse> call, Throwable t) {
                    // 실패 무시
                }
            });
    }
}
