package com.me.daydaystudy.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;


/**
 * @author :   郗琛
 * @date :   2017/1/24
 */

public class ViewUtils {
    public static <T extends View> T inflate(Context context, int id) {
        View inflate = LayoutInflater.from(context).inflate(id, null);
        return (T) inflate;
    }

}
