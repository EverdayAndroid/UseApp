package com.everday.useapp.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.everday.useapp.R;

import java.util.ArrayList;

/**
 * Description: 版本更新弹窗
 * Author:unknown
 * create Date:2018/7/10 18:16
 */
public class UpDateDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 注意，这里不再需要 getActivity().getLayoutInflater(), 因为 BaseDialogFragment 已经返回了正确的 inflater
        View view = inflater.inflate(R.layout.popupwindow_new_version, container, false);
        Bundle bundle = getArguments();
        String versionno;
        int isForbbiden;
        ArrayList<String> updateInfo;
        if (bundle != null) {
            versionno = bundle.getString("versionno");
            isForbbiden = bundle.getInt("isForbbiden");//默认0
            updateInfo = (ArrayList<String>) bundle.getSerializable("updateinfo");
            TextView tvNewVersionInfo = view.findViewById(R.id.tv_new_version_info);
            LinearLayout llNotForbbiden = view.findViewById(R.id.ll_not_forbbiden);
            Button btnImmediateExp = view.findViewById(R.id.btn_immediate_experience);//立即更新
            if (isForbbiden == 0) {//不强制更新
                llNotForbbiden.setVisibility(View.VISIBLE);
            } else {//强制更新
                btnImmediateExp.setVisibility(View.VISIBLE);
            }
            tvNewVersionInfo.setText(new StringBuilder().append("发现新版本").append(versionno).toString());

            btnImmediateExp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (closeListener != null) {
                        closeListener.onDoIt();
                    }
                }
            });
            view.findViewById(R.id.btn_cancel_update).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (closeListener != null) {
                        closeListener.onClose();
                    }
                }
            });
            view.findViewById(R.id.btn_go_update).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (closeListener != null) {
                        closeListener.onDoIt();
                    }
                }
            });
        }
        return view;
    }

    public interface OnCloseListener {
        void onClose();

        void onDoIt();
    }

    public void setOnCloseListener(OnCloseListener closeListener) {
        this.closeListener = closeListener;
    }

    protected OnCloseListener closeListener;
}