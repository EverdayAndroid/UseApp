package com.everday.useapp.network.interceptor;

import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.utils.PreferencesUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * data 2019/6/28
 * author Everday
 * email wangtaohandsome@gmail.com
 * desc Token拦截器配置信息
 **/
public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String token = (String) PreferencesUtils.get(UserConfig.TOKEN, "");
        if (!request.url().url().toString().contains("login")) {
            request = request.newBuilder()
                    .addHeader("token", token)
                    .addHeader("User-Agent", "Android")
                    .build();
        }
        return chain.proceed(request);
    }

}
