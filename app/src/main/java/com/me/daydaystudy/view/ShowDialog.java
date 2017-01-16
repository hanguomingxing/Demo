package com.me.daydaystudy.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.me.daydaystudy.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/15.
 */

public class ShowDialog extends BottomSheetDialog {

    private String url = "还没有输入分享地址";
    private Context context;
    private View inflate;


    @Bind(R.id.wechat)
    Button wechat;
    @Bind(R.id.wxFriendCircle)
    Button wxFriendCircle;
    @Bind(R.id.qzone)
    Button qzone;
    @Bind(R.id.wb)
    Button wb;
    @Bind(R.id.qq)
    Button qq;


    public ShowDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }


    public ShowDialog(Context context, String url) {
        super(context);
        this.context = context;
        this.url = url;
        init();
    }

    public ShowDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
    }

    protected ShowDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void init() {
        inflate = View.inflate(context, R.layout.share_dialog_item, null);
        this.setContentView(inflate);
        ButterKnife.bind(this, this);
        this.show();
    }


    /**
     * 设置url
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }


    @OnClick({R.id.wechat, R.id.wxFriendCircle, R.id.qzone, R.id.wb, R.id.qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wechat:
                Toast.makeText(context, "分享到wechat   " + url, Toast.LENGTH_SHORT).show();
                break;
            case R.id.wxFriendCircle:
                Toast.makeText(context, "分享到wxFriendCircle   " + url, Toast.LENGTH_SHORT).show();
                break;
            case R.id.qzone:
                Toast.makeText(context, "分享到qzone    " + url, Toast.LENGTH_SHORT).show();
                break;
            case R.id.wb:
                Toast.makeText(context, "分享到wb    " + url, Toast.LENGTH_SHORT).show();
                break;
            case R.id.qq:
                Toast.makeText(context, "分享到qq    " + url, Toast.LENGTH_SHORT).show();
                break;
        }
        ButterKnife.unbind(this);
        this.dismiss();
    }


}
