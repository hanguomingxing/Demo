package com.me.daydaystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseFragment;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/11.
 */

/**
 * 关注
 */
public class AttentionFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.attention_fragment_layout, null);

        return super.onCreateView(inflater, container, savedInstanceState);

    }
}