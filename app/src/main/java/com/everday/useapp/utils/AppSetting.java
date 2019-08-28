package com.everday.useapp.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.everday.useapp.UseApplication;
/**
 * date:2019/8/28
 * author:Everday
 * email wangtahandsome@gmail.com
 * desc: 系统设置
 */
public class AppSetting {
    /**
     * 系统通知
     */
    public static void gotoNotificationSet() {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 26) {
            // android 8.0引导
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", UseApplication.getApplication().getPackageName());
        } else if (Build.VERSION.SDK_INT >= 21) {
            // android 5.0-7.0
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", UseApplication.getApplication().getPackageName());
            intent.putExtra("app_uid", UseApplication.getApplication().getApplicationInfo().uid);
        } else {
            // 其他
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", UseApplication.getApplication().getPackageName(), null));
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        UseApplication.getApplication().startActivity(intent);
    }
}
