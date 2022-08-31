package com.example.lab07;

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

public class previewactivity extends AppCompatActivity {

    TextView tname, tcollege, tid;
    Button bprev, bnext, bback, bdelete, bupdate;

    SQLiteDatabase db;
    Cursor c;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previewactivity);
        tname = findViewById(R.id.tname);
        tcollege = findViewById(R.id.tcollege);
        tid = findViewById(R.id.tid);
        bprev = findViewById(R.id.bprev);
        bnext = findViewById(R.id.bnext);
        bback = findViewById(R.id.bback);
        bdelete = findViewById(R.id.bdelete);
        bupdate = findViewById(R.id.bupdate);

        db = openOrCreateDatabase("Mydb", MODE_PRIVATE, null);

        c = db.rawQuery("select * from student", null);
        c.moveToFirst();
        if (c.getCount() == 0) {
            Intent intent = new Intent(previewactivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            tname.setText(c.getString(c.getColumnIndex("name")));
            tcollege.setText(c.getString(c.getColumnIndex("college")));
            tid.setText(c.getString(c.getColumnIndex("StudentID")));
        }

        bback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
                    tid.setText(c.getString(c.getColumnIndex("StudentID")));

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Last Record", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        bprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    c.moveToPrevious();
                    tname.setText(c.getString(c.getColumnIndex("name")));
                    tcollege.setText(c.getString(c.getColumnIndex("college")));
                    tid.setText(c.getString(c.getColumnIndex("StudentID")));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "First Record", Toast.LENGTH_LONG).show();

                    e.printStackTrace();
                }
            }
        });

        bdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = tname.getText().toString();
                db.execSQL("delete from student where name = '" + n + "'");
                Toast.makeText(getApplicationContext(), "Row Deleted", Toast.LENGTH_LONG).show();

                if (c.getCount() == 0) {
                    Intent intent = new Intent(previewactivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(previewactivity.this, previewactivity.class);
                    startActivity(intent);
                }


            }
        });

        bupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prevName = tname.getText().toString();
                String prevCollege = tcollege.getText().toString();
                String prevId = tid.getText().toString();


                Intent intent = new Intent(previewactivity.this, updatedb.class);
                intent.putExtra("keyname", prevName);
                intent.putExtra("keycollege", prevCollege);
                intent.putExtra("keyid", prevId);
                startActivity(intent);
            }
        });

    }
}