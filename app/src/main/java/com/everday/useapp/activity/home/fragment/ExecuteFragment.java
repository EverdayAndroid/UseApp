package com.everday.useapp.activity.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.everday.useapp.R;
import com.everday.useapp.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/8/28
 * author:Everday
 * email wangtahandsome@gmail.com
 * desc:进行中
 */
public class ExecuteFragment extends BaseFragment {

    @BindView(R.id.mlist)
    ListView mlist;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.nodata_view)
    LinearLayout nodataView;
    @BindView(R.id.mNo_net_layout)
    LinearLayout mNoNetLayout;

    @Override
    public int initLayout() {
        return R.layout.fragment_execute;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
