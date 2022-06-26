package com.example.multiplescreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Page2 extends AppCompatActivity {

    TextView text;
    Button button2;
    public static final String USER = "com.example.multiplescreen.USER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        text = findViewById(R.id.textView2);
        button2 = findViewById(R.id.button2);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_NAME);
        text.setText(message);

        button2.setOnClickListener(view -> {
            text = findViewById(R.id.textView2);
            String name = text.getText().toString();
            Intent i = new Intent(this,MainActivity.class);
            i.putExtra(USER, name);
            setResult(RESULT_OK, i);
            finish();
        });

    }
//

    @Override
    public void onBackPressed() {

    }
}