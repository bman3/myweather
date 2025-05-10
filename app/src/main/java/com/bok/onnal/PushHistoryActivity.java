package com.bok.onnal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Set;

public class PushHistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_history);

        ListView listView = findViewById(R.id.list_history);
        SharedPreferences prefs = getSharedPreferences("history", MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("push_logs", null);
        ArrayList<String> list = new ArrayList<>();
        if (set != null) list.addAll(set);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));
    }
}
