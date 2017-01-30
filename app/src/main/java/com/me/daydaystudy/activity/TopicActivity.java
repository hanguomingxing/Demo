package com.me.daydaystudy.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.bean.TopicTopData;
import com.me.daydaystudy.fragment.HotTopicFragment;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;
import com.me.daydaystudy.view.PostInfoView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;


public class TopicActivity extends BaseActivity {

    @Bind(R.id.topUpdate)
    CheckBox topUpdate;
    @Bind(R.id.topback_iv)
    ImageView topback_iv;
    @Bind(R.id.topUser_iv)
    ImageView topUser_iv;
    @Bind(R.id.topTitle)
    TextView topTitle;
    @Bind(R.id.topBrief)
    TextView topBrief;
    @Bind(R.id.userCount)
    TextView userCount;
    @Bind(R.id.topPostCount)
    TextView topPostCount;
    @Bind(R.id.topTableLayout)
    TabLayout topTableLayout;
    @Bind(R.id.topViewPager)
    ViewPager topViewPager;
    private TextView titleView;
    private String[] Type = {"最新", "最热"};
    private String nid;
    private int order=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        nid = getIntent().getStringExtra("nid");
        ButterKnife.bind(this);
        initTitle();
        init();
        requestTopData();
    }

    /**
     * 初始化控件的一些操作
     */
    private void init() {
        //tabLayout不显示
        LinearLayout linearLayout = (LinearLayout) topTableLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(25);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
                R.drawable.layout_divider_vertical));

        topTableLayout.setupWithViewPager(topViewPager);
        topViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(topTableLayout));
        topViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                HotTopicFragment hotTopicFragment = new HotTopicFragment();
                Bundle bundle = new Bundle();
                bundle.putString("nid", nid);
                bundle.putString("order", (order++)+"");
                hotTopicFragment.setArguments(bundle);
                return hotTopicFragment;
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return Type[position];
            }
        });
    }


    /**
     * 请求上方数据
     */
    private void requestTopData() {
        Map<String, String> map = new HashMap<>();
        map.put("nid", nid);
        map.put("order", 1 + "");
        map.put("page", 1 + "");
        HttpManger.postMethod(ConstantUtils.CircleTopicTopUrl, map, new MyCallBack() {
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }

            @Override
            public void onResponse(String response) {
                TopicTopData topicTopData = new Gson().fromJson(response, TopicTopData.class);
                TopicTopData.DataBean data = topicTopData.getData();
                if (data != null) {
                    setTopData(data);
                }
            }


        });

    }

    /**
     * 添加数据
     *
     * @param data
     */
    private void setTopData(TopicTopData.DataBean data) {
        //背景
        Glide.with(TopicActivity.this).load(data.getN_big_img()).animate(R.anim.load_image).placeholder(R.drawable.default_one).error(R.drawable.default_one).into(topback_iv);
        //用户头像
        Glide.with(TopicActivity.this).load(data.getN_small_img()).animate(R.anim.load_image).placeholder(R.drawable.default_three).error(R.drawable.default_three).into(topUser_iv);
        //标题
        topTitle.setText(data.getN_title());
        topBrief.setText(data.getN_brief());
        userCount.setText(data.getN_user_count());
        topPostCount.setText(data.getN_post_count());
    }

    /**
     * /**
     * 初始化title
     */
    private void initTitle() {
        titleView = (TextView) findViewById(R.id.title_text);
        titleView.setText(getIntent().getStringExtra("title"));
        findViewById(R.id.title_back).setVisibility(View.VISIBLE);
        titleView.setVisibility(View.VISIBLE);
        PostInfoView title_postInfo = (PostInfoView) findViewById(R.id.title_postInfo);
        title_postInfo.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
