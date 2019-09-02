package com.everday.useapp.activity.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.everday.useapp.R;
import com.everday.useapp.UseApplication;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.entity.UserBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * date:2019/8/29
 * author:Everday
 * email wangtahandsome@gmail.com
 * desc:手机号登陆
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    private int countTime = 60;
    private CountDownTimer downTimer;
    private String phone,code;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("账号登录");
        initListener();
    }

    public void time() {
        downTimer = new CountDownTimer(countTime * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countTime--;
                btnGetCode.setEnabled(false);
                btnGetCode.setText(countTime + "s");
            }

            @Override
            public void onFinish() {
                btnGetCode.setEnabled(true);
                btnGetCode.setText("获取验证码");
                countTime = 60;
            }
        };
        downTimer.start();
    }

    public void initListener() {
        phone = editPhone.getText().toString().trim();
        code = editCode.getText().toString().trim();
        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone = s.toString();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code) ) {
                    btnLogin.setEnabled(false);
                    btnLogin.setClickable(false);
                    btnLogin.setBackgroundResource(R.mipmap.login_uncheck_bg);
                } else {
                    btnLogin.setEnabled(true);
                    btnLogin.setClickable(true);
                    btnLogin.setBackgroundResource(R.mipmap.login_check_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                code = s.toString();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code)) {
                    btnLogin.setEnabled(false);
                    btnLogin.setClickable(false);
                    btnLogin.setBackgroundResource(R.mipmap.login_uncheck_bg);
                } else {
                    btnLogin.setEnabled(true);
                    btnLogin.setClickable(true);
                    btnLogin.setBackgroundResource(R.mipmap.login_check_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }
    
    @OnClick({R.id.text_register, R.id.layout_login_pwd,R.id.btn_get_code,R.id.btn_login})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.text_register:
                ActivityUtils.startActivity(this, RegisteredActivity.class);
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.layout_login_pwd:
                //账户登陆
                ActivityUtils.startActivity(this, LoginAcountActivity.class);
                break;
            case R.id.btn_get_code:
                time();
                break;
        }
    }

    public void login() {
        PreferencesUtils.put(UserConfig.USERNAME, "wt", false);
        BamToast.show(UseApplication.getApplication(),"登录成功");
        finish();
        String phone = editPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            return;
        }
        RequestBody body = new FormBody.Builder()
                .add("phone", phone)
                .add("", "")
                .build();
//        HttpManager.getInstance().post("", this, body);
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        if (isFinishing()) {
            return;
        }
        GsonUtils.getInstance().parseJsonToBean(t, UserBean.class);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
