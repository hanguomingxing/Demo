package com.me.daydaystudy.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import org.xutils.x;

import java.util.ArrayList;

/**
 * @author :   郗琛
 * @date :   2017/1/10
 */

public class MyApplication extends Application {

    private static ArrayList<Activity> activityList = new ArrayList();
    private static Context mContext = null;
    private static boolean isLogined = false;

    /**
     * 查询是否登陆
     *
     * @return
     */
    public static boolean isLogined() {
        return isLogined;
    }

    /**
     * 设置是否登陆
     *
     * @param isLogined
     */
    public static void setIsLogined(boolean isLogined) {
        MyApplication.isLogined = isLogined;
    }

    /**
     * 创建添加
     *
     * @param activity
     */
    public static void addActivityList(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 退出移除
     *
     * @param activity
     */
    public static void removeActivityList(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 退出应用
     *
     * @param activity
     */
    public static void outApplication(Activity activity) {
        for (int i = 0; i < activityList.size(); i++) {
            activityList.get(i).finish();
        }
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        x.Ext.init(this);
    }

}
