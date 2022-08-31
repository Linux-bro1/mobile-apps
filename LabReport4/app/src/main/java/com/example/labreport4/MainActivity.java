package com.example.labreport4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e_name, e_mail, e_mobile,e_pass;
    String name,email,mobile,password;
    Integer temp=0;

    BatteryPercentage batteryPercentage;
    Intent intent;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    SQLiteDatabase db;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batteryPercentage = new BatteryPercentage();
        registerReceiver(batteryPercentage,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        intent=new Intent(getApplicationContext(),Alarm.class);
        pendingIntent= PendingIntent.getBroadcast(getApplicationContext(), 0, intent,0);
        alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(1000),60000, pendingIntent);


        e_name = findViewById(R.id.ename);
        e_mail = findViewById(R.id.e_mail);
        e_mobile = findViewById(R.id.e_mobile);
        e_pass = findViewById(R.id.e_pass);

        db = openOrCreateDatabase("lr4", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users(name Text primary key,email Text,mobile Text,password Text);");

        c = db.rawQuery("select * from users", null);

        findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!e_name.getText().toString().isEmpty() || !e_mobile.getText().toString().isEmpty() || !e_mail.getText().toString().isEmpty()|| !e_pass.getText().toString().isEmpty()) {
                    name = e_name.getText().toString();
                    email = e_mail.getText().toString();
                    mobile = e_mobile.getText().toString();
                    password = e_pass.getText().toString();
                    ContentValues content = new ContentValues();
                    content.put("name",name);
                    content.put("email",email);
                    content.put("mobile",mobile);
                    content.put("password",password);
                    Long result = db.insert("users",null,content);
                    Log.d("Value of result:", String.valueOf(result));

                    if (result!=-1) {
                        e_name.setText("");
                        e_mail.setText("");
                        e_mobile.setText("");
                        e_pass.setText("");
                        Toast.makeText(getApplicationContext(), "Row Inserted", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Found duplicate", Toast.LENGTH_SHORT).show();
                    }
                    temp = 1;
                } else {
                    Toast.makeText(getApplicationContext(), "Empty row, please insert value", Toast.LENGTH_SHORT).show();
                }

            }
        });

        findViewById(R.id.btn_display).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (c.getCount() == 0 && temp==0) {
                    Toast.makeText(getApplicationContext(), "Database is Empty", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), Preview.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        findViewById(R.id.btn_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        findViewById(R.id.btn_faq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FAQ.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(batteryPercentage);
        alarmManager.cancel(pendingIntent);
    }

}