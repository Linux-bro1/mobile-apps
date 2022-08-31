package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText e_name,e_mail,e_mobile,e_pass,e_cp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e_name = findViewById(R.id.e_name);
                e_mail = findViewById(R.id.e_mail);
                e_mobile = findViewById(R.id.e_number);
                e_pass = findViewById(R.id.e_pass);
                e_cp = findViewById(R.id.ec_pass);

                String user = e_name.getText().toString();
                String mail = e_mail.getText().toString();
                String mobile = e_mobile.getText().toString();
                String pass = e_pass.getText().toString();
                String repass = e_cp.getText().toString();
                DBHelper DB = new DBHelper(MainActivity.this);
                if(user.equals("") || mail.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(MainActivity.this,"Please enter all the field!!!",Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkUsername(user);
                        if(checkuser){
                            Toast.makeText(MainActivity.this,"User already exists please Sign In!!!",Toast.LENGTH_SHORT).show();
                        }else{
                            Boolean insert = DB.insertData(user,mail,mobile,pass);
                            if(insert) {
                                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, Home.class);
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
        findViewById(R.id.login_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Login.class);
                startActivity(i);
            }
        });
    }
}