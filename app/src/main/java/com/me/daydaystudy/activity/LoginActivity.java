package com.me.daydaystudy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.bean.LoginBean;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;
import com.me.daydaystudy.utils.TextViewUtils;

import java.util.HashMap;

import retrofit2.Call;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText phone;
    private EditText password;
    private Button but_login;
    private Button register;
    private TextView presentation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initTitle();
        initView();


    }


    /**
     * 初始化view
     */
    private void initView() {
        phone = (EditText) findViewById(R.id.et_phone);
        password = (EditText) findViewById(R.id.et_password);
        register = (Button) findViewById(R.id.register);
        but_login = (Button) findViewById(R.id.but_login);
        presentation = (TextView) findViewById(R.id.presentation);
        findViewById(R.id.forget_pwd).setOnClickListener(this);
        but_login.setOnClickListener(this);
        register.setOnClickListener(this);


    }

    /**
     * 初始化title
     */
    private void initTitle() {
        TextView titleView = (TextView) findViewById(R.id.title_text);
        findViewById(R.id.title_back).setVisibility(View.VISIBLE);
        titleView.setVisibility(View.VISIBLE);
        titleView.setText("登陆");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //注册界面
            case R.id.register:
                jumpActivity(RegisterActivity.class);
                break;
            //忘记密码
            case R.id.forget_pwd:
                jumpActivity(ForgetPasswordActivity.class);
                break;
            case R.id.but_login:
                HashMap<String, String> map = new HashMap<>();
                map.put("userName", TextViewUtils.getText(phone));
                map.put("password", TextViewUtils.getText(password));
                map.put("dosubmit", "1");
                HttpManger.postMethodAndSaveCookie(ConstantUtils.signIn, map, new MyCallBack() {
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "连接超时", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response) {
                        presentation.setText("");
                        final LoginBean loginBean = new Gson().fromJson(response, LoginBean.class);
                        switch (loginBean.getStatus()) {
                            case 200:           //登陆成功
                                System.out.println(response);
                                finish();
                                break;
                            case 201:           //密码错误
                            case 202:           //用户不存在
                                presentation.setText(loginBean.getMsg());
                                break;
                        }

                    }
                });
                break;
        }

    }
}
