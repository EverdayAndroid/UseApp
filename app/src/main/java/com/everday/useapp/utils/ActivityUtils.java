package com.everday.useapp.utils;

import android.app.Activity;
import android.content.Intent;

/**
* data 2019/7/8
* author Everday
* email wangtaohandsome@gmail.com
* desc 界面跳转
**/
public class ActivityUtils {

    public static void startActivity(Activity activity,Class<? extends Activity> clazz){
        Intent intent = new Intent(activity,clazz);
        activity.startActivity(intent);
    }
}
