package com.example.common;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public abstract class Activity_Main extends AppCompatActivity {

    protected TextView main_TXT_title;
    protected TextView main_TXT_timer;
    protected MaterialButton main_BTN_start;
    protected MaterialButton main_BTN_pause;
    protected MaterialButton main_BTN_stop;

    protected boolean started = false, paused = false, stopped = false, didTimerStart = false, didTimerFinish = false;
    protected int userMinutes = 10;
    protected long timePassed = 0;
    protected CountDownTimer countDownTimer;
    protected int timerMinutes;
    private TextInputEditText main_ETXT_userTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();
    }

    protected void findViews() {
        main_TXT_title  = findViewById(R.id.main_TXT_title);
        main_TXT_timer  = findViewById(R.id.main_TXT_timer);
        main_BTN_start  = findViewById(R.id.main_BTN_start);
        main_BTN_pause  = findViewById(R.id.main_BTN_pause);
        main_BTN_stop   = findViewById(R.id.main_BTN_stop);
        main_ETXT_userTime = findViewById(R.id.main_ETXT_userTime);
    }

    protected void initViews() {
        runOnUiThread(() -> {
            main_BTN_start.setOnClickListener(v -> {
                startTimer();
            });
            main_BTN_pause.setOnClickListener(v -> {
                pauseTimer();
            });
            main_BTN_stop.setOnClickListener(v -> {
                stopTimer();
            });
        });
    }

    protected void startTimer(){
//        userMinutes = Integer.valueOf(String.valueOf(main_ETXT_userTime.getText()));
//        main_ETXT_userTime.setText("");
    }

    protected void pauseTimer() {

    }

    protected void stopTimer() {
    }


}