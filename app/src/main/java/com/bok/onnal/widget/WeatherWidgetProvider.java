package com.bok.onnal.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.bok.onnal.MainActivity;
import com.bok.onnal.R;

public class WeatherWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int id : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_weather_1x1);
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
            views.setOnClickPendingIntent(R.id.widget_container, pendingIntent);
            views.setTextViewText(R.id.widget_temp, "22°C");
            views.setImageViewResource(R.id.widget_icon, R.drawable.ic_sunny);
            appWidgetManager.updateAppWidget(id, views);
        }
    }

    public static void updateAllWidgets(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(context, WeatherWidgetProvider.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(componentName);
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        // 예시로 텍스트 설정 (필요시 실제 날씨 정보로 대체)
        views.setTextViewText(R.id.widget_text, "날씨 위젯 업데이트됨");

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}
