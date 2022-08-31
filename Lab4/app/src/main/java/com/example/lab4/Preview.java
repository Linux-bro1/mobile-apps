package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Preview extends AppCompatActivity {
    TextView tname, tcollege,tId;
    Button bprev, bnext, bback;

    SQLiteDatabase db;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        tname = findViewById(R.id.tname);
        tcollege = findViewById(R.id.tcollege);
        tId = findViewById(R.id.tId);
        bprev = findViewById(R.id.bprev);
        bnext = findViewById(R.id.bnext);
        bback = findViewById(R.id.bback);

        db = openOrCreateDatabase("st_info", MODE_PRIVATE, null);
        final Cursor c = db.rawQuery("select * from student", null);  c.moveToFirst();
        tname.setText(c.getString(c.getColumnIndex("name")));
        tcollege.setText(c.getString(c.getColumnIndex("college")));
        tId.setText(c.getString(c.getColumnIndex("id")));
        bback.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainActivity .class);
            startActivity(intent);
            finish();
        }
        });

        bnext.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            try {
                c.moveToNext();
                tname.setText(c.getString(c.getColumnIndex("name")));
                tcollege.setText(c.getString(c.getColumnIndex("college")));
                tId.setText(c.getString(c.getColumnIndex("id")));
            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Last Record", Toast .LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        });

        bprev.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            try {
                c.moveToPrevious();
                tname.setText(c.getString(c.getColumnIndex("name"))); tcollege.setText(c.getString(c.getColumnIndex("college"))); } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "First Record", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }

        });
    }
}
