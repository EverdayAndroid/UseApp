package com.everday.useapp.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;

import com.everday.useapp.R;
import com.everday.useapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/10/30
 * description: 用户协议,or 隐私政策
 */
public class UseActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView webview;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_use;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        ivMessage.setVisibility(View.GONE);
        int type = getIntent().getIntExtra("type", 0);
        if(type == 0) {
            tvTitle.setText("用户协议");
            webview.loadUrl("http://www.yongrenbao.co/yhxy.html");
        }else{
            tvTitle.setText("隐私政策");
            webview.loadUrl(" http://www.yongrenbao.co/yszc.html");
        }
    }
}
