package com.bok.onnal;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager2 pager = findViewById(R.id.viewPager);
        TabLayout tabs = findViewById(R.id.tabLayout);
        pager.setAdapter(new MainPagerAdapter(this));
        new TabLayoutMediator(tabs, pager, (tab, pos) -> {
            if (pos == 0) tab.setText("오늘");
            else if (pos == 1) tab.setText("5일 예보");
            else tab.setText("지도");
        }).attach();
    }
}
