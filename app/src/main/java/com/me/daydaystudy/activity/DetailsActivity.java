package com.me.daydaystudy.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.bean.DetailsBean;
import com.me.daydaystudy.fragment.CommentsFragment;
import com.me.daydaystudy.fragment.DetailsFragment;
import com.me.daydaystudy.fragment.DirectoryFragment;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;


/**
 * autour: 孙运泰
 * date: 2017/1/13 13:43
 * update: 2017/1/13
 */
public class DetailsActivity extends BaseActivity {

    private static final String TAG = "----))))";
    private TabLayout tabLayout;
    private ViewPager viewp;
    private String[] title = new String[]{"详情", "目录", "评论(0)"};
    private ImageView details_img;
    private TextView details_name;
    private TextView details_price;
    private TextView details_hour;
    private TextView details_commentcount;
    private TextView details_paycount;
    public DetailsBean detailsBean;
    private static String cid;
    private DetailsBean.DataBean databean;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        cid = intent.getStringExtra("cid");
        initView();
        initData();

    }

    private void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("courseid", cid);
        HttpManger.postMethod(ConstantUtils.USER_XiaoBIAN, map, new MyCallBack() {
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }

            @Override
            public void onResponse(String response) {
                initViewi();
                Gson gson = new Gson();
                detailsBean = gson.fromJson(response, DetailsBean.class);
                if (detailsBean.getData() != null) {
                    databean = detailsBean.getData();
                }
                Log.i("ssssssss", "onResponse: " + databean.toString());
                initshuju();
            }
        });
    }

    private void initshuju() {
        Glide.with(getActivity()).load(databean.getCourse_pic()).into(details_img);
        details_name.setText(databean.getCourse_name());
        if (databean.getCourse_price().equals("0.00")) {
            details_price.setText("免费");
        } else {
            details_price.setText(databean.getCourse_price());
        }
        details_hour.setText("总课时:" + databean.getCourse_hour());
        details_commentcount.setText("评分:" + databean.getGoodrate());
        details_paycount.setText(databean.getCourse_paycount() + "人学");


    }

    private void initViewi() {
        details_img = (ImageView) findViewById(R.id.details_img);
        details_name = (TextView) findViewById(R.id.details_name);
        details_price = (TextView) findViewById(R.id.details_price);
        details_hour = (TextView) findViewById(R.id.details_hour);
        details_commentcount = (TextView) findViewById(R.id.details_commentcount);
        details_paycount = (TextView) findViewById(R.id.details_paycount);
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.details_tablayout);
        viewp = (ViewPager) findViewById(R.id.vp);
        addTab();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return title.length;
            }

            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new DetailsFragment();
                        break;
                    case 1:
                        fragment = new DirectoryFragment();
                        break;
                    case 2:
                        fragment = new CommentsFragment();
                        break;
                }
                return fragment;
            }
        });
        viewp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void addTab() {
        tabLayout.addTab(tabLayout.newTab().setText(title[0]), false);
        tabLayout.addTab(tabLayout.newTab().setText(title[1]), 1, true);
        tabLayout.addTab(tabLayout.newTab().setText(title[2]), false);
    }
}
