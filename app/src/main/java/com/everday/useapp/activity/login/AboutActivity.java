package com.everday.useapp.activity.login;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.everday.useapp.R;
import com.everday.useapp.base.BaseActivity;

import butterknife.BindView;

/**
 * date:2019/11/1
 * author:Everday
 * email wangtahandsome@gmail.com
 * desc: 关于我们
 */
public class AboutActivity extends BaseActivity {
    @BindView(R.id.webview)
    WebView webview;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_about;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("关于我们");
        ivMessage.setVisibility(View.GONE);
        webview.loadUrl("http://www.wyrenli.com/channels/2.html");
    }
}
