package com.everday.useapp.base;


import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.everday.useapp.R;
import com.everday.useapp.activity.login.MessageActivity;
import com.everday.useapp.dialog.LoadingView;
import com.everday.useapp.network.http.CallBack;
import com.everday.useapp.utils.ActivityManagement;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.EverdayLog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/6/29
 * description: 基类
 */
public class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements IActivity,CallBack {
    protected P mPresent;
    private Unbinder mUnbinder;
    //是否隐藏状态栏
    protected boolean hideStatus;
    protected LoadingView loadingView;
    ImageView ivMessage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.problem_AppTheme);
        int layout = initView(savedInstanceState);
        if (layout != 0) {
            loadingView = new LoadingView();
            setContentView(layout);
            ivMessage = findViewById(R.id.iv_message);
            //绑定到butterknife
            mUnbinder = ButterKnife.bind(this);
        }
        ActivityManagement.getInstance().addActivity(this);
        initStatus();
        initData(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagement.getInstance().finishActivity(this);
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        if (mPresent != null) {
            mPresent.onDestroy();
            mPresent = null;
        }
    }


    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return 0;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if(ivMessage!=null){
            ivMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //消息列表
                    ActivityUtils.startActivity(BaseActivity.this, MessageActivity.class);
                }
            });
        }
    }
    @Override
    public void initStatus() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //状态栏透明
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //导航栏透明
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN: 使状态栏出现的时候，不会重新调整activity的高度，状态栏覆盖在activity之上。
            //SYSTEM_UI_FLAG_LAYOUT_STABLE: 让应用的主体内容占用系统状态栏的空间
            //全屏显示
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                //start 流海屏幕适配
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                window.setAttributes(attributes);
            }
            //设置为true就不会出现头部view和状态栏重叠
//            window.getDecorView().setFitsSystemWindows(false);
            //设置状态栏颜色
            window.setStatusBarColor(Color.TRANSPARENT);

            //设置底部虚拟导航栏颜色
            window.setNavigationBarColor(Color.BLACK);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.0系统
            //oppo官方沉浸式适配
            WindowManager.LayoutParams attributes = window.getAttributes();
            //透明状态栏
            attributes.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            window.setAttributes(attributes);
        }
        if (hideStatus()) {
            //隐藏状态栏
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    /**
     * 是否隐藏状态栏
     *
     * @return
     */
    private boolean hideStatus() {
        return hideStatus;
    }

    public void setHideStatus(boolean hideStatus) {
        this.hideStatus = hideStatus;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (loadingView.isAdded() && !loadingView.isHidden()) {
            loadingView.dismiss();
        }
    }

    /**
     * 消息震动
     */
    protected void vibrate(){
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onSuccess(String t) {
        if(isFinishing()){return;}
    }

    @Override
    public void onFailure(String message, int error){
        if(isFinishing()){return;}
    }

    @Override
    public void onThrows(String message, int error) {
        if(isFinishing()){return;}
    }
}
