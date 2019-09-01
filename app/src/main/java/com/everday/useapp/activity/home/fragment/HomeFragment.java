package com.everday.useapp.activity.home.fragment;

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
import com.everday.useapp.dialog.CertificationDialog;
import com.everday.useapp.dialog.OrderDialog;
import com.everday.useapp.entity.TaskBean;
import com.everday.useapp.entity.TaskInfoBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.GsonUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

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
        loadData();
        mAdapter = new HomeFragmentAdapter(R.layout.adapter_home_fragment_item, mList,1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadMore(true);
        Map<String, Object> map = new HashMap<>();
        map.put("pageIndex", 1);
        map.put("pageSize", 1);
        map.put("cid", 0);
        map.put("status", 0);
        map.put("source", 1);
        map.put("totalType", 0);
        HttpManager.getInstance().get(Constants.HOST + API.MYTASK, map, this);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //接单详情
                ActivityUtils.startActivity(getActivity(), OrderDetailsActivity.class);
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.bt_take_job:
                        OrderDialog.getInstance("诶哟，还是慢了一步！订单被抢走了").show(getChildFragmentManager(),"orderDialog");
                        //接单
                        break;
                }
            }
        });
        certificationDialog = new CertificationDialog();
        certificationDialog.show(getChildFragmentManager(),"certificationDialog");
    }

    public void loadData(){
        TaskBean taskBean = new TaskBean();
        taskBean.setStartTime("2019-09-02");
        taskBean.setEndTime("2019-09-02");
        taskBean.setYjfy(190.0);
        taskBean.setTaskName("企业市场推广");
        taskBean.setDuration(2);
        taskBean.setAddress("太原市小店区");
        mList.add(taskBean);
        mList.add(taskBean);
        mList.add(taskBean);
        mList.add(taskBean);
        mList.add(taskBean);
        mList.add(taskBean);
        mList.add(taskBean);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        certificationDialog.show(getChildFragmentManager(),"certificationDialog");
    }

    /**
     * 接单
     */
    public void orders(){
        //TODO  接单请求
    }


    @Override
    public void onSuccess(String t) {
        if(isDetached()){return;}
        refreshLayout.setVisibility(View.VISIBLE);
        nodataView.setVisibility(View.GONE);
        mNoNetLayout.setVisibility(View.GONE);
        TaskInfoBean taskInfoBean = GsonUtils.getInstance().parseJsonToBean(t, TaskInfoBean.class);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String message, int error) {
        if(isDetached()){return;}
        if(error == Constants.NO_NET_WORK){
            refreshLayout.setVisibility(View.GONE);
            nodataView.setVisibility(View.GONE);
            mNoNetLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onThrows(String message, int error) {
        if(isDetached()){return;}
        if(error == Constants.THROWS_CODE){
            refreshLayout.setVisibility(View.GONE);
            nodataView.setVisibility(View.GONE);
            mNoNetLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh();
    }
}
