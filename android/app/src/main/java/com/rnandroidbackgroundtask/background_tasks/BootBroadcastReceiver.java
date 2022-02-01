package com.rnandroidbackgroundtask.background_tasks;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Intent myAlarmIntent = new Intent(context, BackgroundTaskReceiver.class);
            PendingIntent pIntent = PendingIntent.getBroadcast(context, BackgroundTaskReceiver.REQUEST_CODE,
                    myAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            long firstMillis = System.currentTimeMillis(); // alarm is set right away
            AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                    60000L, pIntent);
        }
    }
}