package com.everday.useapp.activity.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.everday.useapp.R;
import com.everday.useapp.activity.home.OrderDetailsActivity;
import com.everday.useapp.activity.home.adapter.HomeFragmentAdapter;
import com.everday.useapp.base.BaseFragment;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.entity.TaskBean;
import com.everday.useapp.entity.TaskInfoBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PreferencesUtils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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
    @BindView(R.id.tv_load)
    TextView tvLoad;
    private HomeFragmentAdapter mAdapter;
    private List<TaskBean> mlist;
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new HomeFragmentAdapter(R.layout.adapter_home_fragment_item, mlist,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        refreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        refreshLayout.setOnRefreshLoadMoreListener(this);
        loadingView.show(getChildFragmentManager(),"loading");
        loadData(true);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TaskBean taskBean = mlist.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean",taskBean);
                //接单详情
                ActivityUtils.startActivity(getActivity(), OrderDetailsActivity.class,bundle);
            }
        });
    }

    /**
     * 请求数据
     * @param isLoading
     */
    public void loadData(boolean isLoading){
        if(isLoading) {
            loadingView.show(getChildFragmentManager(), "loading");
        }
        String gson = "{\n" +
                " \"page\":\""+pageNumber+"\",\n" +
                " \"tele\":\""+ PreferencesUtils.get(UserConfig.TELE,"").toString() +"\",\n" +
                " \"state\":\"2\"\n" +
                "}";
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE),gson);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HttpManager.getInstance().post(Constants.HOST + API.GETMYTASK, this,requestBody);
    }
    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        TaskInfoBean taskInfoBean = GsonUtils.getInstance().parseJsonToBean(t, TaskInfoBean.class);
        mlist.addAll(taskInfoBean.getData().getPage().getList());
        if(taskInfoBean.getData().getPage().isLastPage() == false){
            refreshLayout.setEnableLoadMore(true);
        }else {
            refreshLayout.setEnableLoadMore(false);
        }
        if(mlist.size() == 0){
            refreshLayout.setVisibility(View.GONE);
            nodataView.setVisibility(View.VISIBLE);
            mNoNetLayout.setVisibility(View.GONE);
        }else{
            refreshLayout.setVisibility(View.VISIBLE);
            nodataView.setVisibility(View.GONE);
            mNoNetLayout.setVisibility(View.GONE);
            mAdapter.notifyDataSetChanged();
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        }
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
        loadData(false);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        pageNumber = 1;
        mlist.clear();
        mAdapter.notifyDataSetChanged();
        loadData(false);
    }

    @OnClick({R.id.mReload_btn,R.id.tv_load})
    void OnClick(View view){
        loadData(true);
    }
}
