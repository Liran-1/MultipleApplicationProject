package com.example.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class TimerSharedPreferences {
    private static final String DB_FILE = "DB_FILE";
    public static final String TIME_PASSED_KEY = "TIME_PASSED_KEY";

    private static TimerSharedPreferences instance = null;
    private SharedPreferences preferences;

    private TimerSharedPreferences(Context context) {
        preferences = context.getSharedPreferences(DB_FILE, Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (instance == null)
            instance = new TimerSharedPreferences(context);
    }

    public static TimerSharedPreferences getInstance() {
        return instance;
    }

    public void putLong(String key, long value) {
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLong(String key, long defValue) {
        return preferences.getLong(key, defValue);
    }
}
