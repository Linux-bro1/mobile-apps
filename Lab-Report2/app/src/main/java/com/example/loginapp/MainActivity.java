package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,email,password,cpass;
    Button signup,signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cpass = findViewById(R.id.confirm_password);

        signin = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                String repass = cpass.getText().toString();
                DBHelper DB = new DBHelper(MainActivity.this);

                if(user.equals("") || mail.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(MainActivity.this,"Please enter all the field!!!",Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkUsername(user);
                        if(checkuser){
                            Toast.makeText(MainActivity.this,"User already exists please Sign In!!!",Toast.LENGTH_SHORT).show();
                        }else{
                            Boolean insert = DB.insertData(user,mail,pass);
                            if(insert) {
                                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(MainActivity.this,"Registered not Successfully",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"Password do not match!!!",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }
}