package com.rnandroidbackgroundtask.background_tasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BackgroundTaskReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE = 65556;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, BackgroundTaskService.class);
        i.putExtra("foo", "bar");
        context.startService(i);
    }
}