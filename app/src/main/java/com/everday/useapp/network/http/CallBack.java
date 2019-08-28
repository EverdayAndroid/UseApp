package com.everday.useapp.network.http;

/**
 * Create Time: 2017/7/19
 * Description:
 */

public interface CallBack {
    void onSuccess(String t);
    void onFailure(String message, int error);
//    void onNetError();
}
