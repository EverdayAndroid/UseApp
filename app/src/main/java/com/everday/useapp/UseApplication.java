package com.everday.useapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.network.http.OkhttpEnginen;
import com.everday.useapp.utils.CrashHandler;
import com.everday.useapp.utils.NetWorkUtils;
import com.everday.useapp.utils.PreferencesUtils;
import com.lzy.okgo.OkGo;

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
        application = this;
        NetWorkUtils.init(this);
        PreferencesUtils.initConext(this);
        HttpManager.getInstance().init(new OkhttpEnginen());
        OkGo.getInstance().init(this);
        CrashHandler.getInstance().init(this);
    }

    /**
     * 全局Application
     * @return
     */
    public static Application getApplication(){
        return application;
    }
}
