package com.example.common;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Main extends AppCompatActivity {

    protected ImageView main_IMG_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViews();
    }

    private void findViews() {
//        main_IMG_background = findViewById(R.id.main_IMG_background);
    }
}