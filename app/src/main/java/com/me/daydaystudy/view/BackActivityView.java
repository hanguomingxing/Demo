package com.me.daydaystudy.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.me.daydaystudy.R;

/**
 * @author :   郗琛
 * @date :   2017/1/10
 */

public class BackActivityView extends ImageView {

    public BackActivityView(Context context) {
        super(context);
        init();
    }

    public BackActivityView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BackActivityView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 实现方法
     */
    private void init() {
        this.setImageResource(R.drawable.back);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) BackActivityView.this.getContext()).finish();
            }
        });
    }


}
