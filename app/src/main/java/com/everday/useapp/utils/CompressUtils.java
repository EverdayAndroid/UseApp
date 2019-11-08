package com.everday.useapp.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/11/5
 * description: 图片压缩处理
 */
public class CompressUtils {

    /**
     * 质量压缩
     *
     * @param bitmap    图片原路径
     * @param tagerFile 图片输出路径
     */
    public static void compressQuality(Bitmap bitmap, File tagerFile) {
        //0-100  100为不压缩
        int options = 100;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.size() / 1024 > 300 ) {
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(tagerFile);
            fileOutputStream.write(baos.toByteArray());
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 采样率压缩
     *
     * @param filePath
     * @param tagerFile
     */
    public static void compressSample(String filePath, File tagerFile) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int inSampleSize = 1;
        int widht = options.outWidth;
        int height = options.outHeight;
        while (widht / inSampleSize > 1000) {
            inSampleSize *= 2;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

        compressQuality(bitmap,tagerFile);
    }

    /**
     * 获取图片当前角度
     * @param filePath
     * @return
     */
    public static int degree(String filePath){
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(filePath);
            degree = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL);
            switch (degree){
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 图片旋转角度
     * @param bitmap
     * @param degree
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap bitmap,int degree){
        if(degree == 0 || null == bitmap){
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(degree,bitmap.getWidth()/2,bitmap.getHeight()/2);
        Bitmap bmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        bitmap.recycle();
        return bmp;
    }

}
