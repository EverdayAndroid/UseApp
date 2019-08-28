package com.everday.useapp.activity.home.fragment;

import android.widget.TextView;

import com.everday.useapp.R;
import com.everday.useapp.base.BaseFragment;

import butterknife.BindView;

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
}
