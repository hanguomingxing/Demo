package com.me.daydaystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.daydaystudy.R;
import com.me.daydaystudy.activity.LoginActivity;
import com.me.daydaystudy.app.MyApplication;
import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.base.BaseFragment;
import com.me.daydaystudy.view.CircleImageView;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;



/**
 * @author :   郗琛
 * @date :   2017/1/10
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.head_image)
    CircleImageView headImage;
    @Bind(R.id.btn_land)
    Button btnLand;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.login)
    AutoLinearLayout login;
    @Bind(R.id.loginEd)
    AutoLinearLayout loginEd;
    @Bind(R.id.myproject)
    AutoLinearLayout myproject;
    @Bind(R.id.collection)
    AutoLinearLayout collection;
    @Bind(R.id.discount)
    AutoLinearLayout discount;
    @Bind(R.id.offline_load)
    AutoLinearLayout offlineLoad;
    @Bind(R.id.mymessage)
    AutoLinearLayout mymessage;
    @Bind(R.id.feedback)
    AutoLinearLayout feedback;
    @Bind(R.id.imageView2)
    ImageView imageView2;
    @Bind(R.id.setting)
    AutoLinearLayout setting;
    @Bind(R.id.activity_mine_fragment)
    AutoLinearLayout activityMineFragment;
    private View viewRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, viewRoot);
        initLoginEd();
        initView();

        return viewRoot;
    }

    /**
     * 初始化控件
     */
    private void initView() {

    }

    /**
     * 设置是否登陆
     */
    private void initLoginEd() {
        if (MyApplication.isLoginEd()) {        //已登录
            loginEd.setVisibility(View.VISIBLE);
            loginEd.setOnClickListener(this);
        } else {        //未登录
            login.setVisibility(View.VISIBLE);
            login.setOnClickListener(this);
            btnLand.setOnClickListener(this);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.head_image, R.id.btn_land, R.id.textView2, R.id.login, R.id.loginEd, R.id.myproject, R.id.collection, R.id.discount, R.id.offline_load, R.id.mymessage, R.id.feedback, R.id.imageView2, R.id.setting, R.id.activity_mine_fragment})
    public void onClick(View view) {
        if (!MyApplication.isLoginEd()) {   //未登陆
            //登陆界面
            ((BaseActivity) getActivity()).jumpActivity(LoginActivity.class);
        }
        switch (view.getId()) {
            case R.id.head_image:
                break;
            case R.id.btn_land:
                break;
            case R.id.textView2:
                break;
            case R.id.login:
                break;
            case R.id.loginEd:
                break;
            case R.id.myproject:
                break;
            case R.id.collection:
                break;
            case R.id.discount:
                break;
            case R.id.offline_load:
                break;
            case R.id.mymessage:
                break;
            case R.id.feedback:
                break;
            case R.id.imageView2:
                break;
            case R.id.setting:
                break;
            case R.id.activity_mine_fragment:
                break;
        }
    }
}
