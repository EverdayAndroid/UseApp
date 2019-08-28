package com.everday.useapp.activity.home.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.everday.useapp.R;
import com.everday.useapp.activity.home.adapter.TaskFragmentAdapter;
import com.everday.useapp.base.BaseFragment;
import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    private TaskFragmentAdapter mAdapter;
    private SparseArray<Fragment> fragmentSparseArray;
    @Override
    public int initLayout() {
        return R.layout.fragment_mission;
    }

    @Override
    public void initData() {
        super.initData();
        fragmentSparseArray = new SparseArray<>();
        mAdapter = new TaskFragmentAdapter(getChildFragmentManager(),fragmentSparseArray);
        viewPager.setAdapter(mAdapter);
        tab.setViewPager(viewPager);
    }

}
