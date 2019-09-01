package com.everday.useapp.activity.money.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.everday.useapp.R;

import java.util.List;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/1
 * description: 明细适配器
 */
public class WithdrawAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public WithdrawAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name,"");
        helper.setText(R.id.tv_money,"");
    }
}
