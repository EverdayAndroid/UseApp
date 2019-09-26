package com.everday.useapp.hot;

import android.content.Context;

import com.everday.useapp.utils.ReflectUtils;

import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/25
 * description: 热修复
 */
public class EnjoyFix {
    /**
     * 安装补丁包
     * @param context
     * @param patch
     */
    private static void installPacht(Context context,String  patch){
        File odex = context.getDir("odex", Context.MODE_PRIVATE);
        ClassLoader classLoader = context.getClassLoader();
        try {
            //获取系统BaseDexClassLoader 属性
            Field pathList = ReflectUtils.getField(classLoader, "pathList");
            Object dexPathList = pathList.get(classLoader);
            //获取DexPathList类里的属性dexElements
            Field dexElements = ReflectUtils.getField(dexPathList, "dexElements");
            //获取Element类
            Object[] dexElement = (Object[]) dexElements.get(dexElements);

            Method makeDexElements = ReflectUtils.getMethod(dexElement,
                    "makeDexElements", List.class,File.class,List.class);
            File file = new File(patch);
            ArrayList flies = new ArrayList();
            flies.add(file);
            ArrayList<IOException> suppressedExceptions = new ArrayList<>();
            Element[] elements = (Element[]) makeDexElements.invoke(dexElement, null, odex, flies, suppressedExceptions, classLoader);
            Object[] newInstance = (Object[]) Array.newInstance(dexElement.getClass().getComponentType(), dexElement.length + elements.length);
            System.arraycopy(elements,0,newInstance,0,elements.length);
            System.arraycopy(dexElement,0,newInstance,newInstance.length,dexElement.length);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
