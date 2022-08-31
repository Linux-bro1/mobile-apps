package com.example.labreport4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    EditText up_name, up_mail, up_mobile,up_password;
    String name, email, mobile, password;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        up_name = findViewById(R.id.up_name);
        up_mail = findViewById(R.id.up_mail);
        up_mobile = findViewById(R.id.up_mobile);
        up_password = findViewById(R.id.up_pass);

        db = openOrCreateDatabase("lr4", MODE_PRIVATE, null);
        String upname = getIntent().getStringExtra("keyname");


        findViewById(R.id.btn_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!up_name.getText().toString().isEmpty() || !up_mail.getText().toString().isEmpty() ||
                        !up_mobile.getText().toString().isEmpty() || !up_password.getText().toString().isEmpty()) {
//                    Cursor c = db.rawQuery("select * from student where name = '" + upname + "'",null);
                    name = up_name.getText().toString();
                    email = up_mail.getText().toString();
                    mobile = up_mobile.getText().toString();
                    password = up_password.getText().toString();

//                    db.execSQL("UPDATE   users where name = '" + name + "'");
//                    db.execSQL("UPDATE   student where name = '" + email + "'");
//                    db.execSQL("UPDATE   student where name = '" + mobile + "'");
                    ContentValues content = new ContentValues();
                    content.put("name",name);
                    content.put("email",email);
                    content.put("mobile",mobile);
                    content.put("password",password);
                    db.update("users",content,"name=?",new String[]{upname});
                    Toast.makeText(getApplicationContext(), "Value Updated", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Update.this, Preview.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Empty row, please insert value", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}