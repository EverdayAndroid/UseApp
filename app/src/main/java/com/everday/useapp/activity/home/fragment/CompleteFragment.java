package com.everday.useapp.activity.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.everday.useapp.R;
import com.everday.useapp.activity.home.adapter.HomeFragmentAdapter;
import com.everday.useapp.base.BaseFragment;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.entity.TaskBean;
import com.everday.useapp.network.HttpManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/8/28
 * author:Everday
 * email wangtahandsome@gmail.com
 * desc:完成
 */
public class CompleteFragment extends BaseFragment implements OnRefreshLoadMoreListener {

    @BindView(R.id.mlist)
    RecyclerView recyclerView;
    @BindView(R.id.nodata_view)
    LinearLayout nodataView;
    @BindView(R.id.mNo_net_layout)
    LinearLayout mNoNetLayout;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private HomeFragmentAdapter mAdapter;
    private List mlist;
    //页码
    private int pageNumber =1;
    @Override
    public int initLayout() {
        return R.layout.fragment_complete;
    }

    @Override
    public void initData() {
        super.initData();
        mlist = new ArrayList();
        loadData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new HomeFragmentAdapter(R.layout.adapter_home_fragment_item, mlist,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
//        HttpManager.getInstance().get(Constants.HOST + API.MYTASK, null, this);
    }
    public void loadData(){
        TaskBean taskBean = new TaskBean();
        taskBean.setStartTime("2019-09-02");
        taskBean.setEndTime("2019-09-02");
        taskBean.setYjfy(190.0);
        taskBean.setTaskName("企业市场推广");
        taskBean.setDuration(2);
        taskBean.setAddress("太原市小店区");
        mlist.add(taskBean);
        mlist.add(taskBean);
        mlist.add(taskBean);
        mlist.add(taskBean);
        mlist.add(taskBean);
        mlist.add(taskBean);
        mlist.add(taskBean);
    }
    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        if(isDetached()){return;}
        refreshLayout.setVisibility(View.VISIBLE);
        nodataView.setVisibility(View.GONE);
        mNoNetLayout.setVisibility(View.GONE);
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
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
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
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
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        pageNumber += 1;
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        pageNumber = 1;
    }
}
