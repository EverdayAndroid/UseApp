package com.everday.useapp.activity.home.fragment;

import android.view.View;
import android.widget.TextView;

import com.everday.useapp.R;
import com.everday.useapp.activity.login.LoginActivity;
import com.everday.useapp.activity.login.PersonalActivity;
import com.everday.useapp.base.BaseFragment;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/27
 * description: 我的
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.head_tv_title)
    TextView tvTitle;

    @Override
    public int initLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initData() {
        super.initData();
        tvTitle.setText("我的");
    }

    @OnClick({R.id.ll_info})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_info:
                String username = (String) PreferencesUtils.get(UserConfig.USERNAME, "");
                if (username.isEmpty()) {
                    ActivityUtils.startActivity(getActivity(), LoginActivity.class);
                } else {
                    ActivityUtils.startActivity(getActivity(), PersonalActivity.class);
                }
                break;
        }
    }
}
