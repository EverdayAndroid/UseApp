package com.everday.useapp.activity.money;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    }
}
