package com.example.labreport4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Preview extends AppCompatActivity {

    TextView d_name, d_mail, d_mobile;
    SQLiteDatabase db;
    Cursor c;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        d_name = findViewById(R.id.d_name);
        d_mail = findViewById(R.id.d_mail);
        d_mobile = findViewById(R.id.d_mobile);


        db = openOrCreateDatabase("lr4", MODE_PRIVATE, null);


        c = db.rawQuery("select * from users", null);
        c.moveToFirst();
        if (c.getCount() == 0) {
            Intent intent = new Intent(Preview.this, MainActivity.class);
            startActivity(intent);
        } else {
            d_name.setText(c.getString(c.getColumnIndex("name")));
            d_mail.setText(c.getString(c.getColumnIndex("email")));
            d_mobile.setText(c.getString(c.getColumnIndex("mobile")));
        }

        findViewById(R.id.btn_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    c.moveToNext();
                    d_name.setText(c.getString(c.getColumnIndex("name")));
                    d_mail.setText(c.getString(c.getColumnIndex("email")));
                    d_mobile.setText(c.getString(c.getColumnIndex("mobile")));

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Last Record", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    c.moveToPrevious();
                    d_name.setText(c.getString(c.getColumnIndex("name")));
                    d_mail.setText(c.getString(c.getColumnIndex("email")));
                    d_mobile.setText(c.getString(c.getColumnIndex("mobile")));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "First Record", Toast.LENGTH_LONG).show();

                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = d_name.getText().toString();
                db.execSQL("delete from users where name = '" + n + "'");
                Toast.makeText(getApplicationContext(), "Row Deleted", Toast.LENGTH_LONG).show();

                if (c.getCount() == 0) {
                    Intent intent = new Intent(Preview.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Preview.this, Preview.class);
                    startActivity(intent);
                }


            }
        });

        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prevName = d_name.getText().toString();
                Intent intent = new Intent(Preview.this, Update.class);
                intent.putExtra("keyname", prevName);
                startActivity(intent);
            }
        });

    }
}