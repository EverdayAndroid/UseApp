package com.everday.useapp.network;

import com.everday.useapp.constants.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
* data 2019/6/28
* author Everday
* email wangtaohandsome@gmail.com
* desc 网络请求封装
**/
public class RetrofitClient {
    private static RetrofitClient instance = null;
    private Retrofit mRetrofit;
    private RetrofitClient(){
        mRetrofit = new Retrofit.Builder()
                .client(OkHttpManager.getInstance().getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ResponseConverterFactory.create())
                .baseUrl(Constants.HOST)
                .build();

    }

    public static RetrofitClient getInstance(){
        synchronized(RetrofitClient.class){
            if(instance == null){
                instance = new RetrofitClient();
            }
        }
        return instance;
    }

    public Retrofit getmRetrofit() {
        return mRetrofit;
    }
}
