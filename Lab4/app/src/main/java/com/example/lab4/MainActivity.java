package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText eName, eCollege,eid;
    Button binsert, bexit, bdisplay;

    String nam, coll,id;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.ename);
        eCollege = findViewById(R.id.ecollege);
        eid = findViewById(R.id.eID);
        binsert = findViewById(R.id.binsert);
        bdisplay = findViewById(R.id.bdisplay);
        bexit = findViewById(R.id.bexit);

        db = openOrCreateDatabase("st_info", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(name VARCHAR,college VARCHAR, id VARCHAR);");

        binsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nam = eName.getText().toString();
                coll = eCollege.getText().toString();
                id = eid.getText().toString();

                db.execSQL("INSERT INTO student VALUES('" + nam + "','" + coll + "','"+ id +"');");
                Toast.makeText(getApplicationContext(), "Row Inserted", Toast. LENGTH_SHORT).show();
            }
        });
        bdisplay.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
             Toast.makeText(getApplicationContext(), "Display clicked", Toast. LENGTH_SHORT).show();
             Intent intent = new Intent(getApplicationContext(), Preview.class);
            startActivity(intent);
            finish();
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