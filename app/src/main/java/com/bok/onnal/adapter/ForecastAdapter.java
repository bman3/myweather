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

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {
    public static class ForecastItem {
        public String day;
        public int weatherIconRes;
        public String tempRange;

        public ForecastItem(String day, int icon, String tempRange) {
            this.day = day;
            this.weatherIconRes = icon;
            this.tempRange = tempRange;
        }
    }

    private final List<ForecastItem> data;

    public ForecastAdapter(List<ForecastItem> data) {
        this.data = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDay, textTemp;
        ImageView imageWeather;

        public ViewHolder(View view) {
            super(view);
            textDay = view.findViewById(R.id.text_day);
            imageWeather = view.findViewById(R.id.image_weather);
            textTemp = view.findViewById(R.id.text_temp);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_daily_forecast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ForecastItem item = data.get(position);
        holder.textDay.setText(item.day);
        holder.textTemp.setText(item.tempRange);
        holder.imageWeather.setImageResource(item.weatherIconRes);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
