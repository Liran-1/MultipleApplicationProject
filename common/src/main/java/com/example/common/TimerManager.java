package com.example.common;

import android.os.CountDownTimer;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimerManager {

    private long timePassed;
    private boolean timerRunning = false, timerPaused = false, timerStopped = false, didTimerStart = false;

    private CountDownTimer countDownTimer;
    private String timerTimeStr;
    public static final int DEFAULT_MINUTES = 10;

    public TimerCallback timerCallback;
    public static final int MAX_MINUTES = 60;

    public interface TimerCallback {
        void updateTimer(String currentTIme, double percentagePassed);
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


    public long getTimePassed() {
        return timePassed;
    }

    public void setTimePassed(long timePassed) {
        this.timePassed = timePassed;
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

    public void startCountDownTimer() {
        countDownTimer.start();
    }

    public void runTimer(int timerMinutesFromInput) {
        long inputToMillis = TimeUnit.MINUTES.toMillis(timerMinutesFromInput);
        long timerDuration = (inputToMillis - timePassed), ticksInterval = 5;
        countDownTimer = new CountDownTimer(timerDuration, ticksInterval) {
            long millis = 1000;
            double percentagePassed;
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
                timePassed = TimerManager.getTimePassed(timerMinutesFromInput, millisUntilFinished);
                percentagePassed = getPercentagePassed(inputToMillis);
                timerCallback.updateTimer(timerTimeStr, percentagePassed);
            }

            @Override
            public void onFinish() {
                int zero = 0;
                timerTimeStr = String.format(Locale.getDefault(), timerTimeFormat,
                        zero, zero, zero);
                percentagePassed = 1;
                timerCallback.updateTimer(timerTimeStr, percentagePassed);
            }
        }.start();

    }

    private static long getTimePassed(int timerMinutesFromInput, long millisUntilFinished) {
        return TimeUnit.MINUTES.toMillis(timerMinutesFromInput) - millisUntilFinished;
    }

    private double getPercentagePassed(long inputToMills) {
        return (double) timePassed / inputToMills;
    }


}
