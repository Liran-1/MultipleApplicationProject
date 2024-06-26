package com.example.common;

import android.os.CountDownTimer;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimerManager {

    private long timePassed = 0;
    private boolean timerRunning = false, timerPaused = false, timerStopped = false, didTimerStart = false, didTimerFinish = false;

    private CountDownTimer countDownTimer;
    private String timerTimeStr;
    private int defaultMinutes = 10;

    public TimerCallback timerCallback;

    public int getDefaultMinutes() {
        return defaultMinutes;
    }

    public interface TimerCallback {
        void updateTimer(String currentTIme);
    }

//    public void setTimerCallback(TimerCallback timerCallback) {
//        this.timerCallback = timerCallback;
//    }

    public TimerManager setTimerCallback(TimerCallback timerCallback) {
        this.timerCallback = timerCallback;
        return this;
    }

    public TimerManager() {
    }

    public void startTimer(int timerMinutesFromInput) {

        if (!didTimerStart) {
            runTimer(timerMinutesFromInput);
            didTimerStart = true;
            timerRunning = true;
            return;
        } else if (timerPaused || timerStopped) {
            runTimer(timerMinutesFromInput);
        }
        timerRunning = true;
        timerStopped = false;
        timerPaused = false;
        startCountDownTimer();
    }

    public void stopTimer() {
        timerRunning = false;
        timerStopped = true;
        timerPaused = false;
        timePassed = 0;
        countDownTimer.cancel();
    }

    public void pauseTimer() {
        timerRunning = false;
        timerStopped = false;
        timerPaused = true;
        countDownTimer.cancel();
    }

    public String getTimerTimeStr() {
        return timerTimeStr;
    }

    public void startCountDownTimer() {
        countDownTimer.start();
    }

    public void runTimer(int timerMinutesFromInput) {
        int minutes = timerMinutesFromInput;            //GET FROM INPUT!!!
        long timerDuration = (TimeUnit.MINUTES.toMillis(minutes) - timePassed), ticksInterval = 5;
        countDownTimer = new CountDownTimer(timerDuration, ticksInterval) {
            long millis = 1000;
            final String timerTimeFormat = "%02d:%02d:%02d";

            @Override
            public void onTick(long millisUntilFinished) {

                long timerMinutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60;
                long timerSeconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60;

                millis = millisUntilFinished - TimeUnit.MINUTES.toMillis(timerMinutes)
                        - TimeUnit.SECONDS.toMillis(timerSeconds);
                if (millis <= 0) {
                    millis = 1000;
                }

                timerTimeStr = String.format(Locale.getDefault(), timerTimeFormat,
                        timerMinutes, timerSeconds, millis);
                timePassed = TimeUnit.MINUTES.toMillis(minutes) - millisUntilFinished;
                timerCallback.updateTimer(timerTimeStr);
            }

            @Override
            public void onFinish() {
                int zero = 0;
                timerTimeStr = String.format(Locale.getDefault(), timerTimeFormat,
                        zero, zero, zero);
                timerCallback.updateTimer(timerTimeStr);
            }
        }.start();

    }

    public boolean isTimerRunning() {
        return timerRunning;
    }

    public boolean isTimerPaused() {
        return timerPaused;
    }

    public boolean isTimerStopped() {
        return timerStopped;
    }

    public boolean isDidTimerStart() {
        return didTimerStart;
    }

    public boolean isDidTimerFinish() {
        return didTimerFinish;
    }
}
