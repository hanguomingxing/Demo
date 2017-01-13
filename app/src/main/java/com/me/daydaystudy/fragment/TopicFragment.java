package com.me.daydaystudy.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseFragment;
import com.me.daydaystudy.bean.TopicData;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;
import com.me.daydaystudy.utils.DividerItemDecoration;
import com.me.daydaystudy.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/11.
 */

public class TopicFragment extends BaseFragment implements OnBannerClickListener {
    private View inflate;
    private Banner banner;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<TopicData.DataBean.CircleBean> hotList = new ArrayList<>();
    private ArrayList<TopicData.DataBean.CircleBean> myHotList = new ArrayList<>();
    private RecyclerView topic_recyclerView;
    private CommonAdapter<TopicData.DataBean.CircleBean> commonAdapter;
    private RecyclerView mytopic_recyclerView;
    private TextView tvTopic;
    private TextView tvMyTopic;

    /**
     * 请求网络
     */
    @Override
    protected void initData() {
        super.initData();
        HttpManger.getMethod(ConstantUtils.CircleTopicUrl, new MyCallBack() {
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                initView();
                TopicData topicData = new Gson().fromJson(response, TopicData.class);
                List<TopicData.DataBean.BannerBean> bannerList = topicData.getData().getBanner();
                List<TopicData.DataBean.CircleBean> circle = topicData.getData().getCircle();
                List<TopicData.DataBean.MycircleBean> mycircle = topicData.getData().getMycircle();
                //轮播图数据
                if (bannerList != null && bannerList.size() > 0) {
                    for (int i = 0; i < bannerList.size(); i++) {
                        list.add(bannerList.get(i).getImg());
                    }
                    addRoolViewPager();
                }
                //热门圈子
                if (circle != null && circle.size() > 0) {
                    show(tvTopic, topic_recyclerView);
                    hotList.clear();
                    hotList.addAll(circle);
                    setHotCircleData();
                } else {
                    hide(tvTopic, topic_recyclerView);
                }
                //我的圈子
                if (mycircle != null && mycircle.size() > 0) {
                    show(tvMyTopic, mytopic_recyclerView);
                    myHotList.clear();
                    myHotList.addAll(circle);
                } else {
                    hide(tvMyTopic, mytopic_recyclerView);
                }

            }


        });

    }

    public void show(TextView textView, RecyclerView recyclerView) {
        textView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    public void hide(TextView textView, RecyclerView recyclerView) {
        textView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        inflate = View.inflate(getActivity(), R.layout.topic_fragment_layout, null);
        //显示正常视图
        banner = (Banner) inflate.findViewById(R.id.banner);
        tvTopic = (TextView) inflate.findViewById(R.id.tvTopic);
        topic_recyclerView = (RecyclerView) inflate.findViewById(R.id.topic_recyclerView);
        tvMyTopic = (TextView) inflate.findViewById(R.id.tvMyTopic);
        mytopic_recyclerView = (RecyclerView) inflate.findViewById(R.id.myTopic_recyclerView);
        requestShowNormalView(inflate);
    }

    /**
     * 热门圈子数据
     */
    private void setHotCircleData() {

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        topic_recyclerView.setLayoutManager(linearLayoutManager);
        topic_recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), RecyclerView.VERTICAL));
        commonAdapter = new CommonAdapter<TopicData.DataBean.CircleBean>(getActivity(), R.layout.hotcircle_recycleritem_layout, hotList) {
            @Override
            protected void convert(final ViewHolder holder, TopicData.DataBean.CircleBean circleBean, final int position) {
                holder.setText(R.id.topic_Title, circleBean.getN_title());
                holder.setText(R.id.topic_Brief, circleBean.getN_brief());
                holder.setText(R.id.topic_userCount, circleBean.getN_user_count() + "人关注");
                holder.setText(R.id.topic_postCount, circleBean.getN_post_count() + "帖子");
                ImageView topic_iv = holder.getView(R.id.topic_iv);
                Glide.with(getActivity()).load(circleBean.getN_small_img()).into(topic_iv);

                ImageView addMyTopic = holder.getView(R.id.addMyTopic);
                addMyTopic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int adapterPosition = holder.getAdapterPosition();
                        //移除
                        hotList.remove(adapterPosition);
                        commonAdapter.notifyItemRemoved(adapterPosition);

                    }
                });
            }
        };
        topic_recyclerView.setAdapter(commonAdapter);
    }

    /**
     * 无线轮播
     */
    private void addRoolViewPager() {
        banner.setImages(list);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.start();
        //banner设置方法全部调用完毕时最后调用
        banner.setOnBannerClickListener(TopicFragment.this);
        banner.setBannerAnimation(Transformer.Accordion);
    }


    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getActivity(), "老子跳了" + position, Toast.LENGTH_SHORT).show();
    }
}
