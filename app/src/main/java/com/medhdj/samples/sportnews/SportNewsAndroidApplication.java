package com.medhdj.samples.sportnews;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class SportNewsAndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
    }
}
