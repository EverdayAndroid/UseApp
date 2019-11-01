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
import com.everday.useapp.dialog.BamToast;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/19
 * description: 身份证
 */
public class LdentityCodeActivity extends BaseActivity {

    @BindView(R.id.text_nickName)
    EditText textNickName;
    private String name;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_ldentity_code;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("身份证");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("确定");
        ivMessage.setVisibility(View.GONE);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            String name = extras.getString("code");
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
                setResult(1,new Intent().putExtra("code",name));
                finish();
                break;
        }
    }
}
