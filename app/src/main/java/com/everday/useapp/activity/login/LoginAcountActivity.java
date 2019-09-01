package com.everday.useapp.activity.login;

import android.os.Bundle;
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
import com.everday.useapp.UseApplication;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/31
 * description: 账户登录
 */
public class LoginAcountActivity extends BaseActivity {

    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.box_password)
    CheckBox boxPassword;
    @BindView(R.id.text_forget)
    TextView textForget;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private String phone,password;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login_acount;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("账号登录");
        ivMessage.setVisibility(View.GONE);
        initListener();
    }

    /**
     * 登录
     */
    public void login() {
        phone = editPhone.getText().toString().trim();
        password = editPassword.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            BamToast.show(UseApplication.getApplication(), editPhone.getHint());
            return;
        }
        if (TextUtils.isEmpty(password)) {
            BamToast.show(UseApplication.getApplication(), "请输入密码");
            return;
        }
        if (TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
            btnLogin.setClickable(true);
            btnLogin.setEnabled(true);
            btnLogin.setBackgroundResource(R.mipmap.login_check_bg);
        }
    }

    public void initListener(){
        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone = s.toString().trim();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
                    btnLogin.setClickable(true);
                    btnLogin.setEnabled(true);
                    btnLogin.setBackgroundResource(R.mipmap.login_check_bg);
                }else{
                    btnLogin.setClickable(false);
                    btnLogin.setEnabled(false);
                    btnLogin.setBackgroundResource(R.mipmap.login_uncheck_bg);
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
                password = s.toString().trim();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
                    btnLogin.setClickable(true);
                    btnLogin.setEnabled(true);
                    btnLogin.setBackgroundResource(R.mipmap.login_check_bg);
                }else{
                    btnLogin.setClickable(false);
                    btnLogin.setEnabled(false);
                    btnLogin.setBackgroundResource(R.mipmap.login_uncheck_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @OnClick({R.id.btn_login,R.id.box_password,R.id.text_forget})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.box_password:
                editPassword.setTransformationMethod(boxPassword.isChecked() ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                break;
            case R.id.text_forget:
                ActivityUtils.startActivity(this,ForgetPasswordActivity.class);
                break;
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
