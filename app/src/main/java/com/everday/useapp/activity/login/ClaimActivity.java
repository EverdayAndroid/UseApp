package com.everday.useapp.activity.login;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.everday.useapp.R;
import com.everday.useapp.base.BaseActivity;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/10/9
 * description: 接单要求
 */
public class ClaimActivity extends BaseActivity {
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_claim;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("接单要求");
        ivMessage.setVisibility(View.GONE);
    }
}
