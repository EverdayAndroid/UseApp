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
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.entity.Code;
import com.everday.useapp.entity.CodeInfoBean;
import com.everday.useapp.entity.ForgetPasswordBean;
import com.everday.useapp.entity.ForgetPasswordInfoBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.GsonUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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
    private String phone,code,password,bizId;
    //请求标识
    private int netCode;
    private int countTime = 60;
    private CountDownTimer downTimer;
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
        netCode = 2;
        phone = editPhone.getText().toString().trim();
        code = editCode.getText().toString().trim();
        password = editPassword.getText().toString().trim();
        ForgetPasswordBean forgetPasswordBean = new ForgetPasswordBean();
        forgetPasswordBean.setTele(phone);
        forgetPasswordBean.setPassword(password);
        forgetPasswordBean.setCheckCode(code);
        forgetPasswordBean.setBizId(bizId);
        String gosn = GsonUtils.getInstance().toObjectGson(forgetPasswordBean);
        RequestBody requestBody =  RequestBody.create(MediaType.parse(Constants.CONTENTYPE),gosn);
        HttpManager.getInstance().post(Constants.HOST+ API.FORGETPWD,this,requestBody);
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
    @OnClick({R.id.btn_reset,R.id.box_password,R.id.btn_get_code})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_reset:
                resetPassword();
                break;
            case R.id.btn_get_code:
                if(TextUtils.isEmpty(phone)){
                    BamToast.show(this,editPhone.getHint());
                    return;
                }
                time();
                netCode = 1;
                Code bean = new Code();
                bean.setTele(phone);
                String gson = GsonUtils.getInstance().toObjectGson(bean);
                RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE),gson);
                HttpManager.getInstance().post(Constants.HOST+API.SENDCODE,this,requestBody);
                BamToast.show(this, R.string.sendCode);
                break;
            case R.id.box_password:
                editPassword.setTransformationMethod(boxPassword.isChecked()? HideReturnsTransformationMethod.getInstance(): PasswordTransformationMethod.getInstance());
                break;
        }
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
                btnGetCode.setText(getString(R.string.getCode));
                countTime = 60;
            }
        };
        downTimer.start();
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        if (isFinishing()) {
            return;
        }
        if(netCode == 1){
            CodeInfoBean codeInfoBean = GsonUtils.getInstance().parseJsonToBean(t, CodeInfoBean.class);
            bizId = codeInfoBean.getData().getBizId();
        }else if(netCode == 2){
            ForgetPasswordInfoBean forgetPasswordInfoBean = GsonUtils.getInstance().parseJsonToBean(t, ForgetPasswordInfoBean.class);
            BamToast.show(this,forgetPasswordInfoBean.getData().getMsg());
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
