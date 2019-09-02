package com.everday.useapp.activity.home.adapter;


import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.everday.useapp.R;
import com.everday.useapp.entity.TaskBean;
import com.everday.useapp.entity.TaskInfoBean;

import java.util.List;
/**
 * date:2019/8/28
 * author:Everday
 * email wangtahandsome@gmail.com
 * desc:接单适配器
 */
public class HomeFragmentAdapter extends BaseQuickAdapter<TaskBean, BaseViewHolder> {
    private int type;
    public HomeFragmentAdapter(int layoutResId, @Nullable List<TaskBean> data,int type) {
        super(layoutResId, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskBean item) {
        //价格
        helper.setText(R.id.tv_price,String.valueOf(item.getYjfy()));
        //类型
        helper.setText(R.id.tv_name,"市场调研");
        //类型名称
        helper.setText(R.id.tv_mission_name,item.getTaskName());
        //地址
        helper.setText(R.id.tv_pick_address,item.getAddress());
        //里程
        helper.setText(R.id.tv_start_distance,"415.5公里");
        //开始时间
        helper.setText(R.id.tv_start_time,item.getStartTime());
        //结束时间
        helper.setText(R.id.tv_end_time,item.getEndTime());
        //时间
        helper.setText(R.id.tv_time,item.getDuration()+"小时");
        String company = "华为有限公司";
        String replaceStr = company.substring(2,4);
        company = company.replace(replaceStr,"****");
        //公司
        helper.setText(R.id.tv_company_name,company);

        helper.addOnClickListener(R.id.bt_take_job);
        if(type == 1){
            helper.getView(R.id.bt_take_job).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.bt_take_job).setVisibility(View.GONE);
        }
    }
}
