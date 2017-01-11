package com.me.daydaystudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.me.daydaystudy.R;

/**
 * 分享链接
 *
 * @author :   郗琛
 * @date :   2017/1/10
 */

public class TransmitView extends ImageView {

    public TransmitView(Context context) {
        super(context);
        init();
    }

    public TransmitView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TransmitView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 实现方法
     */
    private void init() {
        this.setImageResource(R.drawable.associated_course);
    }

}
