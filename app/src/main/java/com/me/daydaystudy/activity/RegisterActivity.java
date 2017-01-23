package com.me.daydaystudy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.send_cede)
    Button sendCede;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.but_submit)
    Button butSubmit;
    @Bind(R.id.che_agreement)
    CheckBox cheAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initTitle();

    }

    /**
     * /**
     * 初始化title
     */
    private void initTitle() {
        TextView titleView = (TextView) findViewById(R.id.title_text);
        findViewById(R.id.title_back).setVisibility(View.VISIBLE);
        titleView.setVisibility(View.VISIBLE);
        titleView.setText("注册");
    }

    @OnClick({R.id.send_cede, R.id.but_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_cede:        //获取验证码

                break;
            case R.id.but_submit:           //提交注册
                Map<String, String> map = new HashMap<>();
                HttpManger.postMethod(ConstantUtils.register, map, new MyCallBack() {
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }

                    @Override
                    public void onResponse(String response) {

                    }
                });
                break;
        }
    }
}
