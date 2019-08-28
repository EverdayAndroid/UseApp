package com.everday.useapp.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.lang.ref.WeakReference;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
* @author Everday
* @emil wangtaohandsome@gmail.com
* create at 2019/3/5
* description: 版本信息，mac地址
*/

public class AppUtils {
    /**
     * 获取本地软件版本号
     */
    public int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取本地软件版本号名称
     */
    public String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            localVersion = "1.0.0";
        }
        return localVersion;
    }

    /**
     * 根据wifi信息获取本地mac
     * @param context
     * @return
     */
    public static String getLocalMacAddressFromWifiInfo(Context context){
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo winfo = wifi.getConnectionInfo();
        String mac =  winfo.getMacAddress();
        return mac;
    }

    /**
     * 通过网络接口取
     * @return
     */
    public static String getNewMac() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) {continue;}

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return null;
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 安装应用
     * @param activityWeakReference
     * @param mApkFile  apk地址
     * @param code 权限申请Code
     */
    public static void installApk(WeakReference<Activity> activityWeakReference, File mApkFile, int code){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Activity activity = activityWeakReference.get();
        //8.0系统获取是否有安装未知应用权限
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            boolean packageInstalls = activity.getPackageManager().canRequestPackageInstalls();
            if(!packageInstalls){
                // 方法一
                // Uri selfPackageUri = Uri.parse("package:" + activity.getPackageName());
                //Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, selfPackageUri);
                // activity.startActivityForResult(intent, code);
                //方法二
                ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES},code);
            }else{
                intent.setDataAndType(Uri.fromFile(mApkFile), "application/vnd.android.package-archive");
            }
        }else if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.N){
            Uri uriForFile = FileProvider.getUriForFile(activity, activity.getPackageName() + ".fileprovider", mApkFile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        }else{
            intent.setDataAndType(Uri.fromFile(mApkFile), "application/vnd.android.package-archive");
        }
        activity.startActivity(intent);
    }
}
