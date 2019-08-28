package com.everday.useapp.activity.home.fragment;

import android.widget.ListView;
import android.widget.TextView;

import com.everday.useapp.R;
import com.everday.useapp.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/27
 * description: 接单
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.mlist)
    ListView mlist;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.head_tv_title)
    TextView tvTitle;
    @Override
    public int initLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void initData() {
        super.initData();
        tvTitle.setText("接单");
    }

}
