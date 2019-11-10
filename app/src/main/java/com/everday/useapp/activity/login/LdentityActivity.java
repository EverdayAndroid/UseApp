package com.everday.useapp.activity.login;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.everday.useapp.R;
import com.everday.useapp.activity.home.HomeActivity;
import com.everday.useapp.activity.login.view.ImageInterFace;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.dialog.ChooseImageDialog;
import com.everday.useapp.entity.BaseModel;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.ActivityManagement;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.CompressUtils;
import com.everday.useapp.utils.EverdayLog;
import com.everday.useapp.utils.FileUtils;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PhotoFromPhotoAlbum;
import com.everday.useapp.utils.PreferencesUtils;
import com.everday.useapp.utils.RotateTransformation;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.wildma.idcardcamera.camera.IDCardCamera;

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
 * create at 2019/9/18
 * description: 实名认证
 */
public class LdentityActivity extends BaseActivity implements ImageInterFace {
    @BindView(R.id.etName)
    EditText tvName;
    @BindView(R.id.etCode)
    EditText tvCode;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.ivPhotoOne)
    ImageView ivPhotoOne;
    @BindView(R.id.ivPhotoTwo)
    ImageView ivPhotoTwo;
    @BindView(R.id.etBankCard)
    EditText etBankCard;

    private String name, code,cardNo;
    //是否认证
    private Boolean certification;
    private ChooseImageDialog chooseImageDialog;
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;
    private File fileName;
    private File compressPath;
    private int netCode,sign;
    public int REQUEST_CODE = 100;
    private String ldentity;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        return R.layout.activity_ldentity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("实名认证");
        ivMessage.setVisibility(View.GONE);
        ldentity = getIntent().getStringExtra("ldentity");
        sign = getIntent().getIntExtra("sign",1);
        tvName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name = s.toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(code)|| TextUtils.isEmpty(cardNo)) {
                    btnSubmit.setEnabled(false);
                    btnSubmit.setClickable(false);
                    btnSubmit.setBackgroundResource(R.mipmap.login_uncheck_bg);
                } else {
                    btnSubmit.setEnabled(true);
                    btnSubmit.setClickable(true);
                    btnSubmit.setBackgroundResource(R.mipmap.login_check_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tvCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                code = s.toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(code)|| TextUtils.isEmpty(cardNo)) {
                    btnSubmit.setEnabled(false);
                    btnSubmit.setClickable(false);
                    btnSubmit.setBackgroundResource(R.mipmap.login_uncheck_bg);
                } else {
                    btnSubmit.setEnabled(true);
                    btnSubmit.setClickable(true);
                    btnSubmit.setBackgroundResource(R.mipmap.login_check_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etBankCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cardNo = s.toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(code)|| TextUtils.isEmpty(cardNo)) {
                    btnSubmit.setEnabled(false);
                    btnSubmit.setClickable(false);
                    btnSubmit.setBackgroundResource(R.mipmap.login_uncheck_bg);
                } else {
                    btnSubmit.setEnabled(true);
                    btnSubmit.setClickable(true);
                    btnSubmit.setBackgroundResource(R.mipmap.login_check_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        String name = (String) PreferencesUtils.get(UserConfig.CERTIFICATION_NAME, "");
        String code = (String) PreferencesUtils.get(UserConfig.CERTIFICATION_CODE, "");
        String backcard = (String) PreferencesUtils.get(UserConfig.BANKCARD, "");
        tvName.setText(name);
        tvCode.setText(code);
        etBankCard.setText(backcard);
        certification = (Boolean) PreferencesUtils.get(UserConfig.CERTIFICATION, false);
        if (certification) {
            tvName.setEnabled(false);
            tvCode.setEnabled(false);
            etBankCard.setEnabled(false);
            ivPhotoOne.setEnabled(false);
            String positive = (String) PreferencesUtils.get(UserConfig.CERTIFICATION_POSITIVE, "");
            String back = (String) PreferencesUtils.get(UserConfig.CERTIFICATION_BACK, "");
            Glide.with(this).load(Constants.HOST+positive).into(ivPhotoOne);
            ivPhotoTwo.setEnabled(false);
            Glide.with(this).load(Constants.HOST+back).into(ivPhotoTwo);
            btnSubmit.setVisibility(View.GONE);
        }
        getListPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE);

        if(ldentity!=null&&sign == 1) {
            btnSubmit.setText("下一步");
        }
    }

    @OnClick({R.id.btn_submit, R.id.etName, R.id.etCode, R.id.ivPhotoOne, R.id.ivPhotoTwo})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                netCode = 3;
                Integer id = (Integer) PreferencesUtils.get(UserConfig.ID, 1);
                loadingView.show(getSupportFragmentManager(), "loading");
                String gson = "{\n" +
                        "    \"idNo\":\"" + code + "\",\n" +
                        "    \"name\":\"" + name + "\",\n" +
                        "    \"id\":\"" + id + "\",\n" +
                        "    \"cardNo\":\"" + cardNo + "\"\n" +
                        "}";
                RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE), gson);
                HttpManager.getInstance().post(Constants.HOST + API.CERTIFICATION, this, requestBody);
                break;
            case R.id.etName:
                break;
            case R.id.etCode:
                break;
            case R.id.ivPhotoOne:
                netCode = 1;
                chooseImageDialog = new ChooseImageDialog(this, R.style.MyDialogStyle, this);
                chooseImageDialog.show();
                break;
            case R.id.ivPhotoTwo:
                netCode = 2;
                chooseImageDialog = new ChooseImageDialog(this, R.style.MyDialogStyle, this);
                chooseImageDialog.show();
                break;
        }
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        BaseModel baseModel = GsonUtils.getInstance().parseJsonToBean(t, BaseModel.class);
        EverdayLog.error(netCode+"");
        if(isFinishing()){return;}
        if (netCode == 1 || netCode == 2) {
            PreferencesUtils.put(UserConfig.CERTIFICATION_POSITIVE, Constants.HOST+baseModel.getData().toString(), false);
            BamToast.show(baseModel.getMessage());
        }else if(netCode == 2){
            PreferencesUtils.put(UserConfig.CERTIFICATION_BACK, Constants.HOST+baseModel.getData().toString(), false);
            BamToast.show(baseModel.getMessage());
        } else if (netCode == 3) {
            PreferencesUtils.put(UserConfig.CERTIFICATION_NAME, name, false);
            PreferencesUtils.put(UserConfig.CERTIFICATION_CODE, code, false);
            PreferencesUtils.put(UserConfig.BANKCARD, cardNo, false);
            PreferencesUtils.put(UserConfig.CERTIFICATION, true, false);
            BamToast.show(baseModel.getMessage());
            if(ldentity!=null && sign == 1){
                Bundle bundle = new Bundle();
                bundle.putString("ldentity",ldentity);
                ActivityUtils.startActivity(this, ElectronicActivity.class,bundle); //跳转电子签约
            }else if(ldentity!=null&& sign == 2){
                ActivityUtils.startActivity(this, HomeActivity.class);
                ActivityManagement.getInstance().finishActivity(LoginActivity.class);
            }else{
                finish();
            }
        }
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
        if(error == Constants.IDENTITY_ERROR){
            if(isFinishing()){return;}
            if(ldentity!=null && sign == 1){
                Bundle bundle = new Bundle();
                bundle.putString("ldentity",ldentity);
                ActivityUtils.startActivity(this, ElectronicActivity.class,bundle); //跳转电子签约
            }else if(ldentity!=null&& sign == 2){
                ActivityUtils.startActivity(this, HomeActivity.class);
                ActivityManagement.getInstance().finishActivity(LoginActivity.class);
            }else{
                finish();
            }
        }else {
            BamToast.show(message);
        }
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
        BamToast.show(message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IDCardCamera.RESULT_CODE) {
            //获取图片路径，显示图片
            final String path = IDCardCamera.getImagePath(data);
            if (!TextUtils.isEmpty(path)) {
                if (requestCode == IDCardCamera.TYPE_IDCARD_FRONT) { //身份证正面
                    Glide.with(this).load(path).bitmapTransform(new RotateTransformation(this,CompressUtils.degree(path))).into(ivPhotoOne);
                } else if (requestCode == IDCardCamera.TYPE_IDCARD_BACK) {  //身份证反面
                    Glide.with(this).load(path).bitmapTransform(new RotateTransformation(this,CompressUtils.degree(path))).into(ivPhotoTwo);
                }
                compressPath = FileUtils.getInstance().createFile(FileUtils.getInstance().getImageDir(),
                        String.valueOf(UUID.randomUUID().toString() + ".png")); //图片压缩地址
                CompressUtils.compressSample(path,compressPath); //进行压缩
                loadingView.show(getSupportFragmentManager(), "loading");
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart(netCode == 1 ? "positive" : "back",
                                "avatar.png",
                                RequestBody.create(MultipartBody.FORM, compressPath))
                        .build();
                HttpManager.getInstance().post(Constants.HOST + API.UPLOADIDENTITY, this, requestBody);
            }
        }
        //相册
        if(requestCode == IDCardCamera.RESULT_ALBUM_CODE){
            Uri uri = data.getData();
            String path = PhotoFromPhotoAlbum.getRealPathFromUri(this, uri);
            if (!TextUtils.isEmpty(path)) {
                Glide.with(this).load(path).bitmapTransform(new RotateTransformation(this,CompressUtils.degree(path))).into(netCode == 1?ivPhotoOne:ivPhotoTwo);
                compressPath = FileUtils.getInstance().createFile(FileUtils.getInstance().getImageDir(),
                        String.valueOf(UUID.randomUUID().toString() + ".png")); //图片压缩地址
                CompressUtils.compressSample(path,compressPath); //进行压缩
                loadingView.show(getSupportFragmentManager(), "loading");
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart(netCode == 1 ? "positive" : "back",
                                "avatar.png",
                                RequestBody.create(MultipartBody.FORM, compressPath))
                        .build();
                HttpManager.getInstance().post(Constants.HOST + API.UPLOADIDENTITY, this, requestBody);
            }
        }
    }

    @Override
    public void albumChoose() {
        IDCardCamera.create(this).openAlbum();
    }

    @Override
    public void photoChoose() {
        IDCardCamera.create(this).openCamera(netCode == 1 ?IDCardCamera.TYPE_IDCARD_FRONT:IDCardCamera.TYPE_IDCARD_BACK);
    }
}
