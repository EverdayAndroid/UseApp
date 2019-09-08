package com.everday.useapp.activity.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.everday.useapp.R;
import com.everday.useapp.activity.login.LoginActivity;
import com.everday.useapp.activity.login.PersonalActivity;
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
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.FormBody;
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
        String gson = "{\"tele\":\""+PreferencesUtils.get(UserConfig.TELE, "").toString()+"\"}";
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
        }, RequestBody.create(MediaType.parse(Constants.CONTENTYPE),gson));
    }

    @OnClick({R.id.ll_info, R.id.ll_money})
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
        }
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        UserInfoBean userInfoBean = GsonUtils.getInstance().parseJsonToBean(t, UserInfoBean.class);
        PreferencesUtils.put(UserConfig.USERNAME, userInfoBean.getData().getAppAccount().getNickName(), false);
        PreferencesUtils.put(UserConfig.TOKEN, userInfoBean.getData().getAccessToken(), false);
        PreferencesUtils.put(UserConfig.AVATAR, userInfoBean.getData().getAppAccount().getAvatar(), false);
        PreferencesUtils.put(UserConfig.TELE, userInfoBean.getData().getAppAccount().getTele(), false);
        Glide.with(this).load(Constants.AVATAR+userInfoBean.getData().getAppAccount().getTele()).into(ivPhoto);
        if (isVisible()) {
            String userName = (String) PreferencesUtils.get(UserConfig.USERNAME, "");
            String avatar = (String) PreferencesUtils.get(UserConfig.AVATAR, "");
            Glide.with(this).load(avatar).into(ivPhoto);
            tvName.setText(userName);
            String replaceStr = userInfoBean.getData().getAppAccount().getTele().substring(3,5);
            String phone = userInfoBean.getData().getAppAccount().getTele().replace(replaceStr,"****");
            tvPhone.setText(phone);
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

    @Override
    public void onStart() {
        super.onStart();
        String userName = (String) PreferencesUtils.get(UserConfig.USERNAME, "");
        String avatar = (String) PreferencesUtils.get(UserConfig.AVATAR, "");
        String tele = (String) PreferencesUtils.get(UserConfig.TELE,"");
        Glide.with(this).load(avatar).into(ivPhoto);
        tvName.setText(userName);
        String replaceStr = tele.substring(3,8);
        String phone = tele.replace(replaceStr,"****");
        tvPhone.setText(phone);
    }
}
