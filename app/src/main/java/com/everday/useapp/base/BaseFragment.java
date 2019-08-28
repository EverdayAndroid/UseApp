package com.everday.useapp.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.everday.useapp.dialog.LoadingView;
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
public class BaseFragment<P extends BasePresenter> extends RxFragment implements IFragment {
    private Unbinder unbinder;
    protected View view;
    protected P mPresent;
    protected LoadingView loadingView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(initLayout(), container, false);
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
//        if(loadingView!=null&& loadingView.isAdded() && !loadingView.isHidden()){
//            loadingView.dismiss();
//        }
    }
}
