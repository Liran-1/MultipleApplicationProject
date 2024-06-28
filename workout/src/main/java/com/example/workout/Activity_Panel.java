package com.example.workout;

import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;

import com.example.common.Activity_Main;

public class Activity_Panel extends Activity_Main {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        super.main_TXT_title.setText(R.string.FirstStartWorkout);
        this.main_RLO_activity.setBackground(AppCompatResources.getDrawable(this, R.drawable.sports_background));
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
    public void updateTimer(String currentTIme, double percentagePassed) {
        main_TXT_timer.setText(currentTIme);
        if (percentagePassed < super.DOUBLE_30_PERCENT) {
            main_TXT_title.setText(R.string.FirstStartWorkout);
        }
        super.updateTimer(currentTIme, percentagePassed);
    }


}