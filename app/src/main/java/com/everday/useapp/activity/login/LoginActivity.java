package com.everday.useapp.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.everday.useapp.R;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.utils.ActivityUtils;

import butterknife.OnClick;

/**
 * date:2019/8/29
 * author:Everday
 * email wangtahandsome@gmail.com
 * desc:登陆
 */
public class LoginActivity extends BaseActivity {

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @OnClick({R.id.text_register})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.text_register:
                ActivityUtils.startActivity(this,RegisteredActivity.class);
                break;
        }
    }
}
