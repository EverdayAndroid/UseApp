package com.everday.useapp.utils;

import android.app.AppOpsManager;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;


import com.everday.useapp.UseApplication;
import com.everday.useapp.entity.PushInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
* data 2019/6/27
* author Everday
* email wangtaohandsome@gmail.com
* desc 消息通知
**/
public class NotificationUtils {
    /**
     * 显示消息通知
     * @param application
     * @param channelId
     * @param pushInfo
     */
    public static void notification(Application application, String channelId,String channeName, PushInfo pushInfo){
        NotificationManager nm = (NotificationManager) application.getSystemService(NOTIFICATION_SERVICE);
        //8.0系统新增消息通道
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(channelId,channeName, NotificationManager.IMPORTANCE_MIN);
            //开启指示灯
            channel.enableLights(true);
            //开启震动
            channel.enableVibration(true);
            //设置指示灯颜色
            channel.setLightColor(Color.RED);
            //设置锁屏之后显示通知
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            //是否显示角标
            channel.setShowBadge(true);
            //设置绕过免打扰模式
            channel.setBypassDnd(true);
            //设置震动频率
            channel.setVibrationPattern(new long[]{100,200,300,400});
            //创建通知渠道
            nm.createNotificationChannel(channel);
        }
        Notification notification = new NotificationCompat.Builder(application.getApplicationContext(),channelId)
                .setContentTitle(pushInfo.getTitle())
                .setContentText(pushInfo.getContentText())
                .setContentInfo(pushInfo.getContentInfo())
                //显示时间
                .setShowWhen(true)
                .build();
        Random random = new Random(100);
        int id = random.nextInt();
        nm.notify(id,notification);
    }

    /**
     * 处理消息通知
     * @param application
     * @param channelId
     * @param pushInfo
     * @param pendingIntent
     */
    public static void notification(Application application, String channelId,String channeName, PushInfo pushInfo, PendingIntent pendingIntent){
        NotificationManager nm = (NotificationManager) application.getSystemService(NOTIFICATION_SERVICE);
        //8.0系统新增消息通道
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //区分消息通知类型
            NotificationChannelGroup channelGroup = new NotificationChannelGroup("groupId","组的名称");


            NotificationChannel channel = new NotificationChannel(channelId,channeName, NotificationManager.IMPORTANCE_MIN);
            //设置渠道属于那些分类
//            channel.setGroup(channelGroup.getId());
            //开启指示灯
            channel.enableLights(true);
            //开启震动
            channel.enableVibration(true);
            //设置指示灯颜色
            channel.setLightColor(Color.RED);
            //设置锁屏之后显示通知
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            //是否显示角标
            channel.setShowBadge(true);
            //设置绕过免打扰模式
            channel.setBypassDnd(true);
            //设置震动频率
            channel.setVibrationPattern(new long[]{100,200,300,400});
            //创建通知渠道
            nm.createNotificationChannel(channel);
        }
        Notification notification = new NotificationCompat.Builder(application.getApplicationContext(),channelId)
                .setContentTitle(pushInfo.getTitle())
                .setContentText(pushInfo.getContentText())
                .setContentInfo(pushInfo.getContentInfo())
                .setContentIntent(pendingIntent)
                //通知更新进度的时候，手机会一直有提示音，加上这句代码后就不会一直有提示音了。
                .setOnlyAlertOnce(true)
                .build();
        Random random = new Random(100);
        int id = random.nextInt();
        nm.notify(id,notification);
    }


    public static NotificationCompat.Builder updateAppNotification(){
        NotificationManager nm = (NotificationManager) UseApplication.getApplication().getSystemService(NOTIFICATION_SERVICE);
        Random random = new Random();
        String channelId = String.valueOf(random.nextInt());
        //8.0系统新增消息通道
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(channelId,"appUpdate", NotificationManager.IMPORTANCE_MIN);
            //开启指示灯
            channel.enableLights(true);
            //开启震动
            channel.enableVibration(true);
            //设置指示灯颜色
            channel.setLightColor(Color.RED);
            //设置锁屏之后显示通知
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            //是否显示角标
            channel.setShowBadge(true);
            //设置绕过免打扰模式
            channel.setBypassDnd(true);
            //设置震动频率
            channel.setVibrationPattern(new long[]{100,200,300,400});
            //创建通知渠道
            nm.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(UseApplication.getApplication().getApplicationContext(),channelId)
                .setPriority(NotificationCompat.PRIORITY_MAX) //设置通知的优先级：最大
                .setProgress(100,0,false)
                .setAutoCancel(true)  //
                .setOngoing(true)
                .setContentTitle("更新")
                .setContentText("正在下载更新APP")
                .setContentInfo("APP更新")
                //显示时间
                .setShowWhen(true);
        nm.notify(1,builder.build());
        return builder;
    }

    /**
     * 通知是否开启
     * @return 开启true 否则false
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean isNotificationEnabled() {
        AppOpsManager appOps = (AppOpsManager) UseApplication.getApplication().getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = UseApplication.getApplication().getApplicationInfo();
        String packageName = UseApplication.getApplication().getApplicationContext().getPackageName();
        int uid = appInfo.uid;
        Class<?> appOpsClass = null;
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method method = appOpsClass.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);
            Field notificationFieldValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION");
            int value = (int) notificationFieldValue.get(Integer.class);
            return ((int) method.invoke(appOps, value, uid, packageName) == AppOpsManager.MODE_ALLOWED);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }


}
