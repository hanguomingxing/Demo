package com.me.daydaystudy.factory;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.me.daydaystudy.fragment.AttentionFragment;
import com.me.daydaystudy.fragment.HotFragment;
import com.me.daydaystudy.fragment.TopicFragment;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/11.
 */

public class CircleyFragmentFactory {

    public static Fragment getFragmentInstance(int position) {
        Fragment fragment = null;
        Bundle bundle = null;
        switch (position) {
            case 0:
                fragment = new TopicFragment();
                break;
            case 1:
                fragment = new HotFragment();
                break;
            case 2:
                fragment = new AttentionFragment();
                break;
        }
        return fragment;
    }
}
