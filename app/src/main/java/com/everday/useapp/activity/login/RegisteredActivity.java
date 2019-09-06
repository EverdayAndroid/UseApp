package com.everday.useapp.activity.login;

import android.content.Intent;
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
import android.widget.TextView;

import com.everday.useapp.R;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.entity.BaseModel;
import com.everday.useapp.entity.Code;
import com.everday.useapp.entity.CodeInfoBean;
import com.everday.useapp.entity.ForgetPasswordBean;
import com.everday.useapp.entity.ForgetPasswordInfoBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.GsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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
    @BindView(R.id.tv_merchant)
    TextView tvMerchant;
    private int countTime = 60;
    private CountDownTimer downTimer;
    private String phone, code, password;
    //请求标识
    private int netCode;
    //商户id
    private int shId;
    //商户名称
    private String shmc;
    //验证码业务标识
    private String bizId;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_registered;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("注册");
        ivMessage.setVisibility(View.GONE);
        initListener();
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
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code) || TextUtils.isEmpty(password)) {
                    btnRegister.setEnabled(false);
                    btnRegister.setClickable(false);
                    btnRegister.setBackgroundResource(R.mipmap.login_uncheck_bg);
                } else {
                    btnRegister.setEnabled(false);
                    btnRegister.setClickable(false);
                    btnRegister.setBackgroundResource(R.mipmap.login_uncheck_bg);
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
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code) || TextUtils.isEmpty(password)) {
                    btnRegister.setEnabled(false);
                    btnRegister.setClickable(false);
                    btnRegister.setBackgroundResource(R.mipmap.login_uncheck_bg);
                } else {
                    btnRegister.setEnabled(false);
                    btnRegister.setClickable(false);
                    btnRegister.setBackgroundResource(R.mipmap.login_uncheck_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = s.toString();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code) || TextUtils.isEmpty(password)) {
                    btnRegister.setEnabled(false);
                    btnRegister.setClickable(false);
                    btnRegister.setBackgroundResource(R.mipmap.login_uncheck_bg);
                } else {
                    btnRegister.setEnabled(false);
                    btnRegister.setClickable(false);
                    btnRegister.setBackgroundResource(R.mipmap.login_check_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
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

    @OnClick({R.id.btn_get_code, R.id.btn_register, R.id.box_password, R.id.tv_merchant})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                if (TextUtils.isEmpty(phone)) {
                    BamToast.show(this, "请输入手机号");
                    return;
                }
                time();
                netCode = 1;
                Code bean = new Code();
                bean.setTele(phone);
                RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE),
                        GsonUtils.getInstance().toObjectGson(bean));
                HttpManager.getInstance().post(Constants.HOST + API.SENDCODE, this, requestBody);
                break;
            case R.id.btn_register:
                if(shmc == null){
                    BamToast.show(this,tvMerchant.getHint());
                    return;
                }
                register();
                break;
            case R.id.box_password:
                editPassword.setTransformationMethod(boxPassword.isChecked() ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                break;
            case R.id.tv_merchant:
                ActivityUtils.startActivityForResult(this, MerchantActivity.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(data !=null) {
                shId = data.getIntExtra("shId", 0);
                shmc = data.getStringExtra("shmc");
                tvMerchant.setText(shmc);
            }
        }
    }

    /**
     * 注册
     */
    public void register() {
        netCode = 2;
        ForgetPasswordBean forgetPasswordBean = new ForgetPasswordBean();
        forgetPasswordBean.setBizId(bizId);
        forgetPasswordBean.setPassword(password);
        forgetPasswordBean.setCheckCode(code);
        forgetPasswordBean.setTele(phone);
        forgetPasswordBean.setShId(shId);
        forgetPasswordBean.setShmc(shmc);
        String gson = GsonUtils.getInstance().toObjectGson(forgetPasswordBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE),gson);
        HttpManager.getInstance().post(Constants.HOST + API.REGISTER, this, requestBody);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downTimer != null) {
            downTimer.cancel();
        }
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        if (isFinishing()) {
            return;
        }
        if (netCode == 1) {
            CodeInfoBean codeInfoBean = GsonUtils.getInstance().parseJsonToBean(t, CodeInfoBean.class);
            bizId = codeInfoBean.getData().getBizId();
//            code = codeInfoBean.getData().getCode();
        } else if (netCode == 2) {
            BaseModel baseModel = GsonUtils.getInstance().parseJsonToBean(t, BaseModel.class);
            BamToast.show(this,baseModel.getMessage());
        }
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

}
