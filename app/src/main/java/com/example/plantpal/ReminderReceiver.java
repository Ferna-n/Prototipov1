package com.example.plantpal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int plantId = intent.getIntExtra("plantId", -1);
        Toast.makeText(context, "Es hora de regar la planta ID: " + plantId, Toast.LENGTH_SHORT).show();
    }
}
