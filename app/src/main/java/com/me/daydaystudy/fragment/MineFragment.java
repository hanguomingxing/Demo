package com.me.daydaystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.me.daydaystudy.R;
import com.me.daydaystudy.activity.CollectionActivity;
import com.me.daydaystudy.activity.DisCountActivity;
import com.me.daydaystudy.activity.FeedBackActivity;
import com.me.daydaystudy.activity.LoginActivity;
import com.me.daydaystudy.activity.MyMessageActivity;
import com.me.daydaystudy.activity.MyProjectActivity;
import com.me.daydaystudy.activity.SettingActivity;
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
    @Bind(R.id.loginMore)
    ImageView loginMore;
    private View viewRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_mine, null);
        initView();
        return viewRoot;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        ButterKnife.bind(this, viewRoot);
        initLoginEd();
    }

    /**
     * 设置是否登陆
     */
    private void initLoginEd() {
        if (MyApplication.isLoginEd()) {        //已登录
            login.setVisibility(View.GONE);
            loginEd.setVisibility(View.VISIBLE);
            loginEd.setOnClickListener(this);
        } else {        //未登录
            login.setVisibility(View.VISIBLE);
            loginEd.setVisibility(View.GONE);
            login.setOnClickListener(this);
            btnLand.setOnClickListener(this);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        initLoginEd();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.head_image, R.id.btn_land, R.id.login, R.id.loginEd, R.id.myproject, R.id.collection, R.id.discount, R.id.offline_load, R.id.mymessage, R.id.feedback, R.id.imageView2, R.id.setting, R.id.activity_mine_fragment, R.id.loginMore})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
            case R.id.head_image://未登录头像
            case R.id.btn_land:
                jumpLoginActivity();//跳转登陆界面
                break;
            case R.id.loginEd:
            case R.id.loginMore:
            case R.id.imageView2://登录头像
                // TODO: 2017/1/23 跳转个人信息
                break;
            case R.id.myproject:
                if (isLogin()) {
                    ((BaseActivity) getActivity()).jumpActivity(MyProjectActivity.class);
                }
                break;
            case R.id.collection:
                if (isLogin()) {
                    ((BaseActivity) getActivity()).jumpActivity(CollectionActivity.class);
                }
                break;
            case R.id.discount:
                if (isLogin()) {
                    ((BaseActivity) getActivity()).jumpActivity(DisCountActivity.class);
                }
                break;
            case R.id.offline_load:
                if (isLogin()) {
                    Toast.makeText(getActivity(), "offline_load", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.mymessage:
                if (isLogin()) {
                    ((BaseActivity) getActivity()).jumpActivity(MyMessageActivity.class);
                }
                break;
            case R.id.feedback:
                //意见反馈
                ((BaseActivity) getActivity()).jumpActivity(FeedBackActivity.class);
                break;
            case R.id.setting:
                ((BaseActivity) getActivity()).jumpActivity(SettingActivity.class);
                break;
        }
    }

    /**
     * 是否已经登陆
     *
     * @return
     */
    private boolean isLogin() {
        if (!MyApplication.isLoginEd()) {   //未登陆
            //登陆界面
            jumpLoginActivity();
            return false;
        } else {
            return true;
        }
    }

    /**
     * 跳转登陆界面
     */
    private void jumpLoginActivity() {
        ((BaseActivity) getActivity()).jumpActivity(LoginActivity.class);
    }

    /**
     * 跳转个人信息界面
     */
    private void jumpSingleInfoActivity() {
        ((BaseActivity) getActivity()).jumpActivity(LoginActivity.class);
    }


}
