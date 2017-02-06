package com.me.daydaystudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.me.daydaystudy.R;

public class PersonalMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_message);
        initTitle();
    }

    private void initTitle() {
        TextView title_text = (TextView) findViewById(R.id.title_text);
        title_text.setVisibility(View.VISIBLE);
        title_text.setText("个人信息");
        findViewById(R.id.title_back).setVisibility(View.VISIBLE);
    }
}
