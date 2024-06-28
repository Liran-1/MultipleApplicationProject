package com.example.common;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.common.utils.SignalGenerator;
import com.example.common.utils.TimerSharedPreferences;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public abstract class Activity_Main extends AppCompatActivity implements TimerManager.TimerCallback {

    protected TextView main_TXT_title, main_TXT_timer;
    protected MaterialButton main_BTN_start, main_BTN_pause, main_BTN_stop;
    protected RelativeLayout main_RLO_activity;

    protected int timerMinutesFromInput;
    protected TimerManager timerManager;

    protected TimerSharedPreferences timerSharedPreferences;
    protected SignalGenerator signalGenerator;
    protected TextInputEditText main_ETXT_userTime;

    protected final double DOUBLE_30_PERCENT = 0.3, DOUBLE_50_PERCENT = 0.5,
            DOUBLE_85_PERCENT = 0.85, DOUBLE_100_PERCENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();
        timerManager = new TimerManager()
                .setTimerCallback(this);
        TimerSharedPreferences.init(this);
        timerSharedPreferences = TimerSharedPreferences.getInstance();
        SignalGenerator.init(this);
        signalGenerator = SignalGenerator.getInstance();
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

        try {
            timerMinutesFromInput = Integer.parseInt(
                    Objects.requireNonNull(
                                    main_ETXT_userTime.getText())
                            .toString());
            if (timerMinutesFromInput > 60) {
                timerMinutesFromInput = 60;
                signalGenerator.toast("Max value = " + TimerManager.MAX_MINUTES);
            } else if (timerMinutesFromInput < 1) {
//                timerMinutesFromInput = TimerManager.DEFAULT_MINUTES;
                signalGenerator.toast("Time cannot be below 1");
                return;
            }
        } catch (NumberFormatException nfe) {
            timerMinutesFromInput = TimerManager.DEFAULT_MINUTES;
        }
        main_TXT_title.setTextSize(28);
        main_ETXT_userTime.setVisibility(View.GONE);
        main_BTN_start.setVisibility(View.GONE);
        main_BTN_pause.setVisibility(View.VISIBLE);
        main_BTN_stop.setVisibility(View.VISIBLE);

        timerManager.startTimer(timerMinutesFromInput);
//        updateTimer(timerManager.getTimerTimeStr(), percentagePassed);
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
        updateTimer(this.getString(com.example.common.R.string.timer_default), 0);
    }

    @Override
    public void updateTimer(String currentTIme, double percentagePassed) {
        if (percentagePassed >= DOUBLE_30_PERCENT && percentagePassed < DOUBLE_50_PERCENT) {
            main_TXT_title.setText(com.example.common.R.string.WhileRunningAfter30Percent);
        } else if (percentagePassed >= DOUBLE_50_PERCENT && percentagePassed < DOUBLE_85_PERCENT) {
            main_TXT_title.setText(com.example.common.R.string.WhileRunningAfter50Percent);
        } else if (percentagePassed >= DOUBLE_85_PERCENT && percentagePassed < DOUBLE_100_PERCENT) {
            main_TXT_title.setText(com.example.common.R.string.WhileRunningAfter85Percent);
        } else if (percentagePassed == DOUBLE_100_PERCENT) {
            signalGenerator.toast("You finished! WOOHOO");
            signalGenerator.vibrate();
            stopTimer();
            main_TXT_title.setText(com.example.common.R.string.WhileRunningAfter100Percent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadTimePassed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTimePassed(timerManager.getTimePassed());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTimePassed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveTimePassed(0);
    }

    private void saveTimePassed(long timePassed) {
        timerSharedPreferences.putLong(TimerSharedPreferences.TIME_PASSED_KEY, timePassed);
    }

    private void loadTimePassed() {
        long timePassed = timerSharedPreferences.getLong(TimerSharedPreferences.TIME_PASSED_KEY, 0);
        timerManager.setTimePassed(timePassed);
    }


}