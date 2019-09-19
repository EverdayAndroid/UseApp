package com.everday.useapp.activity.money;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.everday.useapp.R;
import com.everday.useapp.UseApplication;
import com.everday.useapp.activity.money.adapter.WithdrawAdapter;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.DateUtils;
import com.everday.useapp.utils.EverdayLog;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/29
 * description: 钱袋
 */
public class MoneyActivity extends BaseActivity implements OnDateSetListener {
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.mlist)
    RecyclerView recyclerView;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private WithdrawAdapter mAdapter;
    private List mlist;
    private TimePickerDialog mDialogHourMinute;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_money;
    }

    @Override
    public int initStatusColor() {
        return R.color.blue;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("钱袋");
        rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.head_blue));
        ivMessage.setVisibility(View.GONE);
        mlist = new ArrayList(5);
        mlist.add(null);
        mlist.add(null);
        mlist.add(null);
        mlist.add(null);
        mlist.add(null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAdapter = new WithdrawAdapter(R.layout.adapter_withdraw_item, mlist);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        refreshLayout.setRefreshHeader(new MaterialHeader(this));
        initTime();
    }

    @OnClick({R.id.tv_checkDetail, R.id.bt_pickMoney, R.id.tv_date})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_checkDetail:
                //查看明细
                ActivityUtils.startActivity(this, MoneyListActivity.class);
                break;
            case R.id.bt_pickMoney:
                withdraw();
                //提现
//                ActivityUtils.startActivity(this, MoneyWithdrawActivity.class);
                break;
            case R.id.tv_date:
                if (!mDialogHourMinute.isVisible()) {
                    mDialogHourMinute.show(getSupportFragmentManager(), "timeDialog");
                }
                break;
        }
    }

    /**
     * 提现
     */
    public void withdraw() {
        double money = Double.valueOf(tvMoney.getText().toString());
        if (money > -1 && money > 0) {
            //TODO  提现
            HttpManager.getInstance().post("", this, null);
        } else {
            BamToast.show(UseApplication.getApplication(), "余额必须大于0");
        }
    }

    public void initTime() {
        mDialogHourMinute = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH)
                .setMinMillseconds(DateUtils.stringToLong("2000-01", "yyyy-MM"))
                .setMaxMillseconds(System.currentTimeMillis())
                .setTitleStringId("选择时间")
                .setThemeColor(getResources().getColor(R.color.blue))
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.black))
                .setCallBack(this)
                .build();
    }


    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        if (isFinishing()) {
            return;
        }
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
        if (isFinishing()) {
            return;
        }
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
        if (isFinishing()) {
            return;
        }
    }


    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        EverdayLog.error(millseconds + "    " + DateUtils.getCurrentDate());
        if (millseconds == DateUtils.getCurrentDate()) {
            tvDate.setText("本月");
        } else {
            String time = DateUtils.getLongToString(millseconds);
            tvDate.setText(time);
        }
    }

}
