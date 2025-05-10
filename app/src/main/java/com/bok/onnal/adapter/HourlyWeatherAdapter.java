package com.bok.onnal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bok.onnal.R;
import java.util.List;

public class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyWeatherAdapter.ViewHolder> {
    public static class HourlyItem {
        public String time;
        public String temperature;
        public int weatherIconRes;
        public String dustLevel;

        public HourlyItem(String time, String temperature, int weatherIconRes, String dustLevel) {
            this.time = time;
            this.temperature = temperature;
            this.weatherIconRes = weatherIconRes;
            this.dustLevel = dustLevel;
        }
    }

    private final List<HourlyItem> data;

    public HourlyWeatherAdapter(List<HourlyItem> data) {
        this.data = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView timeText, tempText, dustText;
        ImageView weatherIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            timeText = itemView.findViewById(R.id.text_time);
            tempText = itemView.findViewById(R.id.text_temp);
            dustText = itemView.findViewById(R.id.text_dust);
            weatherIcon = itemView.findViewById(R.id.icon_weather);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hourly_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HourlyItem item = data.get(position);
        holder.timeText.setText(item.time);
        holder.tempText.setText(item.temperature);
        holder.weatherIcon.setImageResource(item.weatherIconRes);
        holder.dustText.setText(item.dustLevel);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
