package com.everday.useapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.everday.useapp.utils.NetWorkUtils;
import com.everday.useapp.utils.PreferencesUtils;

public class UseApplication extends Application {
    private static Application application;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkUtils.init(this);
        PreferencesUtils.initConext(this);
    }

    /**
     * 全局Application
     * @return
     */
    public static Application getApplication(){
        return application;
    }
}