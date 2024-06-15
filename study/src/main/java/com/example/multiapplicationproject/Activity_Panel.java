package com.example.multiapplicationproject;

import android.os.Bundle;

import com.example.common.Activity_Main;

public class Activity_Panel extends Activity_Main {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.main_IMG_background.setImageResource(R.drawable.study_background);
    }
}