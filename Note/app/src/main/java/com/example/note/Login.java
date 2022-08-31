package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.u_name);
        password = findViewById(R.id.u_pass);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                DBHelper DB = new DBHelper(Login.this);
                if(user.equals("") || pass.equals("")){
                    Toast.makeText(Login.this,"Please enter all the field!!!",Toast.LENGTH_SHORT).show();
                }else{
                    Boolean loginCheck = DB.checkUsernamePassword(user,pass);
                    if(loginCheck){
                        Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Home.class);
                        intent.putExtra("user_name",user);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this,"Login not Successful",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        findViewById(R.id.signup_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = new Intent(Login.this,MainActivity.class);
                startActivity(signupIntent);
            }
        });
    }
}