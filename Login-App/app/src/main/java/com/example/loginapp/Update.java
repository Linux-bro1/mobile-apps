package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    EditText up_name,up_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper DB = new DBHelper(Update.this);
                up_name=findViewById(R.id.up_name);
                up_email=findViewById(R.id.up_email);
                String name = up_name.getText().toString();
                String email = up_email.getText().toString();
                Intent resultIntent = getIntent();
                String prev_name = resultIntent.getStringExtra("key_user");
                boolean update = DB.updateData(name,email,prev_name);
                if(update){
                    Toast.makeText(Update.this,"Update successfully",Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_home = new Intent(Update.this, HomeActivity.class);
                startActivity(intent_home);
            }
        });

    }
}