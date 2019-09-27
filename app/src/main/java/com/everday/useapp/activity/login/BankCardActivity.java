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
import android.widget.TextView;

import com.everday.useapp.R;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.entity.BankCard;
import com.everday.useapp.entity.BaseModel;
import com.everday.useapp.entity.Code;
import com.everday.useapp.entity.CodeInfoBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/22
 * description: 绑定银行卡
 */
public class BankCardActivity extends BaseActivity {

    @BindView(R.id.text_nickName)
    EditText textNickName;
    @BindView(R.id.etIdCard)
    EditText etIdCard;
    @BindView(R.id.etBankCard)
    EditText etBankCard;
    @BindView(R.id.etBankType)
    TextView etBankType;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private String name, idCard, bankCardType, bankCard, phone, code, bizId;
    private int countTime = 60;
    private CountDownTimer downTimer;
    //银行卡号1，验证码2，确认3
    private int netCode;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_bank_card;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("添加银行卡");
        ivMessage.setVisibility(View.GONE);
        String certification_name = (String) PreferencesUtils.get(UserConfig.CERTIFICATION_NAME, "");
        String certification_code = (String) PreferencesUtils.get(UserConfig.CERTIFICATION_CODE, "");
        textNickName.setText(certification_name);
        etIdCard.setText(certification_code);
        initListener();
    }


    public void initListener() {
        name = textNickName.getText().toString();
        idCard = etIdCard.getText().toString();
        bankCard = etBankCard.getText().toString();
        bankCardType = etBankType.getText().toString();
        phone = etPhone.getText().toString().trim();
        code = etCode.getText().toString().trim();
        textNickName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name = s.toString();
                if (TextUtils.isEmpty(name)
                        || TextUtils.isEmpty(idCard)
                        || TextUtils.isEmpty(bankCard)
                        || TextUtils.isEmpty(bankCardType)
                        || TextUtils.isEmpty(phone)
                        || TextUtils.isEmpty(code)) {
                    btnRegister.setEnabled(false);
                    btnRegister.setClickable(false);
                    btnRegister.setBackgroundResource(R.mipmap.login_uncheck_bg);
                } else {
                    btnRegister.setEnabled(true);
                    btnRegister.setClickable(true);
                    btnRegister.setBackgroundResource(R.mipmap.login_check_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etIdCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                idCard = s.toString();
                if (TextUtils.isEmpty(name)
                        || TextUtils.isEmpty(idCard)
                        || TextUtils.isEmpty(bankCard)
                        || TextUtils.isEmpty(bankCardType)
                        || TextUtils.isEmpty(phone)
                        || TextUtils.isEmpty(code)) {
                    btnRegister.setEnabled(false);
                    btnRegister.setClickable(false);
                    btnRegister.setBackgroundResource(R.mipmap.login_uncheck_bg);
                } else {
                    btnRegister.setEnabled(true);
                    btnRegister.setClickable(true);
                    btnRegister.setBackgroundResource(R.mipmap.login_check_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etBankCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bankCard = s.toString();
                if (TextUtils.isEmpty(name)
                        || TextUtils.isEmpty(idCard)
                        || TextUtils.isEmpty(bankCard)
                        || TextUtils.isEmpty(bankCardType)
                        || TextUtils.isEmpty(phone)
                        || TextUtils.isEmpty(code)) {
                    btnRegister.setEnabled(false);
                    btnRegister.setClickable(false);
                    btnRegister.setBackgroundResource(R.mipmap.login_uncheck_bg);
                } else {
                    btnRegister.setEnabled(true);
                    btnRegister.setClickable(true);
                    btnRegister.setBackgroundResource(R.mipmap.login_check_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etBankCard.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false && bankCard.length() == 16 || bankCard.length() == 17 || bankCard.length() == 19) {
                    netCode = 1;
                    String gson = "{\n" +
                            " \"cardNum\":\"" + bankCard + "\"\n" +
                            "}";
                    RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE), gson);
                    HttpManager.getInstance().post(Constants.HOST + API.BANKCARDTYPE, BankCardActivity.this, requestBody);
                }
            }
        });
        etBankType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bankCardType = s.toString();
                if (TextUtils.isEmpty(name)
                        || TextUtils.isEmpty(idCard)
                        || TextUtils.isEmpty(bankCard)
                        || TextUtils.isEmpty(bankCardType)
                        || TextUtils.isEmpty(phone)
                        || TextUtils.isEmpty(code)) {
                    btnRegister.setEnabled(false);
                    btnRegister.setClickable(false);
                    btnRegister.setBackgroundResource(R.mipmap.login_uncheck_bg);
                } else {
                    btnRegister.setEnabled(true);
                    btnRegister.setClickable(true);
                    btnRegister.setBackgroundResource(R.mipmap.login_check_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone = s.toString();
                if (TextUtils.isEmpty(name)
                        || TextUtils.isEmpty(idCard)
                        || TextUtils.isEmpty(bankCard)
                        || TextUtils.isEmpty(bankCardType)
                        || TextUtils.isEmpty(phone)
                        || TextUtils.isEmpty(code)) {
                    btnRegister.setEnabled(false);
                    btnRegister.setClickable(false);
                    btnRegister.setBackgroundResource(R.mipmap.login_uncheck_bg);
                } else {
                    btnRegister.setEnabled(true);
                    btnRegister.setClickable(true);
                    btnRegister.setBackgroundResource(R.mipmap.login_check_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                code = s.toString();
                if (TextUtils.isEmpty(name)
                        || TextUtils.isEmpty(idCard)
                        || TextUtils.isEmpty(bankCard)
                        || TextUtils.isEmpty(bankCardType)
                        || TextUtils.isEmpty(phone)
                        || TextUtils.isEmpty(code)) {
                    btnRegister.setEnabled(false);
                    btnRegister.setClickable(false);
                    btnRegister.setBackgroundResource(R.mipmap.login_uncheck_bg);
                } else {
                    btnRegister.setEnabled(true);
                    btnRegister.setClickable(true);
                    btnRegister.setBackgroundResource(R.mipmap.login_check_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @OnClick({R.id.btn_get_code, R.id.btn_register})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                if (TextUtils.isEmpty(phone)) {
                    BamToast.show(this, "请输入手机号");
                    return;
                }
                time();
                netCode = 2;
                Code bean = new Code();
                bean.setTele(phone);
                RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE),
                        GsonUtils.getInstance().toObjectGson(bean));
                HttpManager.getInstance().post(Constants.HOST + API.SENDCODE, this, requestBody);
            case R.id.btn_register:
                loadingView.show(getSupportFragmentManager(), "loading");
                bindBankCard();
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
                btnGetCode.setText("获取验证码");
                countTime = 60;
            }
        };
        downTimer.start();
    }

    /**
     * 绑定银行卡
     */
    public void bindBankCard() {
        String gson = "{\n" +
                " \"tele\":\"" + phone + "\",\n" +
                " \"cardNum\":\"" + bankCard + "\",\n" +
                " \"cardType\":\"" + bankCardType + "\",\n" +
                " \"checkCode\":\"" + code + "\",\n" +
                " \"bizId\":\"" + bizId + "\"\n" +
                "}";
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE), gson);
        HttpManager.getInstance().post(Constants.HOST + API.BINDBANKCARD, this, requestBody);
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        if(netCode == 1){
            BankCard bankCard = GsonUtils.getInstance().parseJsonToBean(t, BankCard.class);
            etBankType.setText(bankCard.getData().getBankName());
            //银行卡类型
            PreferencesUtils.put(UserConfig.BANKCARDTYPE, etBankType.getText().toString(), true);
        }
        if (netCode == 2) {
            CodeInfoBean codeInfoBean = GsonUtils.getInstance().parseJsonToBean(t, CodeInfoBean.class);
            bizId = codeInfoBean.getData().getBizId();
        }
        if (netCode == 3) {
            //记录绑定银行卡
            PreferencesUtils.put(UserConfig.BANKCARD, bankCard, true);
            //记录银行卡手机号
            PreferencesUtils.put(UserConfig.BANKCARDPHONE, phone, true);
            BaseModel baseModel = GsonUtils.getInstance().parseJsonToBean(t, BaseModel.class);
            BamToast.show(this, baseModel.getMessage());
            finish();
        }
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
        BamToast.show(this, message);
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
    }


}
