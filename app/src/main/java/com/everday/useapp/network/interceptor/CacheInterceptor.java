package com.everday.useapp.network.interceptor;

import com.everday.useapp.utils.NetWorkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/**
* data 2019/6/28
* author Everday
* email wangtaohandsome@gmail.com
* desc 缓存拦截器
**/
public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = null;
        //只针对GET进行缓存信息
        if(request.method().equals("GET")){
            //获取缓存信息
            response = chain.proceed(request).cacheResponse();
            //如果缓存信息为null直接获取网络缓存
            if(response == null){
                response = chain.proceed(request).networkResponse();
            }
        }else{
            response = chain.proceed(request);
        }
        return response;
    }
}
