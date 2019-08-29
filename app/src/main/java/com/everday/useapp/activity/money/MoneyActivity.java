package com.everday.useapp.activity.money;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.everday.useapp.R;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/29
 * description: 钱袋
 */
public class MoneyActivity extends BaseActivity {
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_money;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }
    @OnClick({R.id.tv_checkDetail,R.id.bt_pickMoney})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_checkDetail:
                //查看明细
                ActivityUtils.startActivity(this,MoneyListActivity.class);
                break;
            case R.id.bt_pickMoney:
                //体现
                ActivityUtils.startActivity(this,MoneyWithdrawActivity.class);
                break;
        }
    }
}
