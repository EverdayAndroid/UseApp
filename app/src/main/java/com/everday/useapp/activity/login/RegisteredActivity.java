package com.everday.useapp.activity.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private String phone, code, password;

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

    @OnClick({R.id.btn_get_code, R.id.btn_register, R.id.box_password})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                time();
                break;
            case R.id.btn_register:
                break;
            case R.id.box_password:
                editPassword.setTransformationMethod(boxPassword.isChecked() ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                break;
        }
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
}
