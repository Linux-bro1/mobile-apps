package com.example.lab07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ename, ecollege, std_id;
    Button binsert, bexit, bdisplay;
    String nam, coll, studentid;
    Integer temp=0;

    SQLiteDatabase db;
    Cursor c,c1,c2,c3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ename = findViewById(R.id.ename);
        ecollege = findViewById(R.id.ecollege);
        std_id = findViewById(R.id.std_id);
        binsert = findViewById(R.id.binsert);
        bdisplay = findViewById(R.id.bdisplay);
        bexit = findViewById(R.id.bexit);

        db = openOrCreateDatabase("Mydb", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(name VARCHAR,college VARCHAR,StudentID VARCHAR);");

        c = db.rawQuery("select * from student", null);

        binsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ename.getText().toString().isEmpty() || !ecollege.getText().toString().isEmpty() || !std_id.getText().toString().isEmpty()) {
                    nam = ename.getText().toString();
                    coll = ecollege.getText().toString();
                    studentid = std_id.getText().toString();
                    c1 = db.rawQuery("select * from student where name = '" + nam + "'", null);
                    c2 = db.rawQuery("select * from student where college = '" + coll + "'", null);
                    c3 = db.rawQuery("select * from student where StudentID = '" + studentid + "'", null);

                    if (c1.getCount() == 0 && c2.getCount() == 0 && c3.getCount() == 0) {
                        db.execSQL("INSERT INTO student VALUES('" + nam + "','" + coll + "','" + studentid + "');");
                        Toast.makeText(getApplicationContext(), "Row Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Found duplicate", Toast.LENGTH_SHORT).show();
                    }
                    temp = 1;
                } else {
                    Toast.makeText(getApplicationContext(), "Empty row, please insert value", Toast.LENGTH_SHORT).show();
                }

            }
        });

        bdisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (c.getCount() == 0 && temp==0) {
                    Toast.makeText(getApplicationContext(), "Database is Empty", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), previewactivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        bexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}