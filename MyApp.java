package com.csy.demo;

import android.app.Application;
import android.content.Context;

import com.csy.net.utils.Utils;


/**
 * Created by edianzu on 2017/4/18.
 */

public class MyApp extends Application {
    private static MyApp app;
    public static Context getAppContext() {
        return app;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        app=this;


    }
}
