package com.everday.useapp.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.everday.useapp.R;
import com.everday.useapp.UseApplication;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.dialog.UseDialog;
import com.everday.useapp.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/11
 * description: 设置
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_setting;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("设置");
        ivMessage.setVisibility(View.GONE);
        String localVersionName = AppUtils.getLocalVersionName(UseApplication.getApplication());
        tvVersion.setText(localVersionName);
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
    }

    @OnClick({R.id.tv_out_login})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_out_login:
                UseDialog.getInstance("是否确定要退出当前账号?", "确定", "取消").show(getSupportFragmentManager(), "usedialog");
                break;
        }
    }
}
