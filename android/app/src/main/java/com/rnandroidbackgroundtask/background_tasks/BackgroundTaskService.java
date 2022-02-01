package com.rnandroidbackgroundtask.background_tasks;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.facebook.react.HeadlessJsTaskService;

import java.util.Date;

public class BackgroundTaskService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Task();

        return Service.START_STICKY;
    }

    private void Task(){
        System.out.println("BackgroundTaskModule=RUNNING BACKGROUND JOB()=" + new Date());
        Intent myIntent = new Intent(this, BackgroundTaskHeadlessJs.class);
        startService(myIntent);
        HeadlessJsTaskService.acquireWakeLockNow(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}