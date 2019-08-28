package com.everday.useapp.activity.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.everday.useapp.R;
import com.everday.useapp.activity.home.fragment.HomeFragment;
import com.everday.useapp.activity.home.fragment.MineFragment;
import com.everday.useapp.activity.home.fragment.TaskFragment;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.network.http.CallBack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/27
 * description: 首页
 */
public class HomeActivity extends BaseActivity implements CallBack {

    @BindView(R.id.radio_home)
    RadioButton radioHome;
    @BindView(R.id.radio_mission)
    RadioButton radioMission;
//    @BindView(R.id.radio_money)
//    RadioButton radioMoney;
    @BindView(R.id.radio_mine)
    RadioButton radioMine;
    @BindView(R.id.radio_main)
    RadioGroup radioMain;
    private String[] menu = new String[]{"HomeFragment","TaskFragment","MineFragment"};
    private int current = 0;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_home;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        showFragment();
    }

    /**
     * 显示碎片
     */
    public void showFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0;i<menu.length;i++){
            Fragment fragmentByTag = fragmentManager.findFragmentByTag(menu[i]);
            if(fragmentByTag!=null && fragmentByTag.isAdded()){
                fragmentTransaction.hide(fragmentByTag);
            }
        }
        Fragment fragment = fragmentManager.findFragmentByTag(menu[current]);
        if(fragment == null){
            fragment = initFragment(current);
        }
        if(fragment.isAdded()){
            fragmentTransaction.show(fragment);
        }else{
            fragmentTransaction.add(R.id.index_main,fragment,menu[current]);
        }
        fragmentTransaction.commit();
    }

    /**
     * 实例化碎片
     * @param current
     * @return
     */
    public Fragment initFragment(int current){
        Fragment fragment = null;
        switch (current){
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new TaskFragment();
                break;
            case 2:
                fragment = new MineFragment();
                break;
            default:break;

        }
        return fragment;
    }

    @OnClick({R.id.radio_home,R.id.radio_mission,R.id.radio_money})
    void OnClick(View view){
        radioHome.setChecked(false);
        radioMission.setChecked(false);
        radioMine.setChecked(false);
        switch (view.getId()){
            case R.id.radio_home:
                current = 0;
                radioHome.setChecked(true);
                break;
            case R.id.radio_mission:
                current = 1;
                radioMission.setChecked(true);
                break;
            case R.id.radio_money:
                current = 2;
                radioMine.setChecked(true);
                break;
        }
    }

    public void chekcVersion(){
        FormBody.Builder builder = new FormBody.Builder();
        HttpManager.getInstance().post("",this,builder.build());
    }

    @Override
    public void onSuccess(String t) {

    }

    @Override
    public void onFailure(String message, int error) {

    }
}
