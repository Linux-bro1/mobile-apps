package com.example.lr2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Button up = findViewById(R.id.update);

        EditText eName = findViewById(R.id.e_name);
        EditText eMail = findViewById(R.id.e_mail);
        EditText ePhone = findViewById(R.id.e_number);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = eName.getText().toString().trim();
                String mail = eMail.getText().toString().trim();
                String number = ePhone.getText().toString().trim();
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("mail", mail);
                bundle.putString("number", number);
                Intent in = new Intent(Update.this,Home.class);
                in.putExtras(bundle);
                startActivity(in);
            }
        });
    }
}