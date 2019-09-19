package com.everday.useapp.activity.login;

import android.content.Intent;
import android.os.Bundle;
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
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.EverdayLog;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/18
 * description: 实名认证
 */
public class LdentityActivity extends BaseActivity {
    @BindView(R.id.etName)
    TextView tvName;
    @BindView(R.id.etCode)
    TextView tvCode;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private String name,code;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        return R.layout.activity_ldentity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("实名认证");
        ivMessage.setVisibility(View.GONE);
        tvName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name = s.toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(code)) {
                    btnSubmit.setEnabled(false);
                    btnSubmit.setClickable(false);
                    btnSubmit.setBackgroundResource(R.mipmap.login_uncheck_bg);
                } else {
                    btnSubmit.setEnabled(true);
                    btnSubmit.setClickable(true);
                    btnSubmit.setBackgroundResource(R.mipmap.login_check_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tvCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                code = s.toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(code)) {
                    btnSubmit.setEnabled(false);
                    btnSubmit.setClickable(false);
                    btnSubmit.setBackgroundResource(R.mipmap.login_uncheck_bg);
                } else {
                    btnSubmit.setEnabled(true);
                    btnSubmit.setClickable(true);
                    btnSubmit.setBackgroundResource(R.mipmap.login_check_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        String name = (String) PreferencesUtils.get(UserConfig.CERTIFICATION_NAME,"");
        String code = (String) PreferencesUtils.get(UserConfig.CERTIFICATION_CODE,"");
        tvName.setText(name);
        tvCode.setText(code);
    }

    @OnClick({R.id.btn_submit,R.id.etName,R.id.etCode})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_submit:
                Integer id = (Integer) PreferencesUtils.get(UserConfig.ID,1);
                loadingView.show(getSupportFragmentManager(),"loading");
                String gson = "{\n" +
                        "    \"idNo\":\""+code+"\",\n" +
                        "    \"name\":\""+name+"\",\n" +
                        "    \"id\":\""+id+"\"\n" +
                        "}";
                RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE), gson);
                HttpManager.getInstance().post(Constants.HOST+ API.CERTIFICATION,this,requestBody);
                break;
            case R.id.etName:
                Bundle bundle = new Bundle();
                bundle.putString("name",tvName.getText().toString());
                ActivityUtils.startActivityForResult(this,LdentityNameActivity.class,bundle,1);
                break;
            case R.id.etCode:
                Bundle bundle1 = new Bundle();
                bundle1.putString("code",tvCode.getText().toString());
                ActivityUtils.startActivityForResult(this,LdentityCodeActivity.class,bundle1,2);
                break;
        }
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        BaseModel baseModel = GsonUtils.getInstance().parseJsonToBean(t, BaseModel.class);
        PreferencesUtils.put(UserConfig.CERTIFICATION,true,false);
        BamToast.show(this,baseModel.getMessage());
        finish();
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
        BamToast.show(this,message);
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
        BamToast.show(this,message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if(data == null){return;}
                String name = data.getStringExtra("name");
                tvName.setText(name);
                break;
            case 2:
                if(data == null){return;}
                String code = data.getStringExtra("code");
                tvCode.setText(code);
                break;
        }
    }
}
