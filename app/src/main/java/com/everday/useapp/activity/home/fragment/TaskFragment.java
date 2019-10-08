package com.everday.useapp.activity.home.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.everday.useapp.R;
import com.everday.useapp.activity.home.adapter.TaskFragmentAdapter;
import com.everday.useapp.base.BaseFragment;
import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/27
 * description: 任务
 */
public class TaskFragment extends BaseFragment {
    @BindView(R.id.tab)
    SlidingTabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.head_tv_title)
    TextView tvTitle;
    private TaskFragmentAdapter mAdapter;
    private SparseArray<Fragment> fragmentSparseArray;
    @Override
    public int initLayout() {
        return R.layout.fragment_mission;
    }

    @Override
    public void initData() {
        super.initData();
        tvTitle.setText("任务");
        ivBack.setVisibility(View.GONE);
        ivMessage.setVisibility(View.GONE);
        fragmentSparseArray = new SparseArray<>();
        ExecuteFragment executeFragment = new ExecuteFragment();
        CompleteFragment completeFragment = new CompleteFragment();
        CancelFragment cancelFragment = new CancelFragment();
        fragmentSparseArray.put(0,executeFragment);
        fragmentSparseArray.put(1,completeFragment);
        fragmentSparseArray.put(2,cancelFragment);
        mAdapter = new TaskFragmentAdapter(getChildFragmentManager(),fragmentSparseArray);
        viewPager.setAdapter(mAdapter);
        tab.setViewPager(viewPager);
    }

}
