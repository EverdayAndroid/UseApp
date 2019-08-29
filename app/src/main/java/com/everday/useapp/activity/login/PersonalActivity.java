package com.everday.useapp.activity.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.everday.useapp.R;
import com.everday.useapp.activity.login.view.ImageInterFace;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.dialog.ChooseImageDialog;
import com.everday.useapp.utils.FileUtils;
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

import java.io.File;
import java.util.UUID;

import butterknife.OnClick;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/29
 * description: 个人中心
 */
public class PersonalActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener, ImageInterFace {
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
    }
    @OnClick({R.id.layout_photo})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.layout_photo:
                chooseImageDialog = new ChooseImageDialog(this,R.style.MyDialogStyle,this);
                chooseImageDialog.show();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void takeSuccess(TResult result) {
        compressPath = result.getImage().getCompressPath();
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
}
