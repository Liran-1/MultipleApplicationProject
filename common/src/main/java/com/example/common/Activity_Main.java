package com.example.common;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public abstract class Activity_Main extends AppCompatActivity implements TimerManager.TimerCallback {

    protected TextView main_TXT_title;
    protected TextView main_TXT_timer;
    protected MaterialButton main_BTN_start;
    protected MaterialButton main_BTN_pause;
    protected MaterialButton main_BTN_stop;
    protected RelativeLayout main_RLO_activity;

    protected int timerMinutesFromInput;
    protected TimerManager timerManager;

    protected TextInputEditText main_ETXT_userTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();
        timerManager = new TimerManager()
                .setTimerCallback(this);
    }

    protected void findViews() {
        main_RLO_activity = findViewById(R.id.main_RLO_activity);
        main_TXT_title = findViewById(R.id.main_TXT_title);
        main_TXT_timer = findViewById(R.id.main_TXT_timer);
        main_BTN_start = findViewById(R.id.main_BTN_start);
        main_BTN_pause = findViewById(R.id.main_BTN_pause);
        main_BTN_stop = findViewById(R.id.main_BTN_stop);
        main_ETXT_userTime = findViewById(R.id.main_ETXT_userTime);
    }

    protected void initViews() {
        main_BTN_start.setOnClickListener(v -> startTimer());
        main_BTN_pause.setOnClickListener(v -> pauseTimer());
        main_BTN_stop.setOnClickListener(v -> stopTimer());
        main_BTN_pause.setVisibility(View.GONE);
        main_BTN_stop.setVisibility(View.GONE);
    }

    protected void startTimer() {
        main_TXT_title.setTextSize(28);
        main_ETXT_userTime.setVisibility(View.GONE);
        main_BTN_start.setVisibility(View.GONE);
        main_BTN_pause.setVisibility(View.VISIBLE);
        main_BTN_stop.setVisibility(View.VISIBLE);

        try {
            timerMinutesFromInput = Integer.parseInt(
                    Objects.requireNonNull(
                                    main_ETXT_userTime.getText())
                            .toString());
            if (timerMinutesFromInput > 60) {
                timerMinutesFromInput = 60;
            } else if (timerMinutesFromInput == 0) {
                timerMinutesFromInput = timerManager.getDefaultMinutes();
            }
        } catch (NumberFormatException nfe) {
            timerMinutesFromInput = timerManager.getDefaultMinutes();
        }
        timerManager.startTimer(timerMinutesFromInput);
        updateTimer(timerManager.getTimerTimeStr());
    }

    protected void pauseTimer() {
        main_BTN_start.setVisibility(View.VISIBLE);
        main_BTN_pause.setVisibility(View.GONE);
        timerManager.pauseTimer();
    }

    protected void stopTimer() {
        main_BTN_start.setVisibility(View.VISIBLE);
        main_BTN_pause.setVisibility(View.GONE);
        main_BTN_stop.setVisibility(View.GONE);
        main_ETXT_userTime.setVisibility(View.VISIBLE);
        timerManager.stopTimer();
        updateTimer(this.getString(com.example.common.R.string.timer_default));
    }

}