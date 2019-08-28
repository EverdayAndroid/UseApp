package com.everday.useapp.activity.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

import com.everday.useapp.R;
import com.everday.useapp.activity.home.adapter.HomeFragmentAdapter;
import com.everday.useapp.base.BaseFragment;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.entity.TaskInfoBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.network.http.CallBack;
import com.everday.useapp.utils.GsonUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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
public class HomeFragment extends BaseFragment implements CallBack {
    @BindView(R.id.mlist)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.head_tv_title)
    TextView tvTitle;
    private HomeFragmentAdapter mAdapter;
    private List mList;
    @Override
    public int initLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void initData() {
        super.initData();
        tvTitle.setText("接单");
        mAdapter = new HomeFragmentAdapter(R.layout.adapter_home_fragment_item,mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex",1);
        map.put("pageSize",1);
        map.put("cid",0);
        map.put("status",0);
        map.put("source",1);
        map.put("totalType",0);
        HttpManager.getInstance().get(Constants.HOST+ API.MYTASK,map,this);
    }

    @Override
    public void onSuccess(String t) {
        TaskInfoBean taskInfoBean = GsonUtils.getInstance().parseJsonToBean(t, TaskInfoBean.class);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String message, int error) {

    }
}
