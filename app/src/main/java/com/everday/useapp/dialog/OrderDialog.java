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
import android.widget.Button;
import android.widget.TextView;

import com.everday.useapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/1
 * description: 接单提示框
 */
public class OrderDialog extends DialogFragment implements DialogInterface.OnKeyListener {
    @BindView(R.id.dialog_des)
    TextView dialogDes;
    @BindView(R.id.dialog_affirm_btn)
    Button dialogAffirmBtn;
    private Context mContext;
    private Unbinder unbinder;
    private boolean isAdd;
    private String leftStr, rightStr, des;
    public static OrderDialog getInstance(String des) {
        OrderDialog orderDialog = new OrderDialog();
        Bundle bundle = new Bundle();
        bundle.putString("des",des);
        orderDialog.setArguments(bundle);
        return orderDialog;
    }
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_order, container, false);
        getDialog().setOnKeyListener(this);
        unbinder = ButterKnife.bind(this, view);
        Bundle arguments = getArguments();
        des = arguments.getString("des");
        leftStr = arguments.getString("leftStr");
        rightStr = arguments.getString("rightStr");
        dialogDes.setText(des);
        return view;
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

    @OnClick({R.id.dialog_affirm_btn})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_affirm_btn:
                dismiss();
                break;
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
}
