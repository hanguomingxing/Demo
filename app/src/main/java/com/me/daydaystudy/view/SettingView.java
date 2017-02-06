package com.me.daydaystudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.me.daydaystudy.R;

/**
 *设置
 * @date :   2017/1/10
 */

public class SettingView extends ImageView {

    public SettingView(Context context) {
        super(context);
        init();
    }

    public SettingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SettingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 实现方法
     */
    private void init() {
        this.setImageResource(R.drawable.toreport);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "0", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
