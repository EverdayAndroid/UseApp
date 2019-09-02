package com.everday.useapp.activity.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.everday.useapp.R;
import com.everday.useapp.activity.login.view.ImageInterFace;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.dialog.ChooseImageDialog;
import com.everday.useapp.dialog.UseDialog;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.FileUtils;
import com.everday.useapp.utils.GlideCircleTransform;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/29
 * description: 个人中心
 */
public class PersonalActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener, ImageInterFace {
    @BindView(R.id.image_photo)
    RoundedImageView imagePhoto;
    @BindView(R.id.text_nickName)
    TextView textNickName;
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;
    private File fileName;
    private String compressPath;
    private ChooseImageDialog chooseImageDialog;

    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTakePhoto().onCreate(savedInstanceState);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_personal;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("个人中心");
        ivMessage.setVisibility(View.GONE);
    }

    @OnClick({R.id.layout_photo, R.id.layout_change_password, R.id.tv_out_login,R.id.layout_nickName})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.layout_photo:
                chooseImageDialog = new ChooseImageDialog(this, R.style.MyDialogStyle, this);
                chooseImageDialog.show();
                break;
            case R.id.layout_change_password:
                ActivityUtils.startActivity(this, ForgetPasswordActivity.class);
                break;
            case R.id.tv_out_login:
                UseDialog.getInstance("是否确定要退出当前账号?", "确定", "取消").show(getSupportFragmentManager(), "usedialog");
                break;
            case R.id.layout_nickName:
                ActivityUtils.startActivityForResult(this, UserNameActivity.class);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                String name = data.getStringExtra("name");
                textNickName.setText(name);
                break;
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        compressPath = result.getImage().getCompressPath();
        Glide.with(this).load(compressPath).bitmapTransform(new GlideCircleTransform(this)).into(imagePhoto);
    }

    @Override
    public void takeFail(TResult result, String msg) {
    }

    @Override
    public void takeCancel() {

    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    public void albumChoose() {
        //压缩
        CompressConfig compressConfig = new CompressConfig.Builder()
                .enableReserveRaw(false)
                .setMaxPixel(1200)
                .create();
        takePhoto.onEnableCompress(compressConfig, false);
        fileName = FileUtils.getInstance().createFile(FileUtils.getInstance().getImageDir(),
                String.valueOf(UUID.randomUUID().toString() + ".png"));
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder()
                .setWithOwnGallery(true);
        takePhoto.setTakePhotoOptions(builder.create());
        takePhoto.onPickFromGalleryWithCrop(Uri.fromFile(fileName), getCropOptions());
    }

    @Override
    public void photoChoose() {
        //压缩
        CompressConfig compressConfig = new CompressConfig.Builder()
                .enableReserveRaw(true)
                .setMaxPixel(1200)
                .create();
        takePhoto.onEnableCompress(compressConfig, true);
        fileName = FileUtils.getInstance().createFile(FileUtils.getInstance().getImageDir(),
                String.valueOf(UUID.randomUUID().toString() + ".png"));
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder()
                .setWithOwnGallery(true);
        takePhoto.setTakePhotoOptions(builder.create());
        takePhoto.onPickFromCaptureWithCrop(Uri.fromFile(fileName), getCropOptions());
    }

    /**
     * 裁剪参数
     */
    public CropOptions getCropOptions() {
        CropOptions cropOptions = new CropOptions.Builder()
                .setAspectX(1)
                .setAspectY(1)
                .setOutputX(500)
                .setOutputY(500)
                .setWithOwnCrop(true)
                .create();
        return cropOptions;
    }


    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        if (isFinishing()) {
            return;
        }
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
        if (isFinishing()) {
            return;
        }
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
        if (isFinishing()) {
            return;
        }
    }
}
