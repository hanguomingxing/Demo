package com.me.daydaystudy.activity;

import android.os.Bundle;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.utils.TitleHelp;

public class FeedBackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initTitle();
    }

    private void initTitle() {
        new TitleHelp(this).setCommonTitle("意见反馈");
    }
}
