package com.example.multiplescreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Page2 extends AppCompatActivity {

    EditText editText2;
    TextView text2;
    Button button2;
    public static final String USER = "com.example.multiplescreen.USER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        editText2 = findViewById(R.id.editText2);
        text2 = findViewById(R.id.textView2);
        button2 = findViewById(R.id.button2);

        Intent resultIntent = getIntent();
        String result = resultIntent.getStringExtra(MainActivity.EXTRA_NAME);
        text2.setText(result);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText2.getText().toString();
                Intent i = new Intent();
                i.putExtra(USER, name);
                setResult(RESULT_OK, i);
                finish();
            }
        });



//        if (editText2.getText().toString().equals("")) {
//            Bundle bundle1 = getIntent().getExtras();
//            if (bundle1 != null) {
//                Toast.makeText(this,"Hit bundle again",Toast.LENGTH_SHORT).show();
//                String message = bundle1.getString(MainActivity.EXTRA_NAME);
//                text2.setText(message);
//            }
//        }

    }
}