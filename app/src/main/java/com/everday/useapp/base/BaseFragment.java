package com.everday.useapp.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.everday.useapp.R;
import com.everday.useapp.activity.login.MessageActivity;
import com.everday.useapp.dialog.LoadingView;
import com.everday.useapp.network.http.CallBack;
import com.everday.useapp.utils.ActivityUtils;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/7/21
 * description: 基类
 */
public class BaseFragment<P extends BasePresenter> extends RxFragment implements IFragment, CallBack {
    private Unbinder unbinder;
    protected View view;
    protected P mPresent;
    protected LoadingView loadingView;
    protected ImageView ivMessage,ivBack;
    protected TextView tvTitle;
    //是否可以操作view控件,防止view已经销毁异步操作
    protected boolean operatingView;
//    protected RequestOptions requestOptions;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(initLayout(), container, false);
//        requestOptions = RequestOptions.circleCropTransform();
        ivMessage = view.findViewById(R.id.iv_message);
        ivBack = view.findViewById(R.id.iv_back);
        tvTitle = view.findViewById(R.id.head_tv_title);
        unbinder = ButterKnife.bind(this, view);
        initData();
        //观察模式
        if(eventBus()){
            EventBus.getDefault().register(this);
        }
        return view;
    }
    @Override
    public int initLayout(){
        return 0;
    }

    @Override
    public void initData() {
        if(loadingView == null){
            loadingView = new LoadingView();
        }
        if(ivMessage!=null){
            ivMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //消息列表
                    ActivityUtils.startActivity(getActivity(), MessageActivity.class);
                }
            });
        }
    }

    @Override
    public boolean eventBus() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder!=null && unbinder != Unbinder.EMPTY){
            unbinder.unbind();
            unbinder = null;
        }
        if(mPresent!=null){
            //销毁
            mPresent.onDestroy();
        }
        //视图销毁时，进行移除
        if(eventBus()){
            EventBus.getDefault().unregister(this);
        }
        operatingView = true;
//        if(loadingView!=null&& loadingView.isAdded() && !loadingView.isHidden()){
//            loadingView.dismiss();
//        }
    }

    @Override
    public void onSuccess(String t) {
        if(isDetached()){return;}
        if(loadingView!=null) {
            loadingView.dismiss();
        }
    }

    @Override
    public void onFailure(String message, int error){
        if(isDetached()){return;}
        if(loadingView!=null) {
            loadingView.dismiss();
        }
    }

    @Override
    public void onThrows(String message, int error) {
        if(isDetached()){return;}
        if(loadingView!=null) {
            loadingView.dismiss();
        }
    }
}
