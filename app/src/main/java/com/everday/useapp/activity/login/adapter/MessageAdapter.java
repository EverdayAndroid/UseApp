package com.everday.useapp.activity.login.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.everday.useapp.R;
import com.everday.useapp.entity.MessageBean;

import java.util.List;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/2
 * description: 消息适配器
 */
public class MessageAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> {
    public MessageAdapter(int layoutResId, @Nullable List<MessageBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {
        helper.setText(R.id.tv_time,item.getPubDate());
        helper.setText(R.id.tv_title,item.getTitle());
//        helper.setText(R.id.tv_detail,"尊敬的用户，您好！应用将在3月21日23:00起至3月22日6:00更新维护，期间会影响应用的提现功能，造成困扰，敬请谅解，Thank you！");
        helper.setText(R.id.tv_detail,item.getContent());
    }
}
