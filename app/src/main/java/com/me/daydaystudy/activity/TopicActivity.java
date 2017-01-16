package com.me.daydaystudy.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;

public class TopicActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        TextView title_text = (TextView) findViewById(R.id.title_text);
        title_text.setText("哈哈");
    }
}
