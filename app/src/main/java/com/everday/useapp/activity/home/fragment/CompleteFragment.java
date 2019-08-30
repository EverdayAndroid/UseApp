package com.everday.useapp.activity.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.everday.useapp.R;
import com.everday.useapp.base.BaseFragment;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.network.HttpManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/8/28
 * author:Everday
 * email wangtahandsome@gmail.com
 * desc:完成
 */
public class CompleteFragment extends BaseFragment {

    @BindView(R.id.mlist)
    RecyclerView mlist;
    @BindView(R.id.nodata_view)
    LinearLayout nodataView;
    @BindView(R.id.mNo_net_layout)
    LinearLayout mNoNetLayout;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    public int initLayout() {
        return R.layout.fragment_complete;
    }

    @Override
    public void initData() {
        super.initData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mlist.setLayoutManager(layoutManager);

//        HttpManager.getInstance().get(Constants.HOST + API.MYTASK, null, this);
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        if(isDetached()){return;}
        refreshLayout.setVisibility(View.VISIBLE);
        nodataView.setVisibility(View.GONE);
        mNoNetLayout.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
        if(isDetached()){return;}
        if(error == Constants.NO_NET_WORK){
            refreshLayout.setVisibility(View.GONE);
            nodataView.setVisibility(View.GONE);
            mNoNetLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
        if(isDetached()){return;}
        if(error == Constants.THROWS_CODE){
            refreshLayout.setVisibility(View.GONE);
            nodataView.setVisibility(View.GONE);
            mNoNetLayout.setVisibility(View.VISIBLE);
        }
    }
}
