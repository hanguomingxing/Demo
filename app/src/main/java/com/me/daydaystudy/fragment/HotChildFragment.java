package com.me.daydaystudy.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.me.daydaystudy.R;
import com.me.daydaystudy.adapter.HotContentAdapter;
import com.me.daydaystudy.base.BaseFragment;
import com.me.daydaystudy.bean.HotContentData;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;
import com.me.daydaystudy.utils.DividerItemDecoration;
import com.me.daydaystudy.view.MyHeader;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/12.
 * 热门的十一个fragment
 */

public class HotChildFragment extends BaseFragment implements SpringView.OnFreshListener {

    private int page = 0;
    ArrayList<HotContentData.DataBean> contentList = new ArrayList<>();
    private RecyclerView hot_child_recyclerView;
    private FloatingActionButton floatingActionButton;
    private SpringView hotSpringView;
    private String titleTid;
    private Map<String, String> map;
    private HotContentAdapter hotContentAdapter;
    private View view;

    @Override
    protected void init() {
        super.init();
        initView();
        hotContentAdapter = new HotContentAdapter(getActivity(), R.layout.hot_recycler_item, contentList);
        hot_child_recyclerView.setAdapter(hotContentAdapter);
        //初始化请求
        requestContentData(page);
    }

    /**
     * 获取该对象的实例
     *
     * @param titleTid
     * @return
     */
    public static HotChildFragment getHotChildFragmentInstance(String titleTid) {
        HotChildFragment hotChildFragment = new HotChildFragment();
        Bundle bundle = new Bundle();
        bundle.putString("titleTid", titleTid);
        hotChildFragment.setArguments(bundle);
        return hotChildFragment;
    }


    /**
     * 请求数据
     */
    private void requestContentData(final int page) {
        titleTid = getArguments().getString("titleTid");
        map = new HashMap<>();
        map.put("tid", titleTid);
        map.put("page", page + "");
        HttpManger.postMethod(ConstantUtils.CircleHotContentUrl, map, new MyCallBack() {

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                requestChangeViewStatus(ERROR_VIEW);
            }

            @Override
            public void onResponse(String response) {
                //解析数据
                HotContentData hotData = new Gson().fromJson(response, HotContentData.class);
                //获取需要的集合
                List<HotContentData.DataBean> data = hotData.getData();
                if (page == 1) {        //刷新操作
                    contentList.addAll(0, data);
                } else {             //加载操作
                    contentList.addAll(data);
                }
                hotContentAdapter.notifyDataSetChanged();
                if (view.getParent() == null) {
                    requestShowNormalView(view);
                }
            }
        });
    }


    /**
     * 初始化控件
     */
    private void initView() {
        view = View.inflate(getActivity(), R.layout.hot_child_fragmnet, null);
        hotSpringView = (SpringView) view.findViewById(R.id.hotSpringView);
        hotSpringView.setType(SpringView.Type.FOLLOW);
        hotSpringView.setHeader(new MyHeader());
        hotSpringView.setFooter(new DefaultFooter(getActivity()));
        hotSpringView.setListener(this);
        hot_child_recyclerView = (RecyclerView) view.findViewById(R.id.hot_Child_RecyclerView);
        hot_child_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        hot_child_recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        floatingActionButton.attachToRecyclerView(hot_child_recyclerView);

    }


    /**
     * 刷新数据
     */
    @Override
    public void onRefresh() {
        requestContentData(1);
        hotSpringView.onFinishFreshAndLoad();
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadmore() {
        requestContentData(++page);
        hotSpringView.onFinishFreshAndLoad();
    }
}
