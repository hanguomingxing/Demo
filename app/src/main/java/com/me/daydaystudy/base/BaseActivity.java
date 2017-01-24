package com.me.daydaystudy.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.me.daydaystudy.R;
import com.me.daydaystudy.app.MyApplication;
import com.me.daydaystudy.utils.RunnablePermissionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    private Activity mContext;
    /**
     * 存放请求码以及运行代码
     */
    private HashMap<Integer, RunnablePermissionUtils> permissionMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置背景
        getWindow().setBackgroundDrawableResource(R.color.common_background_color);
        //将自己添加到activity集合中
        MyApplication.addActivityList(this);
        init();
    }

    /**
     * 初始化操作
     */
    private void init() {
        mContext = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //将自己移除
        MyApplication.removeActivityList(this);
    }

    /**
     * 请求权限
     *
     * @param permissionCode  请求码
     * @param permissions     请求的权限
     * @param successRunnable 运行的动作
     */
    public void requestPermission(Integer permissionCode, String[] permissions, Runnable successRunnable) {
        requestPermission(permissionCode, permissions, successRunnable, null);
    }

    /**
     * 请求权限
     *
     * @param permissionCode
     * @param permissions
     * @param successRunnable
     * @param failRunnable
     */
    public void requestPermission(Integer permissionCode, String[] permissions, Runnable successRunnable, Runnable failRunnable) {
        if (Build.VERSION.SDK_INT >= 23) {      //如果为6.0以上进行权限申请
            //未被用户同意的权限组
            ArrayList<String> prepareRequestPermission = new ArrayList<>();
            for (int i = 0; i < permissions.length; i++) {
                //查询是否授予
                int checkCallPhonePermission = ContextCompat.checkSelfPermission(mContext, permissions[i]);
                if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                    //未授予权限，准备申请权限
                    prepareRequestPermission.add(permissions[i]);
                }
            }
            //判断是否应该申请
            if (prepareRequestPermission.size() == 0) {
                //全部授权，执行动作
                if (successRunnable != null)
                    successRunnable.run();
            } else {
                //有未申请的权限，进行申请
                //数组转化
                String[] strings = prepareRequestPermission.toArray(new String[prepareRequestPermission.size()]);
                //申请权限
                ActivityCompat.requestPermissions(mContext, strings, permissionCode);
                permissionMap.put(permissionCode, new RunnablePermissionUtils(successRunnable, failRunnable));
            }
        } else {
            //无需授权，执行动作
            if (successRunnable != null)
                successRunnable.run();
        }
    }

    /**
     * 请求权限的回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //map转化set
        Set<Integer> entries = permissionMap.keySet();
        for (Integer permissionCode : entries) {
            if (permissionCode == requestCode) {        //有对应的请求
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        // Permission Denied
                        deniedRequest(requestCode);
                        return;
                    }
                }
                // Permission Success
                runRequest(permissionCode);
                return;
            }
        }
        //没有对应的请求

    }

    /**
     * 运行请求的事件
     *
     * @param permissionCode
     */
    private void runRequest(Integer permissionCode) {
        if (permissionMap.get(permissionCode) != null) {
            if (permissionMap.get(permissionCode).getSuccessRunable() != null)
                permissionMap.get(permissionCode).getSuccessRunable().run();
            permissionMap.remove(permissionCode);
        }
    }

    /**
     * 拒绝请求
     */
    private void deniedRequest(Integer permissionCode) {
        if (permissionMap.get(permissionCode) != null) {
            if (permissionMap.get(permissionCode).getFailRunable() != null)
                permissionMap.get(permissionCode).getFailRunable().run();
            permissionMap.remove(permissionCode);
        }
    }

    /**
     * 获取当前activity的上下文
     *
     * @return
     */
    public Activity getActivity() {
        return mContext;
    }

    /**
     * 跳转界面
     *
     * @param c
     */
    public void jumpActivity(Class c) {
        jumpActivity(new Intent(this, c));
    }


    /**
     * 通过意图跳转界面
     *
     * @param intent
     */
    public void jumpActivity(Intent intent) {
        startActivity(intent);
    }


}
