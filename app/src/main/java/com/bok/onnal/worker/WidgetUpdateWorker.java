package com.bok.onnal.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.bok.onnal.widget.WeatherWidgetProvider;

public class WidgetUpdateWorker extends Worker {

    public WidgetUpdateWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        WeatherWidgetProvider.updateAllWidgets(getApplicationContext());
        return Result.success();
    }
}
