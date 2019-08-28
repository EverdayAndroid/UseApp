package com.everday.useapp.utils;

import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/7/6
 * description: 刘海屏状态栏适配
 */
public class StatusAdaptation {

    /**
     * 华为 判断是否流海屏接口
     * @param context
     * @return true：是刘海屏false：非刘海屏
     */
    public static boolean hasNotchInScreenHuaWei(Context context){
        boolean ret = false;
        try{
            ClassLoader classLoader = context.getClassLoader();
            Class HwNotchSizeUtil = classLoader.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method hasNotchInScreen = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) hasNotchInScreen.invoke(HwNotchSizeUtil);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            EverdayLog.error( "hasNotchInScreen ClassNotFoundException");
        }catch (NoSuchMethodException e){
            e.printStackTrace();
            EverdayLog.error( "hasNotchInScreen NoSuchMethodException");
        }catch (Exception e){
            EverdayLog.error( "hasNotchInScreen Exception");
        }finally {
            return ret;
        }
    }

    /**
     * 华为 获取刘海屏幕尺寸
     * @param context
     * @return 获取刘海尺：width、heightint； [0]值为刘海宽度int；[1]值为刘海高度
     */
    public static int[] getNotchSize(Context context){
        int[] ret = new int[]{0,0};
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method method = HwNotchSizeUtil.getMethod("getNotchSize");
            ret = (int[]) method.invoke(HwNotchSizeUtil);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            EverdayLog.error( "getNotchSize ClassNotFoundException");
        }catch (NoSuchMethodException e){
            e.printStackTrace();
            EverdayLog.error( "getNotchSize NoSuchMethodException");
        }catch (Exception e){
            EverdayLog.error( "getNotchSize Exception");
        }finally {
            return ret;
        }
    }

    public static final int FLAG_NOTCH_SUPPORT=0x00010000;

    /**
     * 设置应用窗口在华为刘海屏手机使用挖孔区
     * @param window
     */
    public static void setFullScreenWindowLayoutInDisplayCutout(Window window){
        if(window == null){return;}
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        try {
            //动态加载类
            Class layoutParamsExCls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            //获取构造方法
            Constructor con = layoutParamsExCls.getConstructor(WindowManager.LayoutParams.class);
            //实例化类
            Object layoutParamsExObj=con.newInstance(layoutParams);
            //获取方法
            Method method=layoutParamsExCls.getMethod("addHwFlags", int.class);
            //执行方法
            method.invoke(layoutParamsExObj, FLAG_NOTCH_SUPPORT);
        }catch (Exception e){
            e.printStackTrace();
            EverdayLog.error( "setFullScreenWindowLayoutInDisplayCutout Exception");
        }
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelOffset(resId);
        }
        return result;
    }

    /**
     * oppo 判断是否流海屏接口
     * @param context
     * @return
     */
    public static boolean hasNotchInScreenOppo(Context context){
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    /**
     * vivo  判断是否为流海屏
     * @param context
     * @return
     */
    public static boolean hasNotchInScreenVivo(Context context){
        boolean ref = false;
        try{
            ClassLoader classLoader = context.getClassLoader();
            Class FtFeature  = classLoader.loadClass("android.util.FtFeature");
            Method method = FtFeature.getMethod("isFeatureSupport", int.class);
            ref = (boolean) method.invoke(FtFeature);
        }catch (Exception e){
            EverdayLog.error( "setFullScreenWindowLayoutInDisplayCutout Exception");
        }
        return ref;
    }

}
