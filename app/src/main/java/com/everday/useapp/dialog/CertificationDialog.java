package com.everday.useapp.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.everday.useapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CertificationDialog extends DialogFragment implements DialogInterface.OnKeyListener {
    @BindView(R.id.rl_exit)
    RelativeLayout rlExit;
    Unbinder unbinder;
    private Context mContext;
    private boolean isAdd;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //不显示标题，设置样式windows背景为透明
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.customDialog1);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_home_require, container, false);
        getDialog().setOnKeyListener(this);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.windowAnimations = R.style.customDialog1;
        window.setAttributes(attributes);
    }

    @Override
    public void show(FragmentManager fragmentManager, String tag) {
        if (isAdd) {
            return;
        }
        if (!isAdded() && !isHidden() && fragmentManager.findFragmentByTag(tag) == null) {
            isAdd = true;
            super.show(fragmentManager, tag);
        }
    }

    @Override
    public void dismiss() {
        if (isAdded() && !isHidden()) {
            isAdd = false;
            super.dismiss();
        }
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            isAdd = true;
            dismiss();
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_exit})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.rl_exit:
                dismiss();
                break;
        }
    }
}
