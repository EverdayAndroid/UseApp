package com.everday.useapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.util.UUID;

/**
 * date:2019/11/7
 * author:Everday
 * email wangtahandsome@gmail.com
 * desc:图片角度
 */
public class RotateTransformation extends BitmapTransformation {
    private float rotateRotationAngle = 0f;
    public RotateTransformation(Context context,float rotateRotationAngle) {
        super(context);
        this.rotateRotationAngle = rotateRotationAngle;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Matrix matrix = new Matrix();

        matrix.postRotate(rotateRotationAngle);

        return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
    }

    @Override
    public String getId() {
        return UUID.randomUUID().toString();
    }
}
