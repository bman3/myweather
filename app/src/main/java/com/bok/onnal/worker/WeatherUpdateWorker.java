package com.bok.onnal.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import android.util.Log;

public class WeatherUpdateWorker extends Worker {
    public WeatherUpdateWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("WeatherUpdateWorker", "날씨 데이터 갱신 시작");
        // TODO: API 호출 및 데이터 저장 처리
        return Result.success();
    }
}
