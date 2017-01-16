package com.me.daydaystudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.me.daydaystudy.R;

/**
 * 分享
 *
 * @author :   郗琛
 * @date :   2017/1/10
 */

public class ShareView extends ImageView {

    public ShareView(Context context) {
        super(context);
        init();
    }

    public ShareView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShareView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 实现方法
     */
    private void init() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new ShowDialog(getContext());
            }
        });
        this.setImageResource(R.drawable.share);
    }

}
