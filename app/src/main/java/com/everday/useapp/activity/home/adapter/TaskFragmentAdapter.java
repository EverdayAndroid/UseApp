package com.everday.useapp.activity.home.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/27
 * description: 任务
 */
public class TaskFragmentAdapter extends FragmentPagerAdapter {

    private SparseArray<Fragment> sparseArray;
    private String[] titles = new String[]{"进行中" , "已完成","已取消"};
    public TaskFragmentAdapter(FragmentManager fm , SparseArray<Fragment> fragmentSparseArray) {
        super(fm);
        this.sparseArray = fragmentSparseArray;
    }

    @Override
    public Fragment getItem(int position) {
        return sparseArray.get(position);
    }

    @Override
    public int getCount() {
        return sparseArray.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}