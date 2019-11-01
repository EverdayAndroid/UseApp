package com.everday.useapp.activity.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.everday.useapp.R;
import com.everday.useapp.activity.login.adapter.MessageAdapter;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.entity.MessageBean;
import com.everday.useapp.entity.MessageInfoBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.GsonUtils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * date:2019/8/29
 * author:Everday
 * email wangtahandsome@gmail.com
 * desc: 消息
 */
public class MessageActivity extends BaseActivity implements OnRefreshLoadMoreListener {
    @BindView(R.id.mlist)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.nodata_view)
    LinearLayout nodataView;
    @BindView(R.id.mNo_net_layout)
    LinearLayout mNoNetLayout;
    private MessageAdapter mAdapter;
    private List<MessageBean> mlist;
    private int pageNumber = 1;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_message;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("消息中心");
        ivMessage.setVisibility(View.GONE);
        mlist = new ArrayList<>();
        mAdapter = new MessageAdapter(R.layout.adapter_item_message,mlist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        refreshLayout.setRefreshHeader(new MaterialHeader(this));
        refreshLayout.setOnRefreshLoadMoreListener(this);
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableRefresh(true);
        loadData(true);
    }


    public void loadData(boolean isLoading){
        if(isLoading){
            loadingView.show(getSupportFragmentManager(),"loading");
        }
        String gson = "{\"page\": \""+pageNumber+"\"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE),gson);
        HttpManager.getInstance().post(Constants.HOST+ API.NOTICE,this,requestBody);
    }
    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        MessageInfoBean messageInfoBean = GsonUtils.getInstance().parseJsonToBean(t, MessageInfoBean.class);
        mlist.addAll(messageInfoBean.getData().getPage().getList());
        if(messageInfoBean.getData().getPage().isLastPage() == false){
            refreshLayout.setEnableLoadMore(true);
        }else {
            refreshLayout.setEnableLoadMore(false);
        }
        if(mlist.size() == 0){
            refreshLayout.setVisibility(View.GONE);
            mNoNetLayout.setVisibility(View.GONE);
            nodataView.setVisibility(View.VISIBLE);
        }else{
            refreshLayout.setVisibility(View.VISIBLE);
            nodataView.setVisibility(View.GONE);
            mNoNetLayout.setVisibility(View.GONE);
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
        if (isFinishing()) {
            return;
        }
        BamToast.show(message);
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
        if (isFinishing()) {
            return;
        }
        BamToast.show(message);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        pageNumber += 1;
        loadData(false);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        pageNumber = 1;
        mlist.clear();
        loadData(false);
    }

    @OnClick({R.id.mReload_btn})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.mReload_btn:
                loadData(true);
                break;
        }
    }
}
