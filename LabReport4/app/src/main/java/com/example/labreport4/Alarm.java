package com.example.labreport4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class Alarm extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast toast = Toast.makeText(context,"Hurry up!!!!", Toast.LENGTH_LONG);

        Toast toast = Toast.makeText(context, Html.fromHtml("<font color='orange' ><b>"+ "Hurry up!!!" + "</b></font>"), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
//        View view = toast.getView();
//        view.setBackgroundResource(R.drawable.custom_background);
//        TextView text = (TextView) view.findViewById(android.R.id.message);
        /*Here you can do anything with above textview like text.setTextColor(Color.parseColor("#000000"));*/
//        text.setTextColor(Color.parseColor("#FFFFFF"));
//        view.setBackgroundColor(Color.parseColor("#F94892"));
        toast.show();
    }
}
