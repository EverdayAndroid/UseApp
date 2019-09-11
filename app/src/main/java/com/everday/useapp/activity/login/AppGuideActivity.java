package com.everday.useapp.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.everday.useapp.R;
import com.everday.useapp.activity.home.HomeActivity;
import com.everday.useapp.activity.login.adapter.AppGuideAdapter;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.utils.ActivityUtils;
import com.everday.useapp.weight.BeckyBanner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/28
 * description: App启动广告页
 */
public class AppGuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.becky)
    BeckyBanner becky;
    @BindView(R.id.iv)
    ImageView iv;
    private AppGuideAdapter mAdapter;
    private int[] imageId = new int[]{R.mipmap.page_one_19,R.mipmap.page_two_19,R.mipmap.page_three_19,R.mipmap.page_four_19};
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_app_guide;
    }

    @Override
    public boolean hideStatus() {
        return true;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mAdapter = new AppGuideAdapter(this,imageId);
        becky.getTotal(imageId.length);
        viewpager.setAdapter(mAdapter);
        viewpager.addOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        becky.getCurrentCount(i);
        if(imageId.length-1 == i){
            becky.setVisibility(View.GONE);
            iv.setVisibility(View.VISIBLE);
        }else{
            becky.setVisibility(View.VISIBLE);
            iv.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @OnClick({R.id.iv})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.iv:
                ActivityUtils.startActivity(this, HomeActivity.class);
                finish();
                break;
        }
    }
}
