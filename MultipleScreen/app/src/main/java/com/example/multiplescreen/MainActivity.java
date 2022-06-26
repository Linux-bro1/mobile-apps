package com.example.multiplescreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText text;
    TextView text1;
    public static final String EXTRA_NAME ="com.example.multiplescreen.Extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.textView);


        ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Toast.makeText(MainActivity.this,"on activity", Toast.LENGTH_LONG).show();
                if(result != null && result.getResultCode() == RESULT_OK){
                    Toast.makeText(MainActivity.this,"result code", Toast.LENGTH_LONG).show();
                    if(result.getData() != null && result.getData().getStringExtra(Page2.USER) != null){
                        Toast.makeText(MainActivity.this,"string set", Toast.LENGTH_LONG).show();

                        text1.setText(result.getData().getStringExtra(Page2.USER));
                    }
                }
            }
        });

        button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,Page2.class);
            startForResult.launch(intent);
            text = findViewById(R.id.editText);
            String value = text.getText().toString();
            intent.putExtra(EXTRA_NAME,value);
            startActivity(intent);
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            if(resultCode == RESULT_OK){
//                assert data != null;
//                ;
//            }
//        }
//    }



}