package com.everday.useapp.network.http;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import com.everday.useapp.UseApplication;
import com.everday.useapp.activity.login.LoginActivity;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.entity.BaseModel;
import com.everday.useapp.network.OkHttpManager;
import com.everday.useapp.utils.DateUtils;
import com.everday.useapp.utils.EverdayLog;
import com.everday.useapp.utils.NetWorkUtils;
import com.everday.useapp.utils.PreferencesUtils;
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
    private OkHttpClient client = OkHttpManager.getInstance().getOkHttpClient();
    private Call call;
    private Gson gson = new Gson();
    private static final Handler mHandler = new Handler(Looper.myLooper());
    long millis = 0L;
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
                millis = System.currentTimeMillis();
                EverdayLog.error("onFailure："+ DateUtils.getLongToString("yyyy-MM-dd HH:mm:ss",millis));
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            callBack.onFailure(e.getMessage(), 0);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            callBack.onThrows(e1.getMessage()+"",Constants.THROWS_CODE);
                        }
                    }
                });
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                final String result = response.body().string();
                millis = System.currentTimeMillis();
                EverdayLog.error("onResponse："+ DateUtils.getLongToString("yyyy-MM-dd HH:mm:ss",millis));
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            callBack.onSuccess(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                            callBack.onThrows(e.getMessage()+"",Constants.THROWS_CODE);
                        }
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
                millis = System.currentTimeMillis();
                EverdayLog.error("onFailure："+ DateUtils.getLongToString("yyyy-MM-dd HH:mm:ss",millis));
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (NetWorkUtils.isAvailable()) {
                                callBack.onFailure("网络请求异常，请稍后重试", Constants.NO_NET_WORK);
                            } else {
                                callBack.onFailure("你的网络不给力，请检查网络设置", Constants.NO_NET_WORK);
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            callBack.onThrows(e1.getMessage()+"",Constants.THROWS_CODE);
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body() != null ? response.body().string() : null;
                millis = System.currentTimeMillis();
                EverdayLog.error("onResponse："+ DateUtils.getLongToString("yyyy-MM-dd HH:mm:ss",millis));
                if (result != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                EverdayLog.error(result);
                                BaseModel baseModel = gson.fromJson(result, BaseModel.class);
                                if (baseModel.getResultCode() == Constants.SUCCESS) {
                                    callBack.onSuccess(result);
                                } else if(baseModel.getResultCode() == Constants.TOKEN_ERROR){
                                    //Token失效
                                    Intent intent = new Intent();
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.setClass(UseApplication.getApplication(), LoginActivity.class);
                                    UseApplication.getApplication().startActivity(intent);
                                }else if(baseModel.getResultCode() == Constants.BUSINESS_ERROR){
                                    //业务失败状态码
                                    callBack.onFailure(baseModel.getMessage(),baseModel.getResultCode());
                                }else if(baseModel.getResultCode() == Constants.IDENTITY_ERROR){
                                    //身份证验证失效状态码
                                    callBack.onFailure(baseModel.getMessage(),baseModel.getResultCode());
                                }else {
                                    callBack.onFailure(baseModel.getMessage(),baseModel.getResultCode());
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                                callBack.onThrows(e.getMessage()+"",Constants.THROWS_CODE);
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
