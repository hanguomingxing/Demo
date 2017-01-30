package com.me.daydaystudy.utils;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;

/**
 * @author :   郗琛
 * @date :   2017/1/23
 */

public class TitleHelp {
    private LinearLayout commonTitle;

    public TitleHelp(BaseActivity activity) {
        commonTitle = (LinearLayout) activity.findViewById(R.id.common_title);
    }

    public void setCommonTitle(String title) {
        showBack();
        setTitleName(title);
    }

    /**
     * 显示搜索按钮
     */
    public void showSearch() {
        commonTitle.findViewById(R.id.title_search).setVisibility(View.VISIBLE);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    private void setTitleName(String title) {
        TextView titleView = (TextView) commonTitle.findViewById(R.id.title_text);
        titleView.setVisibility(View.VISIBLE);
        titleView.setText(title);
    }


    /**
     * 显示返回按钮
     */
    private void showBack() {
        commonTitle.findViewById(R.id.title_back).setVisibility(View.VISIBLE);
    }


}
