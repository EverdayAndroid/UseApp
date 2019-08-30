package com.everday.useapp.network.http;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/30
 * description: 网络回调接口
 */
public interface CallBack {
    void onSuccess(String t) throws Exception;
    void onFailure(String message, int error) throws Exception;
    void onThrows(String message,int error);
//    void onNetError();
}
