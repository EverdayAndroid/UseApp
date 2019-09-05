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
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.entity.ForgetPasswordInfoBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/29
 * description: 修改密码
 */
public class CheckPasswordActivity extends BaseActivity {
    @BindView(R.id.edit_old_password)
    EditText editOldPassword;
    @BindView(R.id.box_old_password)
    CheckBox boxOldPassword;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.box_password)
    CheckBox boxPassword;
    @BindView(R.id.edit_password_again)
    EditText editPasswordAgain;
    @BindView(R.id.box_password_again)
    CheckBox boxPasswordAgain;
    @BindView(R.id.bt_change)
    Button btChange;
    private String oldPassword,password,passwordAgain;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_check_password;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initListener();
    }
    public void initListener(){
        editOldPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                oldPassword = s.toString().trim();
                if (!TextUtils.isEmpty(oldPassword) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(passwordAgain)) {
                    btChange.setClickable(true);
                    btChange.setEnabled(true);
                    btChange.setBackgroundResource(R.mipmap.login_check_bg);
                }else{
                    btChange.setClickable(false);
                    btChange.setEnabled(false);
                    btChange.setBackgroundResource(R.mipmap.login_uncheck_bg);
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
                if (!TextUtils.isEmpty(oldPassword) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(passwordAgain)) {
                    btChange.setClickable(true);
                    btChange.setEnabled(true);
                    btChange.setBackgroundResource(R.mipmap.login_check_bg);
                }else{
                    btChange.setClickable(false);
                    btChange.setEnabled(false);
                    btChange.setBackgroundResource(R.mipmap.login_uncheck_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editPasswordAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = s.toString().trim();
                if (!TextUtils.isEmpty(oldPassword) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(passwordAgain)) {
                    btChange.setClickable(true);
                    btChange.setEnabled(true);
                    btChange.setBackgroundResource(R.mipmap.login_check_bg);
                }else{
                    btChange.setClickable(false);
                    btChange.setEnabled(false);
                    btChange.setBackgroundResource(R.mipmap.login_uncheck_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 修改密码
     */
    public void checkPassword(){
        password = editPassword.getText().toString().trim();
        oldPassword = editOldPassword.getText().toString().trim();
        passwordAgain = editPasswordAgain.getText().toString().trim();
        RequestBody requestBody = new FormBody.Builder()
                .add("tele", PreferencesUtils.get(UserConfig.USERNAME,"").toString())
                .add("oldpassword",oldPassword)
                .add("newpassword1",password)
                .add("newpassword2",passwordAgain)
                .build();
        HttpManager.getInstance().post(Constants.HOST+ API.UPDATEPASSWORD,this,requestBody);
    }
    @OnClick({R.id.bt_change,R.id.box_password,R.id.box_old_password,R.id.box_password_again})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.bt_change:
                checkPassword();
                break;
            case R.id.box_password:
                editPassword.setTransformationMethod(boxPassword.isChecked()? HideReturnsTransformationMethod.getInstance(): PasswordTransformationMethod.getInstance());
                break;
            case R.id.box_old_password:
                editOldPassword.setTransformationMethod(boxOldPassword.isChecked()? HideReturnsTransformationMethod.getInstance(): PasswordTransformationMethod.getInstance());
                break;
            case R.id.box_password_again:
                editPasswordAgain.setTransformationMethod(boxPasswordAgain.isChecked()? HideReturnsTransformationMethod.getInstance(): PasswordTransformationMethod.getInstance());
                break;
        }
    }
    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        if (isFinishing()) {
            return;
        }
        ForgetPasswordInfoBean forgetPasswordInfoBean = GsonUtils.getInstance().parseJsonToBean(t, ForgetPasswordInfoBean.class);
        BamToast.show(this,forgetPasswordInfoBean.getData().getMsg());
        finish();
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
