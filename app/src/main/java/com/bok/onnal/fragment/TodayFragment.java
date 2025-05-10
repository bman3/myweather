package com.bok.onnal.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bok.onnal.R;
import com.bok.onnal.ui.WeatherAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class TodayFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_today);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<WeatherAdapter.WeatherData> weatherList = new ArrayList<>();
        weatherList.add(new WeatherAdapter.WeatherData("03:00", "21.9°C", R.drawable.ic_sunny));
        weatherList.add(new WeatherAdapter.WeatherData("09:00", "21.6°C", R.drawable.ic_cloud));
        weatherList.add(new WeatherAdapter.WeatherData("15:00", "16.6°C", R.drawable.ic_rain));
        weatherList.add(new WeatherAdapter.WeatherData("21:00", "14.5°C", R.drawable.ic_cloud));

        WeatherAdapter adapter = new WeatherAdapter(weatherList);
        recyclerView.setAdapter(adapter);

        LineChart lineChart = view.findViewById(R.id.line_chart);
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 12.8f));
        entries.add(new Entry(1, 19.5f));
        entries.add(new Entry(2, 13.2f));
        entries.add(new Entry(3, 21.9f));
        entries.add(new Entry(4, 23.1f));

        LineDataSet dataSet = new LineDataSet(entries, "온도(°C)");
        dataSet.setDrawFilled(true);
        dataSet.setDrawCircles(true);
        dataSet.setCircleRadius(4f);
        dataSet.setColor(Color.BLUE);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setDrawValues(false);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        lineChart.getAxisRight().setEnabled(false);

        lineChart.getLegend().setEnabled(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.invalidate();

        return view;
    }
}
