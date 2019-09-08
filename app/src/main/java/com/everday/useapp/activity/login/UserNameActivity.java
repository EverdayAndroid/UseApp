package com.everday.useapp.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.everday.useapp.R;
import com.everday.useapp.UseApplication;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.entity.BaseModel;
import com.everday.useapp.entity.UserBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/2
 * description: 修改昵称
 */
public class UserNameActivity extends BaseActivity{
    @BindView(R.id.text_nickName)
    EditText textNickName;
    private String name;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_user_name;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("昵称");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("确定");
        ivMessage.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        if(isFinishing()){return;}
        BaseModel baseModel = GsonUtils.getInstance().parseJsonToBean(t,BaseModel.class);
        setResult(1,new Intent().putExtra("name",name));
        BamToast.show(this,baseModel.getMessage());
        finish();
    }

    @OnClick({R.id.tv_right})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_right:
                name = textNickName.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    BamToast.show(UseApplication.getApplication(),textNickName.getHint());
                    return;
                }

                UserBean userBean = new UserBean();
                userBean.setTele(PreferencesUtils.get(UserConfig.TELE,"").toString());
                userBean.setNickName(name);
                String gson = GsonUtils.getInstance().toObjectGson(userBean);
                RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE),gson);
                HttpManager.getInstance().post(Constants.HOST+ API.UPDATENICKNAME,this,requestBody);
                break;
        }
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
    }
}
