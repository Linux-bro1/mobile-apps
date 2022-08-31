package com.example.labreport4;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BatteryPercentage extends BroadcastReceiver {
    int percentage,prev,dlt=0;
    @Override
    public void onReceive(Context context, Intent intent) {
        percentage = intent.getIntExtra("level",0);
//        if(dlt==0){
//            prev=percentage;
//            dlt++;
//        }else if(percentage!=prev){
            Toast.makeText(context,"Battery Died " + percentage,Toast.LENGTH_SHORT).show();
//            prev=percentage;
//        }

    }
}
