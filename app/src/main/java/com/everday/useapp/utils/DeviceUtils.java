package com.everday.useapp.utils;
/**
 * date:2019/8/28
 * author:Everday
 * email wangtahandsome@gmail.com
 * desc:设备信息
 */
public class DeviceUtils {
    private DeviceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Return the version name of device's system.
     *
     * @return the version name of device's system
     */
    public static String getSDKVersionName() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * Return version code of device's system.
     *
     * @return version code of device's system
     */
    public static int getSDKVersionCode() {
        return android.os.Build.VERSION.SDK_INT;
    }


}
