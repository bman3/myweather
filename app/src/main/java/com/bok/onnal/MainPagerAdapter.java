package com.bok.onnal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.bok.onnal.fragment.TodayFragment;
import com.bok.onnal.fragment.ForecastFragment;
import com.bok.onnal.fragment.MapFragment;

public class MainPagerAdapter extends FragmentStateAdapter {
    public MainPagerAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) return new TodayFragment();
        else if (position == 1) return new ForecastFragment();
        else return new MapFragment();
    }
    @Override
    public int getItemCount() {
        return 3;
    }
}
