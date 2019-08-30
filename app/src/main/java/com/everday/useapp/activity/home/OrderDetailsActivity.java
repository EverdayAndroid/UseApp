package com.everday.useapp.activity.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.everday.useapp.R;
import com.everday.useapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/29
 * description: 接单详情
 */
public class OrderDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.arrow1)
    ImageView arrow1;
    @BindView(R.id.point1)
    ImageView point1;
    @BindView(R.id.tv_await)
    TextView tvAwait;
    @BindView(R.id.arrow2)
    ImageView arrow2;
    @BindView(R.id.point2)
    ImageView point2;
    @BindView(R.id.tv_watingpay)
    TextView tvWatingpay;
    @BindView(R.id.arrow3)
    ImageView arrow3;
    @BindView(R.id.point3)
    ImageView point3;
    @BindView(R.id.tv_payed)
    TextView tvPayed;
    @BindView(R.id.arrow4)
    ImageView arrow4;
    @BindView(R.id.point4)
    ImageView point4;
    @BindView(R.id.view_gray)
    View viewGray;
    @BindView(R.id.state_line)
    LinearLayout stateLine;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_position_name)
    TextView tvPositionName;
    @BindView(R.id.rl_mian)
    RelativeLayout rlMian;
    @BindView(R.id.tv_time_hint)
    TextView tvTimeHint;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.offine_address)
    TextView offineAddress;
    @BindView(R.id.online_logo)
    ImageView onlineLogo;
    @BindView(R.id.rl_online)
    RelativeLayout rlOnline;
    @BindView(R.id.iv_start_address)
    ImageView ivStartAddress;
    @BindView(R.id.tv_pick_address)
    TextView tvPickAddress;
    @BindView(R.id.tv_start_distance)
    TextView tvStartDistance;
    @BindView(R.id.location)
    RelativeLayout location;
    @BindView(R.id.address_line)
    View addressLine;
    @BindView(R.id.tv_job_scrip)
    TextView tvJobScrip;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_no_hint)
    TextView tvNoHint;
    @BindView(R.id.tv_no)
    TextView tvNo;
    @BindView(R.id.tv_task_state)
    TextView tvTaskState;
    @BindView(R.id.task_state)
    TextView taskState;
    @BindView(R.id.rl_task_state)
    RelativeLayout rlTaskState;
    @BindView(R.id.tv_contract)
    TextView tvContract;
    @BindView(R.id.people)
    TextView people;
    @BindView(R.id.rl_contract)
    RelativeLayout rlContract;
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.cancel_reason)
    TextView cancelReason;
    @BindView(R.id.rl_cancel_reason)
    RelativeLayout rlCancelReason;
    @BindView(R.id.tv_cancel_time)
    TextView tvCancelTime;
    @BindView(R.id.cancel_time)
    TextView cancelTime;
    @BindView(R.id.rl_cancel_time)
    RelativeLayout rlCancelTime;
    @BindView(R.id.tv_pay_time)
    TextView tvPayTime;
    @BindView(R.id.pay_time)
    TextView payTime;
    @BindView(R.id.rl_pay_time)
    RelativeLayout rlPayTime;
    @BindView(R.id.grid_recycler)
    RecyclerView gridRecycler;
    @BindView(R.id.bt_take_job)
    Button btTakeJob;
    @BindView(R.id.bt_ok)
    Button btOk;
    @BindView(R.id.bt_cancel)
    Button btCancel;
    @BindView(R.id.rl_bottom)
    LinearLayout rlBottom;
    @BindView(R.id.ll_main)
    RelativeLayout llMain;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_order_details;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
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
