package com.everday.useapp.activity.home.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.everday.useapp.entity.TaskInfoBean;

import java.util.List;
/**
 * date:2019/8/28
 * author:Everday
 * email wangtahandsome@gmail.com
 * desc:接单适配器
 */
public class HomeFragmentAdapter extends BaseQuickAdapter<TaskInfoBean, BaseViewHolder> {

    public HomeFragmentAdapter(int layoutResId, @Nullable List<TaskInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskInfoBean item) {

    }
}
