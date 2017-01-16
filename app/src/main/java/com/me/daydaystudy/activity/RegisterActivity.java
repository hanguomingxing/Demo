package com.me.daydaystudy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.utils.LogUtils;

import static com.me.daydaystudy.R.id.title_text;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initTitle();

    }

    /**
    /**
     * 初始化title
     */
    private void initTitle() {
        TextView titleView = (TextView) findViewById(R.id.title_text);
        findViewById(R.id.title_back).setVisibility(View.VISIBLE);
        titleView.setVisibility(View.VISIBLE);
        titleView.setText("注册");
    }
}
