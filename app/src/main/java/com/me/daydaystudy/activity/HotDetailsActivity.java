package com.me.daydaystudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.view.CircleImageView;

public class HotDetailsActivity extends BaseActivity {

    private TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_details);
        initTitle();
    }

    private void initTitle() {
        Intent intent = getIntent();
        TextView hot_userName = (TextView) findViewById(R.id.hot_userName);
        TextView time = (TextView) findViewById(R.id.time);
        time.setText(intent.getStringExtra("time"));
        hot_userName.setText(intent.getStringExtra("userName"));
        CircleImageView hot_userIcon = (CircleImageView) findViewById(R.id.hot_userIcon);
        Glide.with(this).load(intent.getStringExtra("head")).into(hot_userIcon);
        titleView = (TextView) findViewById(R.id.title_text);
        titleView.setVisibility(View.VISIBLE);
        titleView.setText("详情");
        findViewById(R.id.collectView).setVisibility(View.VISIBLE);
        findViewById(R.id.title_back).setVisibility(View.VISIBLE);
        findViewById(R.id.settingView).setVisibility(View.VISIBLE);
    }
}
