package com.bok.onnal.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bok.onnal.BuildConfig;
import com.bok.onnal.R;
import com.bok.onnal.adapter.TodayAdapter;
import com.bok.onnal.api.ForecastResponse;
import com.bok.onnal.api.WeatherApiClient;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class TodayFragment extends Fragment {
    private RecyclerView recyclerView;
    private TodayAdapter adapter;
    private LineChart lineChart;
    private TextView textPm10, textPm25;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, container, false);
        recyclerView = view.findViewById(R.id.recycler_today);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TodayAdapter();
        recyclerView.setAdapter(adapter);

        lineChart = view.findViewById(R.id.line_chart);
        textPm10 = view.findViewById(R.id.text_pm10);
        textPm25 = view.findViewById(R.id.text_pm25);

        loadForecast();
        return view;
    }

    private void loadForecast() {
        WeatherApiClient.getInstance().getForecast("Seoul", "metric", BuildConfig.WEATHER_API_KEY)
            .enqueue(new Callback<ForecastResponse>() {
                @Override
                public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<ForecastResponse.ForecastItem> filtered = new ArrayList<>();
                        List<Entry> chartEntries = new ArrayList<>();
                        int index = 0;
                        for (ForecastResponse.ForecastItem item : response.body().getList()) {
                            if (index % 2 == 0 && filtered.size() < 12) {
                                filtered.add(item);
                                chartEntries.add(new Entry(filtered.size(), item.getMain().getTemp()));
                            }
                            index++;
                        }
                        adapter.setItems(filtered);
                        LineDataSet dataSet = new LineDataSet(chartEntries, "온도(°C)");
                        lineChart.setData(new LineData(dataSet));
                        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                        lineChart.getAxisRight().setEnabled(false);
                        lineChart.invalidate();
                        textPm10.setText("PM10: 40 ㎍/m³ (보통)");
                        textPm25.setText("PM2.5: 20 ㎍/m³ (좋음)");
                    }
                }

                @Override
                public void onFailure(Call<ForecastResponse> call, Throwable t) {}
            });
    }
}
