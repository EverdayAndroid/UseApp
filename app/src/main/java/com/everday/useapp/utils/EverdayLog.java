package com.everday.useapp.utils;

import android.util.Log;

/**
* @author Everday
* @emil wangtaohandsome@gmail.com
* create at 2019/3/9
* description: 日志打印
*/
public class EverdayLog {
    private static boolean openLog = true;
    private static String TAG = "EverdayLog";
    public static void initOpenLog(boolean isOpen){
        openLog = isOpen;
    }

    /**
     * error日志打印
     * @param msg
     */
    public static void error(String msg){
        if(openLog) {
            Log.e(TAG, msg);
        }
    }
    /**
     * info日志打印
     * @param msg
     */
    public static void info(String msg){
        if(openLog) {
            Log.i(TAG, msg);
        }
    }

}
