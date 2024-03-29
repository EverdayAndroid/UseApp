package com.everday.useapp.activity.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.everday.useapp.R;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.dialog.CertificationDialog;
import com.everday.useapp.dialog.OrderDialog;
import com.everday.useapp.entity.BaseModel;
import com.everday.useapp.entity.TaskBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PreferencesUtils;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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
    private TaskBean taskBean;
    //1接单，2确认完成，3取消
    private int network ;
    //认证
    private CertificationDialog certificationDialog;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_order_details;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("任务详情");
        ivMessage.setVisibility(View.GONE);
        Bundle extras = getIntent().getExtras();
        taskBean = (TaskBean) extras.getSerializable("bean");
        tvName.setText(taskBean.getTaskName());
        tvPrice.setText(taskBean.getYjfy()+"");
        tvPositionName.setText("");
        tvStartTime.setText(taskBean.getStartTime());
        tvEndTime.setText(taskBean.getEndTime());
        tvTime.setText(taskBean.getDuration()+"小时");
        tvPickAddress.setText(taskBean.getAddress());
        tvStartDistance.setText("里程");
        tvJobScrip.setText(taskBean.getDes());
        tvCompanyName.setText(taskBean.getShmc());
        tvNo.setText(taskBean.getTaskBh());
        int type = extras.getInt("type");
        if(type == 1){
            rlBottom.setVisibility(View.VISIBLE);
        }else if(type == 2){
            rlBottom.setVisibility(View.VISIBLE);
            btTakeJob.setVisibility(View.GONE);
            btOk.setVisibility(View.VISIBLE);
            btCancel.setVisibility(View.VISIBLE);
        }else{
            rlBottom.setVisibility(View.GONE);
        }
        certificationDialog = new CertificationDialog();
    }


    @OnClick({R.id.bt_take_job,R.id.bt_ok,R.id.bt_cancel})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.bt_take_job:
                job();
                break;
            case R.id.bt_ok:
                jobComplete();
                break;
            case R.id.bt_cancel:
                jobCancel();
                break;
        }
    }

    /**
     * 接单
     */
    public void job(){
        //判断是否实名认证
        String certification_name = (String) PreferencesUtils.get(UserConfig.CERTIFICATION_NAME,"");
        if(TextUtils.isEmpty(certification_name)){
            certificationDialog.show(getSupportFragmentManager(), "certificationDialog");
            return;
        }
        //判断是否电子签约
        Integer sign = (Integer) PreferencesUtils.get(UserConfig.SIGN, 1);
        if(sign == 1){
            certificationDialog.show(getSupportFragmentManager(), "certificationDialog");
            return;
        }
        network = 0;
        loadingView.show(getSupportFragmentManager(),"loading");
        String gson = "{ \"taskId\":\""+taskBean.getId()+"\"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE),gson);
        HttpManager.getInstance().post(Constants.HOST+ API.TAKETASK,this,requestBody);
    }

    /**
     * 确认完成
     */
    public void jobComplete(){
        network = 1;
        loadingView.show(getSupportFragmentManager(),"loading");
        String gson = "{ \"taskId\":\""+taskBean.getId()+"\",\"tele\":\""+telePhone+"\"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE),gson);
        HttpManager.getInstance().post(Constants.HOST+ API.COMPLETE_TAKETASK,this,requestBody);
    }

    /**
     * 取消
     */
    public void jobCancel(){
        network = 2;
        loadingView.show(getSupportFragmentManager(),"loading");
        String gson = "{ \"taskId\":\""+taskBean.getId()+"\",\"tele\":\""+telePhone+"\"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE),gson);
        HttpManager.getInstance().post(Constants.HOST+ API.CANCEL_TAKETASK,this,requestBody);
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        if(isFinishing()){return;}
        BaseModel baseModel = GsonUtils.getInstance().parseJsonToBean(t, BaseModel.class);
        BamToast.show(baseModel.getMessage());
        finish();
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
        if(isFinishing()){return;}
        if (error == Constants.BUSINESS_ERROR) {
            OrderDialog.getInstance(message).show(getSupportFragmentManager(), "orderDialog");
        }
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
        if(isFinishing()){return;}
    }
}
