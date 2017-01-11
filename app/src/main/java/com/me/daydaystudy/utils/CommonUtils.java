package com.me.daydaystudy.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.View;

import com.me.daydaystudy.app.MyApplication;


/**
 * 普通的工具类
 */
public class CommonUtils {
    private static final String TAG = "CommonUtils";

    public static Context getContext() {
        return MyApplication.getContext();
    }

    // 获取资源文件夹操作
    public static Resources getResources() {
        return getContext().getResources();
    }

    // dip--->px, 1dp = 2px,定义一个控件的宽高 layoutParams(w,h)
    public static int dip2px(int dip) {
        // 获取dp和px的转换关系的变量
        float density = getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5);
    }

    // px---->dp
    public static int px2dip(int px) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    public static View inflate(int layoutId) {
        return View.inflate(getContext(), layoutId, null);
    }

    // 将dimens中的dp转换成像素值
    public static int getDimens(int dimensId) {
        return getResources().getDimensionPixelSize(dimensId);
    }

    /**
     * 获取Color中的属性
     *
     * @param colorId
     * @return
     */
    public static int getResourseColor(int colorId) {
        int color = MyApplication.getContext().getResources().getColor(colorId);
        return color;
    }

}
