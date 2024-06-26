package com.example.workout;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.common.Activity_Main;

public class Activity_Panel extends Activity_Main {

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.main_RLO_activity.setBackground(getDrawable(R.drawable.sports_background));
    }


    @Override
    protected void startTimer() {
        super.startTimer();
        super.main_TXT_title.setText(R.string.FirstStartWorkout);
    }

    @Override
    protected void pauseTimer() {
        super.pauseTimer();
        this.main_TXT_title.setText(R.string.PauseWorkout);
    }

    @Override
    protected void stopTimer() {
        super.stopTimer();
        this.main_TXT_title.setText(R.string.StopWorkout);
    }

    @Override
    public void updateTimer(String currentTIme) {

    }
}