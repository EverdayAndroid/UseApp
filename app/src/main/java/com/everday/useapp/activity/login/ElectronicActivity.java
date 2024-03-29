package com.everday.useapp.activity.login;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.everday.useapp.R;
import com.everday.useapp.activity.home.HomeActivity;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.constants.EventConfig;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.entity.PublicBean;
import com.everday.useapp.entity.PublicInfoBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.ActivityManagement;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PreferencesUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    private String ldentity;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_electronic;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("电子签约");
        ivMessage.setVisibility(View.GONE);
        ldentity = getIntent().getStringExtra("ldentity");
//        loadingView.show(getSupportFragmentManager(),"loading");
        webview.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loadingView.show(getSupportFragmentManager(),"loading");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                webview.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loadingView.dismiss();
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }
        });
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
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ldentity!=null){
                    ActivityUtils.startActivity(ElectronicActivity.this, HomeActivity.class);
                    ActivityManagement.getInstance().finishActivity(LoginActivity.class);
                    finish();
                }else {
                    finish();
                }
            }
        });
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        PublicInfoBean publicInfoBean = GsonUtils.getInstance().parseJsonToBean(t, PublicInfoBean.class);
        if(publicInfoBean.getData()!=null) {
            PublicBean data = publicInfoBean.getData();
            webview.loadUrl(data.getUrl());
            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setDomStorageEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(ldentity!=null){
            ActivityUtils.startActivity(this, HomeActivity.class);
            ActivityManagement.getInstance().finishActivity(LoginActivity.class);
            finish();
        }else {
            finish();
        }
    }
    @OnClick({R.id.iv_back})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                if(ldentity!=null){
                    ActivityUtils.startActivity(this, HomeActivity.class);
                    ActivityManagement.getInstance().finishActivity(LoginActivity.class);
                }else {
                    finish();
                }
                break;
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(EventConfig.HOMEFRAGMENT_USER);
    }
}
