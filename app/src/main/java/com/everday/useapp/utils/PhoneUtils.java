package com.everday.useapp.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.everday.useapp.UseApplication;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/7/16
 * description: 手机设备信息
 */
public class PhoneUtils {

    /**
     * 获取手机型号
     * @return
     */
    public static String getPhoneModel() {
        String phoneModel = android.os.Build.MODEL;
        return phoneModel;
    }

    /**
     * 手机厂商
     * @return
     */
    public static String getPhoneProducer() {
        String phoneProducer = android.os.Build.BRAND;
        return phoneProducer;
    }

    /**
     * 获取系统版本号
     *
     * @return
     */
    public static String getSystemVersion() {
        String systemVersion = android.os.Build.VERSION.RELEASE;
        return systemVersion;
    }

    /**
     * SDK 版本
     *
     * @return
     */
    public static int getSDKVersion() {
        int SDKVersion = Build.VERSION.SDK_INT;
        return SDKVersion;
    }

    /**
     * 获取当前本地apk的版本
     * @return
     */
    public static int getVersionCode() {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = UseApplication.getApplication().getPackageManager().
                    getPackageInfo(UseApplication.getApplication().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }


    /**
     * 获取版本号名称
     * @return
     */
    public static String getVersionName() {
        String verName = "";
        try {
            verName = UseApplication.getApplication().getPackageManager().
                    getPackageInfo(UseApplication.getApplication().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    /*
     * 手机品牌
     * */
    public static String getBrand() {
        String brand = Build.BRAND;
        return brand;
    }

    /*
     * 获取手机运行内存
     * */
    public static String getAvailMemory() {// 获取android当前可用内存大小  
        //获取运行内存的信息
        ActivityManager manager = (ActivityManager) UseApplication.getApplication().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        manager.getMemoryInfo(info);

        long totalMem = info.totalMem;
        return totalMem + "";// 将获取的内存大小规格化  
    }

    /*
     * 获取手机总内存
     * */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static String getTotalMemory(){
        final StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long size = statFs.getBlockSizeLong();
        long totalCounts = statFs.getBlockCountLong();
        long totalROMSize = totalCounts *size;
        return totalROMSize+"";
    }

    /*
     * 获取IMEI
     * */
    public static String getIMEI() {
        StringBuilder deviceId = new StringBuilder();
        String imei = "";
        try {
            //实例化TelephonyManager对象
            TelephonyManager telephonyManager = (TelephonyManager) UseApplication.getApplication().getSystemService(Context.TELEPHONY_SERVICE);
            //获取IMEI号
            if (ActivityCompat.checkSelfPermission(UseApplication.getApplication(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return "";
            }
            imei = telephonyManager.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imei;
    }

    /*
     * 获取手机分辨率
     * */
    public static  String getScs (){
        DisplayMetrics metrics = UseApplication.getApplication().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        return width+"*"+height;
    }
}
