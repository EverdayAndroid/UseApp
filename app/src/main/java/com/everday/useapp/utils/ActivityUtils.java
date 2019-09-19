package com.everday.useapp.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

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
    public static void startActivity(Activity activity, Class<? extends Activity> clazz, Bundle bundle){
        Intent intent = new Intent(activity,clazz);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
    public static void startActivityForResult(Activity activity,Class<? extends Activity> clazz){
        Intent intent = new Intent(activity,clazz);
        activity.startActivityForResult(intent,1);
    }
    public static void startActivityForResult(Activity activity,Class<? extends Activity> clazz,Bundle bundle,int requestCode){
        Intent intent = new Intent(activity,clazz);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent,requestCode);
    }
    public static void startActivityForResult(Activity activity,Class<? extends Activity> clazz,int requestCode){
        Intent intent = new Intent(activity,clazz);
        activity.startActivityForResult(intent,requestCode);
    }
}
