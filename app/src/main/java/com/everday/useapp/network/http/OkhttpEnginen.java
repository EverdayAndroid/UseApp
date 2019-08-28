package com.everday.useapp.network.http;

import android.os.Handler;
import android.os.Looper;

import com.everday.useapp.constants.Constants;
import com.everday.useapp.entity.BaseModel;
import com.everday.useapp.utils.NetWorkUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/28
 * description: 数据报文
 */
public class OkhttpEnginen implements IHttpEngien {
    private              OkHttpClient client;
    private              Call         call;
    private Gson gson = new Gson();
    private static final Handler      mHandler = new Handler(Looper.myLooper());

    @Override
    public void get(String url, Map<String, Object> params, final CallBack callBack) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e.getMessage(), 0);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String result = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(result);
                    }
                });
            }
        });
    }

    @Override
    public void post(final String url, final CallBack callBack, RequestBody body) {

        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (NetWorkUtils.isAvailable()) {
                            callBack.onFailure("网络请求异常，请稍后重试", 0);
                        } else {
                            callBack.onFailure("你的网络不给力，请检查网络设置", 0);
                        }

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body() != null ? response.body().string() : null;
                if (result != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            BaseModel baseModel = gson.fromJson(result, BaseModel.class);
                            if(baseModel.getResultCode() == Constants.SUCCESS) {
                                callBack.onSuccess(result);
                            }else{
                                //TODO 其它处理
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public Call getCall() {
        if (call != null) {
            return call;
        } else {
            return null;
        }
    }
}