package com.everday.useapp.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;

import com.everday.useapp.R;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.entity.PublicBean;
import com.everday.useapp.entity.PublicInfoBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/26
 * description: 电子签约
 */
public class ElectronicActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView webview;
    private Integer thirdPartyUserId;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_electronic;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("电子签约");
        ivMessage.setVisibility(View.GONE);
        String name = (String) PreferencesUtils.get(UserConfig.CERTIFICATION_NAME, "");
        String code = (String) PreferencesUtils.get(UserConfig.CERTIFICATION_CODE, "");
        thirdPartyUserId = (Integer) PreferencesUtils.get(UserConfig.ID,1);
        String gson = "{\n" +
                "  \"thirdPartyUserId\":\"" + thirdPartyUserId + "\",\n" +
                " \"idCard\":\"" + code + "\",\n" +
                " \"name\":\"" + name + "\"\n" +
                "}";
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE), gson);
        HttpManager.getInstance().post(Constants.HOST + API.ELECTRONIC, this, requestBody);
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        PublicInfoBean publicInfoBean = GsonUtils.getInstance().parseJsonToBean(t, PublicInfoBean.class);
        if(publicInfoBean.getData()!=null) {
            PublicBean data = publicInfoBean.getData();
            webview.loadUrl(data.getUrl());
        }
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
    }

}
