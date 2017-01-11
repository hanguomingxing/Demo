package com.me.daydaystudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.me.daydaystudy.R;
import com.me.daydaystudy.activity.AddFriendActivity;
import com.me.daydaystudy.base.BaseActivity;

/**
 * @author :   郗琛
 * @date :   2017/1/10
 */

public class AddFriendView extends ImageView {
    public AddFriendView(Context context) {
        super(context);
        init();
    }

    public AddFriendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AddFriendView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 实现方法
     */
    private void init() {
        this.setImageResource(R.drawable.add_friend);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getContext()).jumpActivity(AddFriendActivity.class);
            }
        });
    }
}
