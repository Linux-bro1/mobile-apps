package com.example.lr2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button logout = findViewById(R.id.logout);
        Button updatePage = findViewById(R.id.update);

        TextView up_name = findViewById(R.id.name);
        TextView up_email = findViewById(R.id.email);
        TextView up_phone = findViewById(R.id.phone);

//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            String name = bundle.getString("name");
//            String mail = bundle.getString("mail");
//            String number = bundle.getString("number");
//
//            up_name.setText(name);
//            up_email.setText(mail);
//            up_phone.setText(number);
//        }
        logout.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this,MainActivity.class);
            startActivity(intent);
        });
        updatePage.setOnClickListener(view -> {
            Toast.makeText(this,"update button clicked",Toast.LENGTH_SHORT);
            Intent i1 = new Intent(Home.this,Update.class);
            startActivity(i1);
        });
    }
}