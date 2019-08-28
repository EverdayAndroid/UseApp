package com.everday.useapp.network;

import com.everday.useapp.constants.Constants;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.Retrofit;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/7/15
 * description: 行测网络框架配置
 */
public class RetrofitClientOne {
    private static RetrofitClientOne instance = null;
    private Retrofit mRetrofit;
    private RetrofitClientOne(){
        mRetrofit = new Retrofit.Builder()
                .client(OkHttpManager.getInstance().getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ResponseConverterFactory.create())
                .baseUrl(Constants.HOSTONE)
                .build();

    }

    public static RetrofitClientOne getInstance(){
        synchronized(RetrofitClient.class){
            if(instance == null){
                instance = new RetrofitClientOne();
            }
        }
        return instance;
    }

    public Retrofit getmRetrofit() {
        return mRetrofit;
    }
}
