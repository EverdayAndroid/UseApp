package com.everday.useapp.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.everday.useapp.R;
import com.everday.useapp.activity.login.LoginActivity;
import com.everday.useapp.activity.login.RegisteredActivity;
import com.everday.useapp.activity.login.UseActivity;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.entity.BaseModel;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.network.http.CallBack;
import com.everday.useapp.utils.ActivityManagement;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/1
 * description: 描述
 */
public class UseDialog extends DialogFragment implements DialogInterface.OnKeyListener {
    @BindView(R.id.dialog_title)
    TextView dialogTitle;
    @BindView(R.id.dialog_message_tv)
    TextView dialogMessageTv;
    @BindView(R.id.dialog_leftbtn)
    Button dialogLeftbtn;
    @BindView(R.id.dialog_rightbtn)
    Button dialogRightbtn;
    @BindView(R.id.dialog_twobtn_layout)
    LinearLayout dialogTwobtnLayout;
    @BindView(R.id.dialog_affirm_btn)
    Button dialogAffirmBtn;
    Unbinder unbinder;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    private Context mContext;
    private boolean isAdd;
    private String leftStr, rightStr, des, title;
    private int leftColor;
    private int rightColor;
    private int type;

    public static UseDialog getInstance(String des, String leftStr, String rightStr) {
        UseDialog useDialog = new UseDialog();
        Bundle bundle = new Bundle();
        bundle.putString("des", des);
        bundle.putString("leftStr", leftStr);
        bundle.putString("rightStr", rightStr);
        useDialog.setArguments(bundle);
        return useDialog;
    }

    public static class Builder {
        private UseDialog useDialog;
        private String des;
        private String leftStr;
        private String rightStr;
        private String title;
        @ColorRes
        private int leftColor;
        @ColorRes
        private int rightColor;
        private int type;

        public Builder() {
            useDialog = new UseDialog();
        }

        public UseDialog builder() {
            Bundle bundle = new Bundle();
            bundle.putString("des", des);
            bundle.putString("leftStr", leftStr);
            bundle.putString("title", title);
            bundle.putString("rightStr", rightStr);
            bundle.putInt("leftColor", leftColor);
            bundle.putInt("rightColor", rightColor);
            bundle.putInt("type", type);
            useDialog.setArguments(bundle);
            return useDialog;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setLeftStr(String leftStr) {
            this.leftStr = leftStr;
            return this;
        }

        public Builder setDesc(String des) {
            this.des = des;
            return this;
        }

        public Builder setRightStr(String rightStr) {
            this.rightStr = rightStr;
            return this;
        }

        public Builder setLeftColor(@ColorRes int leftColor) {
            this.leftColor = leftColor;
            return this;
        }

        public Builder setRightColor(@ColorRes int rightColor) {
            this.rightColor = rightColor;
            return this;
        }

        public Builder setType(int type) {
            this.type = type;
            return this;
        }

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_ask_alert, container, false);
        getDialog().setOnKeyListener(this);
        unbinder = ButterKnife.bind(this, view);
        Bundle arguments = getArguments();
        des = arguments.getString("des");
        leftStr = arguments.getString("leftStr");
        rightStr = arguments.getString("rightStr");
        title = arguments.getString("title");
        leftColor = arguments.getInt("leftColor", -1);
        rightColor = arguments.getInt("rightColor", -1);
        type = arguments.getInt("type", 0);


        dialogMessageTv.setText(des);
        dialogLeftbtn.setText(leftStr);
        dialogRightbtn.setText(rightStr);
        if (leftColor != -1) {
            dialogTitle.setText(title);
            dialogLeftbtn.setTextColor(leftColor);
            dialogMessageTv.setTextColor(Color.parseColor("#909090"));
            tv1.setText("<<用户许可协议>>");
            tv2.setText("<<隐私政策>>");
            tv1.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);
        }
        if (rightColor != -1) {
            dialogRightbtn.setTextColor(rightColor);
        }
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

    @OnClick({R.id.dialog_leftbtn, R.id.dialog_rightbtn,R.id.tv1,R.id.tv2})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_leftbtn:
                if (type == 0) {  //退出登陆
                    logout();
                } else {
                    dismiss();
                    //关闭当前界面
                    ActivityManagement.getInstance().finishActivity(RegisteredActivity.class);
                }
                break;
            case R.id.dialog_rightbtn:
                if (type == 0) {
                    dismiss();
                } else {
                    dismiss();
                }
                break;
            case R.id.tv1:
                ActivityUtils.startActivity(getActivity(),UseActivity.class);
                break;
            case R.id.tv2:
                startActivity(new Intent(getActivity(),UseActivity.class).putExtra("type",1));
                break;
        }
    }

    /**
     * 退出
     */
    public void logout() {
        HttpManager.getInstance().get(Constants.HOST + API.OUTLOGIN, null, new CallBack() {
            @Override
            public void onSuccess(String t) throws Exception {
                BaseModel baseModel = GsonUtils.getInstance().parseJsonToBean(t, BaseModel.class);
                PreferencesUtils.clearPreferences();
                BamToast.show(baseModel.getMessage());
                ActivityManagement.getInstance().finishAll();
                ActivityUtils.startActivity(getActivity(), LoginActivity.class);
                dismiss();
            }

            @Override
            public void onFailure(String message, int error) throws Exception {
                BamToast.show(message);
            }

            @Override
            public void onThrows(String message, int error) {
                BamToast.show(message);
            }
        });
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
