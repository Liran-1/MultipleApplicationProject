package com.example.multiapplicationproject;

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
        super.main_TXT_title.setText(R.string.FirstStartStudy);
        super.main_RLO_activity.setBackground(AppCompatResources.getDrawable(this, R.drawable.study_background));
    }

    protected void startTimer() {
//        if(timerManager.isTimerRunning()) {
//            return;
//        }
        super.startTimer();
        super.main_TXT_title.setText(R.string.FirstStartStudy);
    }

    @Override
    protected void pauseTimer() {
//        if(!super.timerManager.isDidTimerStart() || super.timerManager.isTimerStopped()) {
//            return;
//        }
        super.pauseTimer();
        this.main_TXT_title.setText(R.string.PauseStudy);
    }

    @Override
    protected void stopTimer() {
//        if(!super.timerManager.isDidTimerStart() || super.timerManager.isTimerStopped()) {
//            return;
//        }
        super.stopTimer();
        this.main_TXT_title.setText(R.string.StopStudy);
    }

    @Override
    public void updateTimer(String currentTIme, double percentagePassed) {
        main_TXT_timer.setText(currentTIme);
        if (percentagePassed < super.DOUBLE_30_PERCENT) {
            main_TXT_title.setText(R.string.FirstStartStudy);
        }
        super.updateTimer(currentTIme, percentagePassed);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
