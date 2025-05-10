package com.bok.onnal.receiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import androidx.core.app.NotificationCompat;

import com.bok.onnal.MainActivity;
import com.bok.onnal.R;

import java.util.Locale;

public class TipPushReceiver extends BroadcastReceiver {
    private TextToSpeech tts;

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = "오늘의 날씨에 맞는 팁을 확인하세요!";

        // 푸시 알림 생성
        String channelId = "weather_tip_channel";
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "날씨 팁 알림", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        Intent notifyIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_sunny)
            .setContentTitle("온날 - 날씨 팁")
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true);

        manager.notify(1001, builder.build());

        // 음성 안내
        tts = new TextToSpeech(context.getApplicationContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.KOREAN);
                tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }
}
