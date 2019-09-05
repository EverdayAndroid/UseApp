package com.everday.useapp.activity.login.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.everday.useapp.R;
import com.everday.useapp.entity.MerchantBean;

import java.util.List;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/5
 * description: 商户适配器
 */
public class MerchantAdapter extends BaseQuickAdapter<MerchantBean, BaseViewHolder> {
    public MerchantAdapter(int layoutResId, @Nullable List<MerchantBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MerchantBean item) {
        helper.setText(R.id.tv_name,item.getShmc());
    }
}
