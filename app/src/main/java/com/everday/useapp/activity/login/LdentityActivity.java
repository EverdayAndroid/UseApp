package com.everday.useapp.activity.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.everday.useapp.R;
import com.everday.useapp.activity.login.view.ImageInterFace;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.dialog.ChooseImageDialog;
import com.everday.useapp.entity.BaseModel;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.EverdayLog;
import com.everday.useapp.utils.FileUtils;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PreferencesUtils;
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

import java.io.File;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
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
public class LdentityActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener, ImageInterFace {
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
    private String compressPath;
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
        super.initView(savedInstanceState);
        return R.layout.activity_ldentity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("实名认证");
        ivMessage.setVisibility(View.GONE);
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
                if (certification) {
                    return;
                }
//                Bundle bundle = new Bundle();
//                bundle.putString("name",tvName.getText().toString());
//                ActivityUtils.startActivityForResult(this,LdentityNameActivity.class,bundle,1);
                break;
            case R.id.etCode:
                if (certification) {
                    return;
                }
//                Bundle bundle1 = new Bundle();
//                bundle1.putString("code",tvCode.getText().toString());
//                ActivityUtils.startActivityForResult(this,LdentityCodeActivity.class,bundle1,2);
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
            finish();
        }
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
        BamToast.show(message);
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
        BamToast.show(message);
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
                tvName.setText(name);
                break;
            case 2:
                if (data == null) {
                    return;
                }
                String code = data.getStringExtra("code");
                tvCode.setText(code);
                break;
        }
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

    @Override
    public void takeSuccess(TResult result) {
        compressPath = result.getImage().getCompressPath();
        if (netCode == 1) {
            Glide.with(this).load(compressPath).into(ivPhotoOne);
        } else if (netCode == 2) {
            Glide.with(this).load(compressPath).into(ivPhotoTwo);
        }
        loadingView.show(getSupportFragmentManager(), "loading");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(netCode == 1 ? "positive" : "back",
                        "avatar.png",
                        RequestBody.create(MultipartBody.FORM, new File(compressPath)))
                .build();
        HttpManager.getInstance().post(Constants.HOST + API.UPLOADIDENTITY, this, requestBody);
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


}
