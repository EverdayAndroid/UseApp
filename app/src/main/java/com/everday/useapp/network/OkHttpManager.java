package com.everday.useapp.network;



import com.everday.useapp.constants.Constants;
import com.everday.useapp.network.interceptor.CacheInterceptor;
import com.everday.useapp.network.interceptor.DeviceInterceptor;
import com.everday.useapp.network.interceptor.TokenInterceptor;
import com.everday.useapp.utils.FileUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
/**
* data 2019/6/28
* author Everday
* email wangtaohandsome@gmail.com
* desc okhttp配置信息
**/
public class OkHttpManager {
    private OkHttpClient okHttpClient;

    private static OkHttpManager instance = null;

    private OkHttpManager(){
        //缓存路径
        Cache cache = new Cache(FileUtils.getInstance().getCacheDir(), Constants.CACHESIZE);
        okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(Constants.CONNECTTIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.WRITETIMEOUT,TimeUnit.SECONDS)
                .readTimeout(Constants.READTIMEOUT,TimeUnit.SECONDS)
                .addInterceptor(new TokenInterceptor())
                .addInterceptor(new CacheInterceptor())
                .addInterceptor(new DeviceInterceptor())
                .cache(cache)
                .build();
        okHttpClient.dispatcher().cancelAll();
    }

    public static OkHttpManager getInstance(){
        synchronized(OkHttpManager.class){
            if(instance == null){
                instance = new OkHttpManager();
            }
        }
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }}
