package com.bok.onnal.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bok.onnal.R;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    public static class WeatherData {
        public final String time;
        public final String temp;
        public final int iconResId;

        public WeatherData(String time, String temp, int iconResId) {
            this.time = time;
            this.temp = temp;
            this.iconResId = iconResId;
        }
    }

    private final List<WeatherData> dataList;

    public WeatherAdapter(List<WeatherData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather, parent, false);
        return new WeatherViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        WeatherData item = dataList.get(position);
        holder.timeText.setText(item.time);
        holder.tempText.setText(item.temp);
        holder.icon.setImageResource(item.iconResId);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class WeatherViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView timeText, tempText;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.weather_icon);
            timeText = itemView.findViewById(R.id.weather_time);
            tempText = itemView.findViewById(R.id.weather_temp);
        }
    }
}
