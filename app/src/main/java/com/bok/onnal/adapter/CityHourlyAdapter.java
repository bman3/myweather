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

public class CityHourlyAdapter extends RecyclerView.Adapter<CityHourlyAdapter.ViewHolder> {

    public static class HourlyItem {
        public String time;
        public String temp;
        public int iconRes;

        public HourlyItem(String time, String temp, int iconRes) {
            this.time = time;
            this.temp = temp;
            this.iconRes = iconRes;
        }
    }

    private final List<HourlyItem> data;

    public CityHourlyAdapter(List<HourlyItem> data) {
        this.data = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView timeText, tempText;
        ImageView iconView;

        public ViewHolder(View view) {
            super(view);
            timeText = view.findViewById(R.id.text_time);
            tempText = view.findViewById(R.id.text_temp);
            iconView = view.findViewById(R.id.icon_weather);
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
        holder.tempText.setText(item.temp);
        holder.iconView.setImageResource(item.iconRes);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
