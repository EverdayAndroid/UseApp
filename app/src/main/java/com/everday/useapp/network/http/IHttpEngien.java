package com.everday.useapp.network.http;

import java.util.Map;

import okhttp3.Call;
import okhttp3.RequestBody;

/**
 *
 */

public interface IHttpEngien {
    void get(String url, Map<String, Object> params, CallBack callBack);
    void post(String url, CallBack callBack, RequestBody body);
    Call getCall();
}
