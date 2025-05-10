package com.bok.onnal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bok.onnal.R;
import com.bok.onnal.api.ForecastResponse;
import com.bok.onnal.util.WeatherIconMapper;

import java.util.ArrayList;
import java.util.List;

public class TodayAdapter extends RecyclerView.Adapter<TodayAdapter.ViewHolder> {
    private List<ForecastResponse.ForecastItem> items = new ArrayList<>();

    public void setItems(List<ForecastResponse.ForecastItem> newItems) {
        items = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_today, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ForecastResponse.ForecastItem item = items.get(position);
        holder.textTime.setText(item.getDate().substring(11, 16));
        holder.textTemp.setText(String.format("%.1f°C", item.getMain().getTemp()));
        if (!item.getWeather().isEmpty()) {
            holder.imageWeather.setImageResource(WeatherIconMapper.getIconRes(item.getWeather().get(0).getIcon()));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTime, textTemp;
        ImageView imageWeather;

        ViewHolder(View view) {
            super(view);
            textTime = view.findViewById(R.id.text_time);
            textTemp = view.findViewById(R.id.text_temp);
            imageWeather = view.findViewById(R.id.image_weather);
        }
    }
}
