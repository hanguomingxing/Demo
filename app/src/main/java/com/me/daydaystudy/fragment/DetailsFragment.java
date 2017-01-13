package com.me.daydaystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseFragment;

/**
 * Created by huanhuan on 2017/1/13.
 */

public class DetailsFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.mainfragment, null);
        return view;
    }
}
