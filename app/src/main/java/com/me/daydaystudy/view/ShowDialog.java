package com.me.daydaystudy.view;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;

import com.me.daydaystudy.R;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/15.
 */

public class ShowDialog {
    /**
     * 分享的dialog
     */
    public void showBottomSheetDialog(Context context) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        View inflate = View.inflate(context, R.layout.share_dialog_item, null);
        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.show();
    }
}
