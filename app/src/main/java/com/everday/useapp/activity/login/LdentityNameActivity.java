package com.everday.useapp.activity.login;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.everday.useapp.entity.UserBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/19
 * description: 身份证名称
 */
public class LdentityNameActivity extends BaseActivity {

    @BindView(R.id.text_nickName)
    EditText textNickName;
    private String name;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_ldentity_name;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("昵称");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("确定");
        ivMessage.setVisibility(View.GONE);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            String name = extras.getString("name");
            textNickName.setText(name);
            textNickName.setSelection(name.length());
        }
    }
    @OnClick({R.id.tv_right})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_right:
                name = textNickName.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    BamToast.show(textNickName.getHint());
                    return;
                }
                setResult(1,new Intent().putExtra("name",name));
                finish();
                break;
        }
    }
}
