package com.everday.useapp.activity.login;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.TextUtils;

import com.everday.useapp.R;
import com.everday.useapp.activity.home.HomeActivity;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.PreferencesUtils;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/28
 * description: 启动页
 */
public class SplashActivity extends BaseActivity {

    private Runnable runnable;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        runnable = new Runnable() {
            @Override
            public void run() {
                Message message = mHandler.obtainMessage();
                message.what = 1;
                mHandler.sendMessage(message);
            }
        };
        mHandler.postDelayed(runnable, 3000);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    boolean firstStart = (boolean) PreferencesUtils.get(UserConfig.FIRST_START, true);
                    String tele = (String) PreferencesUtils.get(UserConfig.TELE, "");
//                    if(firstStart){
//                        ActivityUtils.startActivity(SplashActivity.this,AppGuideActivity.class);
//                    }else
                    if (TextUtils.isEmpty(tele)) {
                        ActivityUtils.startActivity(SplashActivity.this, LoginActivity.class);
                    } else {
                        ActivityUtils.startActivity(SplashActivity.this, HomeActivity.class);
                    }
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(runnable);
    }
}
