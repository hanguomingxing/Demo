package com.me.daydaystudy.activity;

import android.os.Bundle;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.utils.TitleHelp;

public class CollectionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        initTitle();
    }

    private void initTitle() {
        new TitleHelp(this).setCommonTitle("我的收藏");
    }
}
