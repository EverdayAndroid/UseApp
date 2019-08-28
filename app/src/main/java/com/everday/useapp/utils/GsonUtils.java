package com.everday.useapp.utils;

import com.google.gson.Gson;
/**
 * date:2019/8/28
 * author:Everday
 * email wangtahandsome@gmail.com
 * desc: gson
 */
public class GsonUtils {
    private static GsonUtils instance;
    private Gson gson;

    public static GsonUtils getInstance() {
        synchronized (GsonUtils.class) {
            if (instance == null) {
                instance = new GsonUtils();
            }
        }
        return instance;
    }

    public GsonUtils() {
        gson = new Gson();
    }

    public <T> T parseJsonToBean(String json, Class<T> cls) {
        T t = null;
        try {
            t = gson.fromJson(json, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return t;
    }
}
