package com.example.multiplescreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    TextView text;
    public static final String EXTRA_NAME ="com.example.multiplescreen.Extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.textView);


        ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result != null && result.getResultCode() == RESULT_OK){
                    if(result.getData() != null && result.getData().getStringExtra(Page2.USER) != null){
                        text.setText(result.getData().getStringExtra(Page2.USER));
                        editText.setText("");
                        editText.setHint("Enter your name");
                    }
                }
            }
        });

        button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,Page2.class);

            editText = findViewById(R.id.editText);
            String value = editText.getText().toString();
            intent.putExtra(EXTRA_NAME,value);
            startForResult.launch(intent);
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