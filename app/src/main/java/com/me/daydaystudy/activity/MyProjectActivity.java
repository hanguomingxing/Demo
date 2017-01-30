package com.me.daydaystudy.activity;

import android.os.Bundle;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.utils.TitleHelp;

public class MyProjectActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_project);
        initTitle();
    }

    /**
     * 初始化title
     */
    private void initTitle() {
        new TitleHelp(this).setCommonTitle("我的课程");
    }
}
