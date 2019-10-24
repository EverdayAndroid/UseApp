package com.everday.useapp.activity.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.everday.useapp.R;
import com.everday.useapp.UseApplication;
import com.everday.useapp.activity.login.view.ImageInterFace;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.dialog.ChooseImageDialog;
import com.everday.useapp.dialog.UseDialog;
import com.everday.useapp.entity.BaseModel;
import com.everday.useapp.entity.UserBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.FileUtils;
import com.everday.useapp.utils.GlideCircleTransform;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PreferencesUtils;
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
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
    EditText textNickName;
    @BindView(R.id.tv_auth_state)
    TextView tvAuthState;
    @BindView(R.id.tv_card_no)
    TextView tvCardNo;
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;
    private File fileName;
    private String compressPath;
    private ChooseImageDialog chooseImageDialog;
    private int netCode;
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
        String userName = (String) PreferencesUtils.get(UserConfig.USERNAME, "");
        String tele = (String) PreferencesUtils.get(UserConfig.TELE, "");
        //是否认证
        Boolean certification = (Boolean) PreferencesUtils.get(UserConfig.CERTIFICATION, false);
        tvAuthState.setText(certification == true ? "已认证" : "未认证");
        textNickName.setText(userName);
        String bankcard = (String) PreferencesUtils.get(UserConfig.BANKCARD,"");
        if(TextUtils.isEmpty(bankcard)) {
            tvCardNo.setText("未绑卡");
        }else{
            tvCardNo.setText("已绑卡");
        }
//        GlideApp.with(this)
//                .load(Constants.AVATAR+tele)
//                .apply(requestOptions)
//                .skipMemoryCache(false)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(imagePhoto);
        Glide.with(this)
                .load(Constants.AVATAR + tele)
                .bitmapTransform(new GlideCircleTransform(this))
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imagePhoto);
        textNickName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId){
                    case EditorInfo.IME_ACTION_DONE:
//                        textNickName.clearFocus();
                        updateName();
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 修改昵称
     */
    public void updateName(){
        String name = textNickName.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            BamToast.show(UseApplication.getApplication(),textNickName.getHint());
            return;
        }
        netCode = 2;
        UserBean userBean = new UserBean();
        userBean.setTele(PreferencesUtils.get(UserConfig.TELE,"").toString());
        userBean.setNickName(name);
        String gson = GsonUtils.getInstance().toObjectGson(userBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE),gson);
        HttpManager.getInstance().post(Constants.HOST+ API.UPDATENICKNAME,this,requestBody);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //是否认证
        Boolean certification = (Boolean) PreferencesUtils.get(UserConfig.CERTIFICATION, false);
        tvAuthState.setText(certification == true ? "已认证" : "未认证");
        String bankcard = (String) PreferencesUtils.get(UserConfig.BANKCARD,"");
        tvCardNo.setText(TextUtils.isEmpty(bankcard) == true ? "未绑卡":"已绑卡");
    }
    @OnClick({R.id.layout_photo, R.id.layout_change_password, R.id.tv_out_login, R.id.layout_nickName, R.id.layout_auth, R.id.layout_card})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.layout_photo:
                chooseImageDialog = new ChooseImageDialog(this, R.style.MyDialogStyle, this);
                chooseImageDialog.show();
                break;
            case R.id.layout_change_password:
                ActivityUtils.startActivity(this, CheckPasswordActivity.class);
                break;
            case R.id.tv_out_login:
                UseDialog.getInstance("是否确定要退出当前账号?", "确定", "取消").show(getSupportFragmentManager(), "usedialog");
                break;
            case R.id.layout_nickName:
//                ActivityUtils.startActivityForResult(this, UserNameActivity.class);
                break;
            case R.id.layout_auth:
                ActivityUtils.startActivity(this, LdentityActivity.class);
                break;
            case R.id.layout_card:
                String bankcard = (String) PreferencesUtils.get(UserConfig.BANKCARD, "");
                //判断是否绑定过银行卡
                if (TextUtils.isEmpty(bankcard)) {
                    ActivityUtils.startActivityForResult(this, BankCardActivity.class,2);
                } else {
                    ActivityUtils.startActivity(this, BankCardDetailsActivity.class);
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (data == null) {
                    return;
                }
                String name = data.getStringExtra("name");
                textNickName.setText(name);
                break;
            case 2:
                String bankcard = (String) PreferencesUtils.get(UserConfig.BANKCARD,"");
                if(TextUtils.isEmpty(bankcard)) {
                    tvCardNo.setText("未绑卡");
                }else{
                    tvCardNo.setText("已绑卡");
                }
                break;
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        compressPath = result.getImage().getCompressPath();
//        GlideApp.with(this).load(compressPath).apply(requestOptions).into(imagePhoto);
        Glide.with(this).load(compressPath).transform(new GlideCircleTransform(this)).into(imagePhoto);
        netCode = 1;
        loadingView.show(getSupportFragmentManager(), "loading");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("avatar",
                        "avatar.png",
                        RequestBody.create(MultipartBody.FORM, new File(compressPath)))
                .build();
        HttpManager.getInstance().post(Constants.HOST + API.UPLOADAVATAR, this, requestBody);
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
        BaseModel baseModel = GsonUtils.getInstance().parseJsonToBean(t, BaseModel.class);
        BamToast.show(this,baseModel.getMessage());
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
        if (isFinishing()) {
            return;
        }
        BamToast.show(this,message);
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
        if (isFinishing()) {
            return;
        }
    }
}
