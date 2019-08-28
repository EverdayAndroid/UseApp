package com.everday.useapp.network;

import com.everday.useapp.constants.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/7/15
 * description: 申论网络框架配置
 */
public class RetrofitClientTwo {
    private static RetrofitClientTwo instance = null;
    private Retrofit mRetrofit;
    private RetrofitClientTwo(){
        mRetrofit = new Retrofit.Builder()
                .client(OkHttpManager.getInstance().getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ResponseConverterFactory.create())
                .baseUrl(Constants.HOSTTWO)
                .build();

    }

    public static RetrofitClientTwo getInstance(){
        synchronized(RetrofitClient.class){
            if(instance == null){
                instance = new RetrofitClientTwo();
            }
        }
        return instance;
    }

    public Retrofit getmRetrofit() {
        return mRetrofit;
    }
}
