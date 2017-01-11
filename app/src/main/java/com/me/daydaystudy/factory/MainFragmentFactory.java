package com.me.daydaystudy.factory;

import android.support.v4.app.Fragment;

import com.me.daydaystudy.fragment.CategoryFragment;
import com.me.daydaystudy.fragment.FriendCircleFragment;
import com.me.daydaystudy.fragment.MainFragment;
import com.me.daydaystudy.fragment.MineFragment;

import static android.os.Build.VERSION_CODES.M;

/**
 * @author :   郗琛
 * @date :   2017/1/10
 */

public class MainFragmentFactory {
    public static Fragment getFragmentInstance(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MainFragment();
                break;
            case 1:
                fragment = new CategoryFragment();
                break;
            case 2:
                fragment = new FriendCircleFragment();
                break;
            case 3:
                fragment = new MineFragment();
                break;
            default:
                fragment = new Fragment();
                break;
        }
        return fragment;
    }
}
