package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class HomeActivity extends AppCompatActivity {
    SQLiteDatabase db;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent resultIntent = getIntent();
        String result = resultIntent.getStringExtra("user_name");

        if(!result.equals("")){
            //        String result="User ";
//        result += " log in";
            TextView un = findViewById(R.id.u_name);
            TextView ue = findViewById(R.id.u_email);
//        un.setText(result);
//        db = openOrCreateDatabase("users", MODE_PRIVATE, null);
            DBHelper DB = new DBHelper(HomeActivity.this);
            Cursor c = DB.getData(result);
//        final Cursor c = db.rawQuery("select * from users where username=?",new String[]{result} );
            c.moveToFirst();
            un.setText(c.getString(c.getColumnIndex("username")));
            ue.setText(c.getString(c.getColumnIndex("email")));
        }
        findViewById(R.id.up_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_up = new Intent(HomeActivity.this, Update.class);
                intent_up.putExtra("key_user",result);
                startActivity(intent_up);
            }
        });
//        System.out.println(c.getString(c.getColumnIndex("username")));

    }
}