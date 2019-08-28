package com.everday.useapp.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.everday.useapp.R;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/28
 * description: 启动页广告点
 */
public class BeckyBanner extends View {
    //空心圆半径
    private int mStrokeRadius;
    //实心圆半径
    private int mFillRadius;
    //空心圆颜色
    private int mStrokeColor;
    //实心圆颜色
    private int mFillColor;
    //间距
    private int mDistance;

    private int mTotalCount;
    private int mCurrentCout;
    public BeckyBanner(Context context , @Nullable AttributeSet attrs) {
        super(context,attrs);
        //托管自定义属性的数组
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LwkBanner);
        //遍历获取每一个自定义属性的索引
        for (int i = 0; i < typedArray.length(); i++) {
            int mIndex = typedArray.getIndex(i);
            switch (mIndex){
                //空心圆半径
                case R.styleable.LwkBanner_stroke_circle_radius:
                    mStrokeRadius = typedArray.getInteger(i,80);
                    break;
                //实心圆半径
                case R.styleable.LwkBanner_fill_circle_radius:
                    mFillRadius = typedArray.getInteger(i,80);
                    break;
                //空心圆颜色
                case R.styleable.LwkBanner_stroke_circle_color:
                    mStrokeColor = typedArray.getColor(i, Color.parseColor("#000000"));
                    break;
                //实心圆颜色
                case R.styleable.LwkBanner_fill_circle_color:
                    mFillColor = typedArray.getColor(i, Color.parseColor("#FCDF00"));
                    break;
                //间距
                case R.styleable.LwkBanner_distance:
                    mDistance = typedArray.getInteger(i,50);
                    break;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //实心圆画笔
        Paint fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(mFillColor);
        //空心圆画笔
        Paint strokePaint = new Paint();
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(mStrokeColor);
        for (int i = 0; i < mTotalCount; i++) {
            canvas.drawCircle(getPaddingLeft()+i*(2*mStrokeRadius+mDistance)+mStrokeRadius,getPaddingTop()+mStrokeRadius,mStrokeRadius,strokePaint);
        }
        canvas.drawCircle(getPaddingLeft()+mCurrentCout*(2*mFillRadius+mDistance)+mFillRadius,getPaddingTop()+mFillRadius,mFillRadius,fillPaint);
    }

    private int measureHeight(int heightMeasureSpec){
        int measureHeight = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY){
            measureHeight = size;
        }else {
            measureHeight = 2 * mStrokeRadius + getPaddingBottom() + getPaddingTop();
        }
        return measureHeight;
    }
    private int measureWidth(int widthMeasureSpec){
        int measureWidth = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY){
            measureWidth = size;
        }else {
            measureWidth = getPaddingLeft() + getPaddingRight() + 2 * mTotalCount * mStrokeRadius + (mTotalCount - 1) * mDistance;
        }
        return measureWidth;
    }
    public void getTotal(int totalCount){
        mTotalCount = totalCount;
        invalidate();
    }
    public void getCurrentCount(int currentCount){
        mCurrentCout = currentCount;
        invalidate();
    }
}
