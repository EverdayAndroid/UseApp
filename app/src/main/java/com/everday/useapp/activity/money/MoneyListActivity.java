package com.everday.useapp.activity.money;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.everday.useapp.R;
import com.everday.useapp.base.BaseActivity;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/29
 * description: 查看明细
 */
public class MoneyListActivity extends BaseActivity {

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_money_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("查看明细");
        ivMessage.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        if(isFinishing()){return;}
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
        if(isFinishing()){return;}
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
        if(isFinishing()){return;}
    }
}
