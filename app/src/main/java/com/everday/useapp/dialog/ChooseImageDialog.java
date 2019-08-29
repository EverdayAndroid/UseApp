package com.everday.useapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.everday.useapp.R;
import com.everday.useapp.activity.login.view.ImageInterFace;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/29
 * description: 描述
 */
public class ChooseImageDialog extends Dialog {
    //从相册选择
    @BindView(R.id.tv_album_choose)
    TextView tvAlbumChoose;
    //拍照
    @BindView(R.id.tv_photo_choose)
    TextView tvPhotoChoose;
    //取消
    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    private Context mContext;
    private Unbinder unbinder;
    private ImageInterFace interFace;
    private int type;
    public ChooseImageDialog(Context context, int themeResId, ImageInterFace interFace) {
        super(context, themeResId);
        this.mContext = context;
        this.interFace = interFace;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_choose_image, null);
        setContentView(view);
        unbinder = ButterKnife.bind(this);

        setCanceledOnTouchOutside(true);
        Window dialogWindow = getWindow();
        if(dialogWindow !=null){
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            // 获取屏幕宽、高用
            DisplayMetrics d = mContext.getResources().getDisplayMetrics();
            // 宽度设置为屏幕的0.9
            lp.width = (int) (d.widthPixels * 0.9);
            lp.y = 20;
            dialogWindow.setAttributes(lp);
            dialogWindow.setGravity(Gravity.BOTTOM);
            //添加动画
            dialogWindow.setWindowAnimations(R.style.dialog_style);
        }
    }

    @OnClick({R.id.tv_album_choose, R.id.tv_cancel, R.id.tv_photo_choose})
    void OnClick(View v){
        switch (v.getId()){
            case R.id.tv_photo_choose:
                // 拍照
                interFace.photoChoose();
                cancel();
                break;
            case R.id.tv_album_choose:
                // 从相册选择
                interFace.albumChoose();
                dismiss();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        try{
            unbinder.unbind();
        }catch (IllegalStateException e){}
    }
}
