package com.everday.useapp.network;

import com.everday.useapp.network.http.CallBack;
import com.everday.useapp.network.http.IHttpEngien;
import com.everday.useapp.utils.EverdayLog;

import java.util.Map;

import okhttp3.Call;
import okhttp3.RequestBody;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/28
 * description: 请求类型
 */
public class HttpManager implements IHttpEngien {
    private volatile static HttpManager instance;
    private IHttpEngien engien;
    public Call call;

    private HttpManager() {
    }

    public void init(IHttpEngien engien) {
        this.engien = engien;
    }

    /**
     * 单例模式获取Http
     *
     * @return
     */
    public static HttpManager getInstance() {
        if (null == instance) {
            synchronized (HttpManager.class) {
                if (null == instance) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    @Override
    public void get(String url, Map<String, Object> params, CallBack callBack) {
        engien.get(url, params, callBack);
    }

    @Override
    public void post(String url, CallBack callBack, RequestBody body) {
        engien.post(url, callBack, body);
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