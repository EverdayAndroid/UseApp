package com.everday.useapp.activity.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.everday.useapp.R;
import com.everday.useapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * date:2019/8/29
 * author:Everday
 * email wangtahandsome@gmail.com
 * desc:注册
 */
public class RegisteredActivity extends BaseActivity {

    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.box_password)
    CheckBox boxPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.text_agreement)
    TextView textAgreement;
    private int countTime = 60;
    private CountDownTimer downTimer;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_registered;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    public void time() {

        downTimer = new CountDownTimer(countTime*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countTime --;
                btnGetCode.setText(countTime+"s");
                btnGetCode.setClickable(false);
            }

            @Override
            public void onFinish() {
                btnGetCode.setText("获取验证码");
                btnGetCode.setClickable(true);
            }
        };
        downTimer.start();
    }

    @OnClick({R.id.btn_get_code})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_get_code:
                time();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(downTimer!=null){
            downTimer.cancel();
        }
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
