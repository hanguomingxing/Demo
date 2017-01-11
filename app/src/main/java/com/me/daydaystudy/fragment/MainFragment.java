package com.me.daydaystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.daydaystudy.base.BaseFragment;

/**
 * @author :   郗琛
 * @date :   2017/1/10
 */

public class MainFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*new Thread() {
            @Override
            public void run() {
                requestShowNormalView(new View(getActivity()));
                requestChangeViewStatus(EMPTY_VIEW);
            }
        }.start();*/
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
