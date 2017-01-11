package com.me.daydaystudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.activity.SearchActivity;

/**
 * 发帖
 *
 * @author :   郗琛
 * @date :   2017/1/10
 */

public class PostInfoView extends ImageView {

    public PostInfoView(Context context) {
        super(context);
        init();
    }

    public PostInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PostInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 实现方法
     */
    private void init() {
        this.setImageResource(R.drawable.post_publish);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getContext()).jumpActivity(SearchActivity.class);
            }
        });
    }
}
