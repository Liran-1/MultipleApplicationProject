package com.example.multiapplicationproject;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.example.common.Activity_Main;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Activity_Panel extends Activity_Main {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.main_IMG_background.setImageResource(R.drawable.study_background);
    }

    protected void startTimer() {
        super.startTimer();
        super.main_TXT_title.setText(R.string.FirstStartStudy);
        super.main_TXT_title.setTextSize(24);
        if (!didTimerStart) {
            runTimer();
            didTimerStart = true;
            return;
        } else if (paused) {
            runTimer();
        } else if (stopped) {
            runTimer();
        }
        started = true;
        stopped = false;
        paused = false;
        countDownTimer.start();
    }

    @Override
    protected void pauseTimer() {
        this.main_TXT_title.setText(R.string.PauseStudy);
        super.pauseTimer();
        super.started = false;
        super.stopped = false;
        super.paused = true;
        countDownTimer.cancel();
    }

    @Override
    protected void stopTimer() {
        super.stopTimer();
        super.started = false;
        super.stopped = true;
        super.paused = false;
        Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
        this.main_TXT_title.setText(R.string.StopStudy);
        super.timePassed = 0;
        countDownTimer.cancel();
    }

    public void runTimer() {
        int minutes = userMinutes;            //GET FROM INPUT!!!
        long timerDuration = (TimeUnit.MINUTES.toMillis(minutes) - timePassed), ticksInterval = 5;
        countDownTimer = new CountDownTimer(timerDuration, ticksInterval) {
            long millis = 1000;

            @Override
            public void onTick(long millisUntilFinished) {

                long timerMinutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60;
                long timerSeconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60;

                millis = millisUntilFinished - TimeUnit.MINUTES.toMillis(timerMinutes)
                        - TimeUnit.SECONDS.toMillis(timerSeconds);
                if (millis <= 0) {
                    millis = 1000;
                }

                String timerText = String.format(Locale.getDefault(), "%02d:%02d:%03d",
                        timerMinutes, timerSeconds, millis);
                timePassed = TimeUnit.MINUTES.toMillis(minutes) - millisUntilFinished;
                main_TXT_timer.setText(timerText);
            }

            @Override
            public void onFinish() {
                main_TXT_title.setText(com.example.common.R.string.WhileRunningAfter100Percent);
            }
        }.start();

    }
}
