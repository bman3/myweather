package com.bok.onnal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bok.onnal.R;
import com.bok.onnal.model.DailyWeather;
import com.bok.onnal.util.WeatherIconMapper;
import java.util.List;

public class DailyWeatherAdapter extends RecyclerView.Adapter<DailyWeatherAdapter.ViewHolder> {
    private final List<DailyWeather> dailyList;

    public DailyWeatherAdapter(List<DailyWeather> dailyList) {
        this.dailyList = dailyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DailyWeather item = dailyList.get(position);
        holder.date.setText(item.getDateLabel());
        holder.temp.setText(item.getMinTemp() + "° / " + item.getMaxTemp() + "°");
        holder.icon.setImageResource(WeatherIconMapper.getIconRes(item.getIconCode()));
    }

    @Override
    public int getItemCount() {
        return dailyList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date, temp;
        ImageView icon;

        ViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.text_date);
            temp = view.findViewById(R.id.text_temp);
            icon = view.findViewById(R.id.image_icon);
        }
    }
}
