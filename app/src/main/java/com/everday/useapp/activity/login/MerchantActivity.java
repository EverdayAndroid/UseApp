package com.everday.useapp.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.everday.useapp.R;
import com.everday.useapp.activity.login.adapter.MerchantAdapter;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.entity.Merchant;
import com.everday.useapp.entity.MerchantInfoBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.network.http.CallBack;
import com.everday.useapp.utils.GsonUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/5
 * description: 商户列表
 */
public class MerchantActivity extends BaseActivity implements CallBack, OnRefreshLoadMoreListener {

    @BindView(R.id.mlist)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.nodata_view)
    LinearLayout nodataView;
    @BindView(R.id.mNo_net_layout)
    LinearLayout mNoNetLayout;
    private MerchantAdapter mAdapter;
    private List<Merchant> mlist;
    private int pageNumber = 1;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_merchant;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        ivMessage.setVisibility(View.GONE);
        tvTitle.setText("商户列表");
        mlist = new ArrayList<>();
        mAdapter = new MerchantAdapter(R.layout.adapter_merchant_item,mlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        refreshLayout.setOnRefreshLoadMoreListener(this);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Merchant merchantBean = mlist.get(position);
                Intent intent = new Intent();
                intent.putExtra("shId",merchantBean.getShId());
                intent.putExtra("shmc",merchantBean.getShmc());
                setResult(1,intent);
                finish();
            }
        });
        merchant(true);
    }

    /**
     * 加载数据
     * @param isLoading
     */
    public void merchant(boolean isLoading) {
        if(isLoading) {
            loadingView.show(getSupportFragmentManager(), "loading");
        }
        HttpManager.getInstance().get(Constants.HOST + API.MERCHANT, null, this);
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        if (isFinishing()) {
            return;
        }
        loadingView.dismiss();
        MerchantInfoBean merchantInfoBean = GsonUtils.getInstance().parseJsonToBean(t, MerchantInfoBean.class);
        mlist.addAll(merchantInfoBean.getData().getList());
        if(mlist.size() == 0){
            refreshLayout.setVisibility(View.GONE);
            nodataView.setVisibility(View.VISIBLE);
            mNoNetLayout.setVisibility(View.GONE);
        }else {
            refreshLayout.setVisibility(View.VISIBLE);
            nodataView.setVisibility(View.GONE);
            mNoNetLayout.setVisibility(View.GONE);
            refreshLayout.setVisibility(View.VISIBLE);
            nodataView.setVisibility(View.GONE);
            mNoNetLayout.setVisibility(View.GONE);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
        if (isFinishing()) {
            return;
        }
        if(error == Constants.NO_NET_WORK){
            refreshLayout.setVisibility(View.GONE);
            nodataView.setVisibility(View.GONE);
            mNoNetLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
        if (isFinishing()) {
            return;
        }
        if(error == Constants.THROWS_CODE){
            refreshLayout.setVisibility(View.GONE);
            nodataView.setVisibility(View.GONE);
            mNoNetLayout.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        pageNumber += 1;
        refreshLayout.finishLoadMore();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        pageNumber = 1;
        refreshLayout.finishRefresh();
    }

    @OnClick({R.id.mReload_btn})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.mReload_btn:
                merchant(true);
                break;
        }
    }
}
