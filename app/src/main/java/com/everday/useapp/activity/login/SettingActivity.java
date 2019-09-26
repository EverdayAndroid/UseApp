package com.everday.useapp.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.everday.useapp.R;
import com.everday.useapp.UseApplication;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.dialog.UseDialog;
import com.everday.useapp.entity.BaseModel;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.AppUtils;
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
 * create at 2019/9/11
 * description: 设置
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.iv_msg_setting)
    ImageView imageView;
    //grab值为1开启自动抢单2关闭
    private Integer grab = 1;
    private Boolean show;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_setting;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("设置");
        ivMessage.setVisibility(View.GONE);
        String localVersionName = AppUtils.getLocalVersionName(UseApplication.getApplication());
        tvVersion.setText(localVersionName);
        show = (Boolean) PreferencesUtils.get(UserConfig.AUTOMATIC,false);
        imageView.setImageResource(show == true ?R.mipmap.icon_bt:R.mipmap.icon_uncheck);
    }

    @OnClick({R.id.tv_out_login,R.id.iv_msg_setting})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_out_login:
                UseDialog.getInstance("是否确定要退出当前账号?", "确定", "取消").show(getSupportFragmentManager(), "usedialog");
                break;
            case R.id.iv_msg_setting:
                automatic();
                break;
        }
    }

    /**
     * 自动抢单
     */
    public void automatic(){
        if(show){
            grab = 2;
        }else{
            grab = 1;
        }
        String gson = "{ \"grab\":\""+grab+"\",\"tele\":\""+telePhone+"\"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE),gson);
        HttpManager.getInstance().post(Constants.HOST+ API.AUTOMATIC,this,requestBody);
    }


    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        show = grab == 1?true:false;
        imageView.setImageResource(grab == 1?R.mipmap.icon_bt:R.mipmap.icon_uncheck);
        PreferencesUtils.put(UserConfig.AUTOMATIC,grab == 1 ?true:false,true);
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
