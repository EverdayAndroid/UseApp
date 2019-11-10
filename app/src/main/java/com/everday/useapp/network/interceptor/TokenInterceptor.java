package com.everday.useapp.network.interceptor;

import com.everday.useapp.constants.API;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.utils.DateUtils;
import com.everday.useapp.utils.EverdayLog;
import com.everday.useapp.utils.PreferencesUtils;

import java.io.IOException;
import java.net.URL;

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
    private long millis = 0L;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String token = (String) PreferencesUtils.get(UserConfig.TOKEN, "");
        URL url = request.url().url();
        if (!url.toString().contains(API.LOGIN) //登陆
                && !url.toString().contains(API.MERCHANT) //商户
                && !url.toString().contains(API.UPDATEBYVERSION) //版本更新
                && !url.toString().contains(API.REGISTER_SENDCODE) //短信验证码
                && !url.toString().contains(API.SENDCODE)//短信验证码
                && !url.toString().contains(API.BANKCARD_SENDCODE)//短信验证码
                && !url.toString().contains(API.REGISTER)) {//注册
            request = request.newBuilder()
                    .addHeader("token", token)
                    .addHeader("User-Agent", "Android")
                    .build();
        }
        millis = System.currentTimeMillis();
        EverdayLog.error("TokenInterceptor：" + DateUtils.getLongToString("yyyy-MM-dd HH:mm:ss", millis));
        return chain.proceed(request);
    }

}
