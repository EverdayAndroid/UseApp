package com.everday.useapp.activity.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.everday.useapp.R;
import com.everday.useapp.UseApplication;
import com.everday.useapp.activity.home.HomeActivity;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.entity.UserBean;
import com.everday.useapp.entity.UserInfoBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import okhttp3.MediaType;
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
    @BindView(R.id.box_password)
    CheckBox boxPassword;
    @BindView(R.id.edit_password)
    EditText editPassword;
    private int countTime = 60;
    private CountDownTimer downTimer;
    private String phone, code,password;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
//        tvTitle.setText("账号登录");
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
        password = editPassword.getText().toString().trim();
        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone = s.toString();
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
//        editCode.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                code = s.toString();
//                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code)) {
//                    btnLogin.setEnabled(false);
//                    btnLogin.setClickable(false);
//                    btnLogin.setBackgroundResource(R.mipmap.login_uncheck_bg);
//                } else {
//                    btnLogin.setEnabled(true);
//                    btnLogin.setClickable(true);
//                    btnLogin.setBackgroundResource(R.mipmap.login_check_bg);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = s.toString();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
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

    @OnClick({R.id.text_register, R.id.layout_login_pwd, R.id.btn_get_code, R.id.btn_login,R.id.img_close,R.id.text_forget,R.id.box_password,R.id.text_agreement})
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
            case R.id.box_password:
                editPassword.setTransformationMethod(boxPassword.isChecked() ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                break;
            case R.id.btn_get_code:
                time();
                break;
            case R.id.img_close:
                finish();
                break;
            case R.id.text_forget:
                ActivityUtils.startActivity(this,ForgetPasswordActivity.class);
                break;
            case R.id.text_agreement:
                ActivityUtils.startActivity(this,UseActivity.class);
                break;
        }
    }

    public void login() {
        loadingView.show(getSupportFragmentManager(), "login");
        UserBean userBean = new UserBean();
        userBean.setTele(phone);
        userBean.setPassword(password);
        String gson = GsonUtils.getInstance().toObjectGson(userBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE), gson);
        HttpManager.getInstance().post(Constants.HOST + API.LOGIN, this, requestBody);
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        if (isFinishing()) {
            return;
        }
        UserInfoBean userInfoBean = GsonUtils.getInstance().parseJsonToBean(t, UserInfoBean.class);
        PreferencesUtils.put(UserConfig.USERNAME,userInfoBean.getData().getAppAccount().getNickName(),false);
        PreferencesUtils.put(UserConfig.PASSWORD,password,false);
        PreferencesUtils.put(UserConfig.TOKEN,userInfoBean.getData().getAccessToken(),false);
        PreferencesUtils.put(UserConfig.ID,userInfoBean.getData().getAppAccount().getId(),false);
        PreferencesUtils.put(UserConfig.TELE,phone,false);

        //1未签约，2已签约
        PreferencesUtils.put(UserConfig.SIGN,userInfoBean.getData().getAppAccount().getSign(),false);
        BamToast.show(UseApplication.getApplication(),userInfoBean.getMsg());
        if(userInfoBean.getData().getAppAccount().getStatus() == 1){

        }else if(userInfoBean.getData().getAppAccount().getStatus() == 2){
            ActivityUtils.startActivity(this,HomeActivity.class);
            finish();
        }else if(userInfoBean.getData().getAppAccount().getStatus() == 3){

        }else if(userInfoBean.getData().getAppAccount().getStatus() == 4){

        }else if(userInfoBean.getData().getAppAccount().getStatus() == 5){

        }
        JPushInterface.setAlias(this , 0,userInfoBean.getData().getAppAccount().getTele());
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
        if (isFinishing()) {
            return;
        }
        BamToast.show(this,message);
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
        if (isFinishing()) {
            return;
        }
        BamToast.show(this,message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downTimer != null) {
            downTimer.cancel();
            downTimer = null;
        }
    }
}
