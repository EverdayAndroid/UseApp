package com.everday.useapp.activity.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.everday.useapp.R;
import com.everday.useapp.activity.home.OrderDetailsActivity;
import com.everday.useapp.activity.home.adapter.HomeFragmentAdapter;
import com.everday.useapp.base.BaseFragment;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.dialog.CertificationDialog;
import com.everday.useapp.dialog.OrderDialog;
import com.everday.useapp.entity.BaseModel;
import com.everday.useapp.entity.TaskBean;
import com.everday.useapp.entity.TaskInfoBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.GsonUtils;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.header.DropBoxHeader;
import com.scwang.smartrefresh.header.FlyRefreshHeader;
import com.scwang.smartrefresh.header.FunGameBattleCityHeader;
import com.scwang.smartrefresh.header.FunGameHitBlockHeader;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.header.StoreHouseHeader;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/27
 * description: 接单
 */
public class HomeFragment extends BaseFragment implements OnRefreshLoadMoreListener {
    @BindView(R.id.mlist)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.head_tv_title)
    TextView tvTitle;
    @BindView(R.id.nodata_view)
    LinearLayout nodataView;
    @BindView(R.id.mNo_net_layout)
    LinearLayout mNoNetLayout;
    private HomeFragmentAdapter mAdapter;
    private List<TaskBean> mList;
    //认证
    private CertificationDialog certificationDialog;
    private int pageNumber = 1;
    //访问类型
    private int netCode;
    private int index;
    @Override
    public int initLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void initData() {
        super.initData();
        tvTitle.setText("接单");
        ivBack.setVisibility(View.GONE);
        mList = new ArrayList<>();
        mAdapter = new HomeFragmentAdapter(R.layout.adapter_home_fragment_item, mList, 1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        refreshLayout.setOnRefreshLoadMoreListener(this);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TaskBean taskBean = mList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean",taskBean);
                bundle.putInt("type",1);
                //接单详情
                ActivityUtils.startActivity(getActivity(), OrderDetailsActivity.class,bundle);
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.bt_take_job:
                        index = position;
                        TaskBean taskBean = mList.get(position);
                        orders(taskBean.getId());
                        //接单
                        break;
                }
            }
        });
//        certificationDialog = new CertificationDialog();
//        certificationDialog.show(getChildFragmentManager(), "certificationDialog");
        loadData(true);
    }

    /**
     * 网络请求
     *
     * @param isLoading
     */
    public void loadData(boolean isLoading) {
        netCode = 0;
        if (isLoading) {
//            loadingView.show(getChildFragmentManager(), "loading");
        }
        String gson = "{\"page\":\"" + pageNumber + "\"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE), gson);
        HttpManager.getInstance().post(Constants.HOST + API.GETTASKDJD, this, requestBody);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        certificationDialog.show(getChildFragmentManager(),"certificationDialog");
    }

    /**
     * 接单
     */
    public void orders(Integer taskId) {
        netCode = 1;
        loadingView.show(getChildFragmentManager(), "loading");
        String gson = "{\"taskId\":\"" + taskId + "\"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE), gson);
        HttpManager.getInstance().post(Constants.HOST + API.TAKETASK, this, requestBody);
    }


    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        if (netCode == 0) {
            TaskInfoBean taskInfoBean = GsonUtils.getInstance().parseJsonToBean(t, TaskInfoBean.class);
            mList.addAll(taskInfoBean.getData().getPage().getList());
            if(taskInfoBean.getData().getPage().isLastPage() == false){
                refreshLayout.setEnableLoadMore(true);
            }else {
                refreshLayout.setEnableLoadMore(false);
            }
            if (mList.size() == 0) {
                refreshLayout.setVisibility(View.GONE);
                nodataView.setVisibility(View.VISIBLE);
                mNoNetLayout.setVisibility(View.GONE);
            } else {
                refreshLayout.setVisibility(View.VISIBLE);
                nodataView.setVisibility(View.GONE);
                mNoNetLayout.setVisibility(View.GONE);
                mAdapter.notifyDataSetChanged();
            }
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        } else if (netCode == 1) {
            mAdapter.notifyItemRemoved(index);
            //接单提示
            BaseModel baseModel = GsonUtils.getInstance().parseJsonToBean(t, BaseModel.class);
            BamToast.show(getContext(), baseModel.getMessage());
        }
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message,error);
        if (netCode == 0) {
            if (error == Constants.NO_NET_WORK) {
                refreshLayout.setVisibility(View.GONE);
                nodataView.setVisibility(View.GONE);
                mNoNetLayout.setVisibility(View.VISIBLE);
                refreshLayout.finishLoadMore();
                refreshLayout.finishRefresh();
            }
        } else if (netCode == 1) {
            mAdapter.notifyItemRemoved(index);
            if (error == Constants.BUSINESS_ERROR) {
                OrderDialog.getInstance(message).show(getChildFragmentManager(), "orderDialog");
            }
        }
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message,error);
        if (error == Constants.THROWS_CODE) {
            if (netCode == 0) {
                refreshLayout.setVisibility(View.GONE);
                nodataView.setVisibility(View.GONE);
                mNoNetLayout.setVisibility(View.VISIBLE);
                refreshLayout.finishLoadMore();
                refreshLayout.finishRefresh();
            } else if (netCode == 1) {
                //接单提示
                BamToast.show(getContext(), message);
            }
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        netCode = 0;
        pageNumber += 1;
        loadData(false);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        netCode = 0;
        pageNumber = 1;
        mList.clear();
        mAdapter.notifyDataSetChanged();
        loadData(false);
    }

    @OnClick({R.id.mReload_btn})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.mReload_btn:
                loadingView.show(getChildFragmentManager(),"loading");
                loadData(true);
                break;
        }
    }
}
