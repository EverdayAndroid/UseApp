package com.everday.useapp.network.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/**
* data 2019/6/28
* author Everday
* email wangtaohandsome@gmail.com
* desc 网络配置信息拦截器
**/
public class NetworkInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        return chain.proceed(request);
    }
}
