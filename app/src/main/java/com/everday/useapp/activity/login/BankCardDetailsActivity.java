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
 * create at 2019/10/8
 * description: 银行卡信息详情
 */
public class BankCardDetailsActivity extends BaseActivity {
    @BindView(R.id.text_nickName)
    TextView textNickName;
    @BindView(R.id.etIdCard)
    TextView etIdCard;
    @BindView(R.id.etBankCard)
    TextView etBankCard;
    @BindView(R.id.etBankType)
    TextView etBankType;
    @BindView(R.id.etPhone)
    TextView etPhone;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_bank_card_details;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("银行卡信息");
        ivMessage.setVisibility(View.GONE);
        String certification_name = (String) PreferencesUtils.get(UserConfig.CERTIFICATION_NAME,"");
        String certification_code = (String) PreferencesUtils.get(UserConfig.CERTIFICATION_CODE,"");
        textNickName.setText(certification_name);
        etIdCard.setText(certification_code);
        initListener();
    }


    public void initListener() {
        String certification_name = (String) PreferencesUtils.get(UserConfig.CERTIFICATION_NAME,"");
        String certification_code = (String) PreferencesUtils.get(UserConfig.CERTIFICATION_CODE,"");
        String bankcard = (String) PreferencesUtils.get(UserConfig.BANKCARD,"");
        String bankcardtype = (String) PreferencesUtils.get(UserConfig.BANKCARDTYPE,"");
        String bankcardphone = (String) PreferencesUtils.get(UserConfig.BANKCARDPHONE,"");
        textNickName.setText(certification_name);
        etIdCard.setText(certification_code);
        etBankCard.setText(bankcard);
        etBankType.setText(bankcardtype);
        etPhone.setText(bankcardphone);
    }

}
