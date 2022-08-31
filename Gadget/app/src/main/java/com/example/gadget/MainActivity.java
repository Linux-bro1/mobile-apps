package com.example.gadget;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.ncorti.slidetoact.SlideToActView;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class MainActivity extends AppCompatActivity {
    ProSwipeButton proSwipeBtn;
    SlideToActView sta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        proSwipeBtn = (ProSwipeButton) findViewById(R.id.awesome_btn);
        sta = (SlideToActView) findViewById(R.id.example);

        sta.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(@NonNull SlideToActView slideToActView) {
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
            }
        });
//        proSwipeBtn.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
//            @Override
//            public void onSwipeConfirm() {
//                // user has swiped the btn. Perform your async operation now
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        // task success! show TICK icon in ProSwipeButton
//                        View awesome_btn = findViewById(R.id.awesome_btn);
////                        proSwipeBtn.removeAllViews();
////                        proSwipeBtn.addView(awesome_btn);
////                        frame.removeAllViews();
////                        frame.addView(awesome_btn);
////                        proSwipeBtn.setState(0);
////                        Log.d("Swipe parent view:", String.valueOf(proSwipeBtn.getRootView()));
////                        proSwipeBtn.removeView(proSwipeBtn.getRootView());
////                        proSwipeBtn.addView(awesome_btn);
//
//                        Intent intent = new Intent(MainActivity.this, Home.class);
//                        startActivity(intent);
////                        proSwipeBtn.showResultIcon(true); // false if task failed
//                    }
//                }, 2000);
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sta.resetSlider();
    }
}