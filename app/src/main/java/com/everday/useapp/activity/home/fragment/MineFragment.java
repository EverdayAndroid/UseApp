package com.everday.useapp.activity.home.fragment;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.everday.useapp.R;
import com.everday.useapp.activity.login.ElectronicActivity;
import com.everday.useapp.activity.login.LoginActivity;
import com.everday.useapp.activity.login.PersonalActivity;
import com.everday.useapp.activity.login.SettingActivity;
import com.everday.useapp.activity.money.MoneyActivity;
import com.everday.useapp.base.BaseFragment;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.entity.UserInfoBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.network.http.CallBack;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.utils.EverdayLog;
import com.everday.useapp.utils.GlideCircleTransform;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/27
 * description: 我的
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_author)
    TextView tvAuthor;

    @Override
    public int initLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initData() {
        super.initData();
        tvTitle.setText("我的");
        ivBack.setVisibility(View.GONE);
        ivMessage.setVisibility(View.GONE);
        String gson = "{\"tele\":\"" + PreferencesUtils.get(UserConfig.TELE, "").toString() + "\"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE), gson);
        HttpManager.getInstance().post(Constants.HOST + API.USERDETAIL, this, requestBody);
        HttpManager.getInstance().post(Constants.HOST + API.GETAVATAR, new CallBack() {
            @Override
            public void onSuccess(String t) throws Exception {
                EverdayLog.error(t);
            }

            @Override
            public void onFailure(String message, int error) throws Exception {

            }

            @Override
            public void onThrows(String message, int error) {

            }
        }, RequestBody.create(MediaType.parse(Constants.CONTENTYPE), gson));
    }

    @OnClick({R.id.ll_info, R.id.ll_money,R.id.ll_setting,R.id.ll_my_contrat})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_info:
                String tele = (String) PreferencesUtils.get(UserConfig.TELE, "");
                if (tele.isEmpty()) {
                    ActivityUtils.startActivity(getActivity(), LoginActivity.class);
                } else {
                    ActivityUtils.startActivity(getActivity(), PersonalActivity.class);
                }
                break;
            case R.id.ll_money:
                ActivityUtils.startActivity(getActivity(), MoneyActivity.class);
                break;
            case R.id.ll_setting:
                ActivityUtils.startActivity(getActivity(), SettingActivity.class);
                break;
            case R.id.ll_my_contrat:
                ActivityUtils.startActivity(getActivity(), ElectronicActivity.class);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        UserInfoBean userInfoBean = GsonUtils.getInstance().parseJsonToBean(t, UserInfoBean.class);
        PreferencesUtils.put(UserConfig.USERNAME, userInfoBean.getData().getAppAccount().getNickName(), false);
        PreferencesUtils.put(UserConfig.TOKEN, userInfoBean.getData().getAccessToken(), false);
        //身份证名字
        PreferencesUtils.put(UserConfig.CERTIFICATION_NAME,userInfoBean.getData().getAppAccount().getName(),false);
        //身份证号码
        PreferencesUtils.put(UserConfig.CERTIFICATION_CODE,userInfoBean.getData().getAppAccount().getIdCard(),false);
        //记录银行卡信息
        PreferencesUtils.put(UserConfig.BANKCARD,userInfoBean.getData().getAppAccount().getBankNumber(),false);
        PreferencesUtils.put(UserConfig.BANKCARDTYPE,userInfoBean.getData().getAppAccount().getBankType(),false);
        PreferencesUtils.put(UserConfig.BANKCARDPHONE,userInfoBean.getData().getAppAccount().getTele(),false);
        //1未签约，2已签约
        PreferencesUtils.put(UserConfig.SIGN,userInfoBean.getData().getAppAccount().getSign(),false);

        if(userInfoBean.getData().getAppAccount().getIdCard().isEmpty()){
            PreferencesUtils.put(UserConfig.CERTIFICATION,false,false);
        }else{
            PreferencesUtils.put(UserConfig.CERTIFICATION,true,false);
        }

        if(operatingView){return;}
        if (isVisible()) {
            String userName = (String) PreferencesUtils.get(UserConfig.USERNAME, "");
            tvName.setText(userName);
            String replaceStr = userInfoBean.getData().getAppAccount().getTele().substring(3, 7);
            String phone = userInfoBean.getData().getAppAccount().getTele().replace(replaceStr, "****");
            tvPhone.setText(phone);
        }
        tvAuthor.setText(userInfoBean.getData().getAppAccount().getIdCard().isEmpty() ? "未认证" : "已认证");
        tvAuthor.setBackground(userInfoBean.getData().getAppAccount().getIdCard().isEmpty()  ? getResources().getDrawable(R.drawable.shape_vetify_name)
                : getResources().getDrawable(R.drawable.shape_vetify_greenname));
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
    }

    @Override
    public void onResume() {
        super.onResume();
        String tele = (String) PreferencesUtils.get(UserConfig.TELE, "");
//        CircleCrop circleCrop = new CircleCrop();
//        RequestOptions requestOptions = RequestOptions.bitmapTransform(circleCrop);
//        Glide.with(getActivity()).load(Constants.AVATAR+tele).into(ivPhoto);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onStart() {
        super.onStart();
        String userName = (String) PreferencesUtils.get(UserConfig.USERNAME, "");
        String tele = (String) PreferencesUtils.get(UserConfig.TELE, "");
        String avatar = Constants.AVATAR + tele;
        EverdayLog.error(avatar);
//        GlideApp.with(this)
//                .load(avatar)
//                .apply(requestOptions)
//                .skipMemoryCache(false)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(ivPhoto);
        Glide.with(this)
                .load(avatar)
                .transform(new GlideCircleTransform(getContext()))
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(ivPhoto);
        tvName.setText(userName);
        if (tele.length() >= 11) {
            String replaceStr = tele.substring(3, 7);
            String phone = tele.replace(replaceStr, "****");
            tvPhone.setText(phone);
        }

    }
}
