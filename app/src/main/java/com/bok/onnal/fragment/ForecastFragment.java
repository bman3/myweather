package com.bok.onnal.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bok.onnal.BuildConfig;
import com.bok.onnal.R;
import com.bok.onnal.adapter.ForecastAdapter;
import com.bok.onnal.api.ForecastResponse;
import com.bok.onnal.api.WeatherApiClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastFragment extends Fragment {
    private RecyclerView recyclerView;
    private ForecastAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        recyclerView = view.findViewById(R.id.recycler_forecast);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ForecastAdapter();
        recyclerView.setAdapter(adapter);

        loadForecast();
        return view;
    }

    private void loadForecast() {
        WeatherApiClient.getInstance().getForecast("Seoul", "metric", BuildConfig.WEATHER_API_KEY)
            .enqueue(new Callback<ForecastResponse>() {
                @Override
                public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        LinkedHashMap<String, ForecastResponse.ForecastItem> dailyMap = new LinkedHashMap<>();
                        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
                        SimpleDateFormat outputFormat = new SimpleDateFormat("MM월 dd일", Locale.KOREA);
                        for (ForecastResponse.ForecastItem item : response.body().getList()) {
                            try {
                                Date date = inputFormat.parse(item.getDate());
                                String key = outputFormat.format(date);
                                if (!dailyMap.containsKey(key)) {
                                    dailyMap.put(key, item);
                                }
                                if (dailyMap.size() == 7) break;
                            } catch (Exception ignored) {}
                        }
                        adapter.setItems(new ArrayList<>(dailyMap.values()));
                    }
                }

                @Override
                public void onFailure(Call<ForecastResponse> call, Throwable t) {}
            });
    }
}
