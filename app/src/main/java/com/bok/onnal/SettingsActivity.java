package com.bok.onnal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {
    private SwitchCompat switchPush, switchTTS;
    private SeekBar intervalSeekBar;
    private TextView intervalText;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        prefs = getSharedPreferences("settings", MODE_PRIVATE);
        switchPush = findViewById(R.id.switch_push);
        switchTTS = findViewById(R.id.switch_tts);
        intervalSeekBar = findViewById(R.id.seekbar_interval);
        intervalText = findViewById(R.id.text_interval);

        switchPush.setChecked(prefs.getBoolean("push", true));
        switchTTS.setChecked(prefs.getBoolean("tts", true));
        int interval = prefs.getInt("interval", 60);
        intervalSeekBar.setProgress(interval);
        intervalText.setText(interval + "분");

        switchPush.setOnCheckedChangeListener((v, b) -> prefs.edit().putBoolean("push", b).apply());
        switchTTS.setOnCheckedChangeListener((v, b) -> prefs.edit().putBoolean("tts", b).apply());

        intervalSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int value = Math.max(5, Math.min(progress, 360));
                intervalText.setText(value + "분");
                prefs.edit().putInt("interval", value).apply();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}
