package com.me.daydaystudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * @author : 张鸿鹏
 * @date : 2017/2/4.
 */

public class CollectView extends CheckBox {

    public CollectView(Context context) {
        super(context);
        init();
    }


    public CollectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public CollectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {


        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isChecked()) {
                    Toast.makeText(getContext(), "收藏", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "取消", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
