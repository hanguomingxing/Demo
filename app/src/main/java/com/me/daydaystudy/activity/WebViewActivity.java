package com.me.daydaystudy.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;

public class WebViewActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        String content = getIntent().getStringExtra("content");
//        webView.loadDataWithBaseURL("", content, "text/html", "UTF-8", "");
        webView.loadData(content,"text/html", "UTF-8");
    }
}
