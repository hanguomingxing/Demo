package com.me.daydaystudy.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.me.daydaystudy.R;
import com.me.daydaystudy.utils.NetUtils;

import java.util.HashMap;
import java.util.Set;

/**
 * @author :   郗琛
 * @date :   2017/1/11
 */

public class BaseFragment extends Fragment {
    private HashMap<Integer, View> viewMap = new HashMap<>();

    public static final int LOADING_VIEW = 0;
    public static final int NORMAL_VIEW = 1;
    public static final int NO_NET_VIEW = 2;
    public static final int NO_LOGIN_VIEW = 3;
    public static final int EMPTY_VIEW = 4;
    public static final int ERROR_VIEW = 5;
    protected FrameLayout frameLayout;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        frameLayout = new FrameLayout(getActivity());
        //显示默认的视图
        requestChangeViewStatus(LOADING_VIEW);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //默认显示正在加载界面
        return frameLayout;
    }

    /**
     * 加载数据
     */
    protected void init() {
        if (NetUtils.isHaveNet()) {
            //设置正在加载
            requestChangeViewStatus(LOADING_VIEW);
        } else {
            //设置没有网络
            requestChangeViewStatus(NO_NET_VIEW);
        }
    }

    /**
     * 输入显示视图的类型
     *
     * @param viewType
     */
    protected final void requestChangeViewStatus(int viewType) {
        Set<Integer> keySet = viewMap.keySet();
        //隐藏所有显示的视图
        for (Integer viewKey : keySet) {
            viewMap.get(viewKey).setVisibility(View.GONE);
        }
        //显示选中的视图
        getViewForMap(viewType).setVisibility(View.VISIBLE);
    }

    /**
     * 请求展示自己的视图
     *
     * @param view
     */
    protected final void requestShowNormalView(View view) {
        viewMap.put(NORMAL_VIEW, view);
        frameLayout.addView(view);
        requestChangeViewStatus(NORMAL_VIEW);
    }

    /**
     * 得到类型对应的视图
     *
     * @param viewType
     * @return
     */
    private View getViewForMap(int viewType) {
        View view = viewMap.get(viewType);
        if (view != null)       //如果有则返回
            return view;
        switch (viewType) {     //没有则创建
            case LOADING_VIEW:
                view = View.inflate(getActivity(), R.layout.base_loading_fragment, null);
                break;
            case ERROR_VIEW:
                view = View.inflate(getActivity(), R.layout.base_error_fragment, null);
                requestRefresh(view);
                break;
            case NO_NET_VIEW:
                view = View.inflate(getActivity(), R.layout.base_no_net_fragment, null);
                requestRefresh(view);
                break;
            case NO_LOGIN_VIEW:
                view = View.inflate(getActivity(), R.layout.base_no_login_fragment, null);
                break;
            case EMPTY_VIEW:
                view = View.inflate(getActivity(), R.layout.base_empty_fragment, null);
                requestRefresh(view);
                break;
            case NORMAL_VIEW:
            default:
                throw new RuntimeException("no select view");
        }
        //保存到集合并且返回
        viewMap.put(viewType, view);
        frameLayout.addView(view);
        return view;
    }

    private void requestRefresh(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
    }

}
