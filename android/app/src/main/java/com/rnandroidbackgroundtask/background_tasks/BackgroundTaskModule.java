package com.rnandroidbackgroundtask;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.rnandroidbackgroundtask.background_tasks.BackgroundTaskReceiver;
import com.rnandroidbackgroundtask.background_tasks.BootBroadcastReceiver;

import javax.annotation.Nonnull;

public class BackgroundTaskModule extends ReactContextBaseJavaModule {

    public static final String REACT_CLASS = "BackgroundTaskModule";
    private static ReactApplicationContext reactContext;

    public BackgroundTaskModule(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Nonnull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void scheduleTask(int interval) {
        System.out.println("BackgroundTaskModule=scheduleTask");

        enableBootReceiver();

        Intent intent = new Intent(reactContext.getApplicationContext(), BackgroundTaskReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(reactContext, BackgroundTaskReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long firstMillis = System.currentTimeMillis(); // alarm is set right away
        AlarmManager alarm = (AlarmManager) reactContext.getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                interval, pIntent);
    }

    @ReactMethod
    public void cancelTask() {
        System.out.println("BackgroundTaskModule=cancelTask");

        disableBootReceiver();

        Intent intent = new Intent(reactContext.getApplicationContext(), BackgroundTaskReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(reactContext, BackgroundTaskReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) reactContext.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pIntent);
    }

    public void disableBootReceiver(){
        ComponentName receiver = new ComponentName(reactContext, BootBroadcastReceiver.class);
        PackageManager pm = reactContext.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void enableBootReceiver(){
        ComponentName receiver = new ComponentName(reactContext, BootBroadcastReceiver.class);
        PackageManager pm = reactContext.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}