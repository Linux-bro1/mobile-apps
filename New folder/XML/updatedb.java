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

public class updatedb extends AppCompatActivity {
    EditText ename, ecollege, std_id;
    Button binsert, bexit;
    String nam, coll, studentid;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedb);

        ename = findViewById(R.id.ename);
        ecollege = findViewById(R.id.ecollege);
        std_id = findViewById(R.id.std_id);
        binsert = findViewById(R.id.binsert);
        bexit = findViewById(R.id.bexit);

        db = openOrCreateDatabase("Mydb", MODE_PRIVATE, null);
        String upname = getIntent().getStringExtra("keyname");


        binsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ename.getText().toString().isEmpty() || !ecollege.getText().toString().isEmpty() || !std_id.getText().toString().isEmpty()) {
                    Cursor c = db.rawQuery("select * from student where name = '" + upname + "'",null);
                    nam = ename.getText().toString();
                    coll = ecollege.getText().toString();
                    studentid = std_id.getText().toString();

                    db.execSQL("UPDATE   student where name = '" + nam + "'");
                    db.execSQL("UPDATE   student where name = '" + coll + "'");
                    db.execSQL("UPDATE   student where name = '" + studentid + "'");
                    Toast.makeText(getApplicationContext(), "Value Updated", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(updatedb.this, previewactivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Empty row, please insert value", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}