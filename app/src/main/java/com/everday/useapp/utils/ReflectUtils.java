package com.everday.useapp.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/25
 * description: 反射工具类
 */
public class ReflectUtils {
    /**
     * 寻找属性
     * @param instance
     * @param name
     * @return
     * @throws NoSuchFieldException
     */
    public static Field getField(Object instance,String name) throws NoSuchFieldException {
        for (Class aClass = instance.getClass();aClass!=null;aClass.getSuperclass()){
            try {
                Field field = aClass.getDeclaredField(name);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        throw new NoSuchFieldException("");
    }

    /**
     * 反射寻找方法
     * @param instance  类
     * @param name 方法名
     * @return
     * @throws NoSuchMethodException
     */
    public static Method getMethod(Object instance,String name,Class<?>... parameterTypes) throws NoSuchMethodException {
        for (Class clz = instance.getClass();clz!=null;clz.getSuperclass()){
            try {
                Method method = clz.getDeclaredMethod(name, parameterTypes);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        throw new NoSuchMethodException("");
    }
}
