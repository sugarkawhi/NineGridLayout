package com.icool.ngl;

import android.app.Application;

/**
 * @author zhzy
 * @Description Created by ZhaoZongyao on 2018/8/16.
 */
public class App extends Application {

    private static App mApp;

    public static App getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

}
