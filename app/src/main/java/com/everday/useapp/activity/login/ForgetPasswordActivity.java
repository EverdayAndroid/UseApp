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

import com.everday.useapp.R;
import com.everday.useapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/29
 * description: 忘记密码
 */
public class ForgetPasswordActivity extends BaseActivity {

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
    @BindView(R.id.btn_reset)
    Button btnReset;
    private String phone,code,password;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_forget_password;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("忘记密码");
        ivMessage.setVisibility(View.GONE);
        initListener();
    }

    /**
     * 重置密码
     */
    public void resetPassword(){
        phone = editPhone.getText().toString().trim();
        code = editCode.getText().toString().trim();
        password = editPassword.getText().toString().trim();
    }

    public void initListener(){
        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone = s.toString().trim();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(code)) {
                    btnReset.setClickable(true);
                    btnReset.setEnabled(true);
                    btnReset.setBackgroundResource(R.mipmap.login_check_bg);
                }else{
                    btnReset.setClickable(false);
                    btnReset.setEnabled(false);
                    btnReset.setBackgroundResource(R.mipmap.login_uncheck_bg);
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
                code = s.toString().trim();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(code)) {
                    btnReset.setClickable(true);
                    btnReset.setEnabled(true);
                    btnReset.setBackgroundResource(R.mipmap.login_check_bg);
                }else{
                    btnReset.setClickable(false);
                    btnReset.setEnabled(false);
                    btnReset.setBackgroundResource(R.mipmap.login_uncheck_bg);
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
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(code)) {
                    btnReset.setClickable(true);
                    btnReset.setEnabled(true);
                    btnReset.setBackgroundResource(R.mipmap.login_check_bg);
                }else{
                    btnReset.setClickable(false);
                    btnReset.setEnabled(false);
                    btnReset.setBackgroundResource(R.mipmap.login_uncheck_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @OnClick({R.id.btn_reset,R.id.box_password})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_reset:
                resetPassword();
                break;
            case R.id.box_password:
                editPassword.setTransformationMethod(boxPassword.isChecked()? HideReturnsTransformationMethod.getInstance(): PasswordTransformationMethod.getInstance());
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