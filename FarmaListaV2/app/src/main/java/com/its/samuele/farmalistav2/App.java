package com.its.samuele.farmalistav2;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import roboguice.RoboGuice;

public class App extends Application {

    private static Context appContext;


    @Override
    public void onCreate() {
        super.onCreate();
        initializeContext();
        RoboGuice.setUseAnnotationDatabases(false);
    }

    private void initializeContext(){
        appContext=getApplicationContext();
    }

    public static Context getAppContext(){
        return appContext;
    }
}
