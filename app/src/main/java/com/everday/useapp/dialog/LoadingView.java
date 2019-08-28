package com.everday.useapp.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.everday.useapp.R;


/**
* data 2019/7/6
* author Everday
* email wangtaohandsome@gmail.com
* desc 等待加载
**/
public class LoadingView extends DialogFragment implements DialogInterface.OnKeyListener {
    private Context mContext;
    private boolean isAdd;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //不显示标题，设置样式windows背景为透明
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.customDialog);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(mContext).inflate(R.layout.loading, container, false);
        getDialog().setOnKeyListener(this);
        return view;
    }

    @Override
    public void show(FragmentManager fragmentManager, String tag) {
        if(isAdd){
            return;
        }
        if(!isAdded() && !isHidden() && fragmentManager.findFragmentByTag(tag)==null){
            isAdd = true;
            super.show(fragmentManager, tag);
        }
    }

    @Override
    public void dismiss() {
        if(isAdded() && !isHidden()){
            isAdd = false;
            super.dismiss();
        }
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            isAdd = true;
            dismiss();
        }
        return true;
    }
}
