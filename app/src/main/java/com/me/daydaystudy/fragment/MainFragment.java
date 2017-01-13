package com.me.daydaystudy.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.daydaystudy.R;
import com.me.daydaystudy.activity.DetailsActivity;
import com.me.daydaystudy.base.BaseFragment;
import com.me.daydaystudy.bean.MainBean;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.GlideImageLoader;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static com.me.daydaystudy.R.id.main_recommended_price;

/**
 * @author :   郗琛
 * @date :   2017/1/10
 */

public class MainFragment extends BaseFragment {
    private static final String TAG = "---------";
    private MainBean mainBean;
    public List<MainBean.DataBean.SliderBean> sliderBeen;
    private ArrayList<Object> imglist;
    private View view;
    private TextView main_strongest_mentality;
    private TextView main_strongest_get;
    private ImageView main_strongest_img;
    public List<MainBean.DataBean.AdlistBean> adlistBeen;
    private TextView main_running_run;
    private TextView main_running_pace;
    private ImageView main_running_img;
    private TextView main_thinking_think;
    private TextView main_thinking_revolution;
    private ImageView main_thinking_img;
    private TextView main_gride_life;
    private ImageView main_gride_imglife;
    private ImageView main_gride_imghobby;
    private TextView main_gride_hobby;
    private ImageView main_gride_imgtworkplace;
    private TextView main_gride_workplace;
    private ImageView main_gride_imgtest;
    private TextView main_gride_test;
    private ImageView main_gride_imgclassification;
    private TextView main_gride_classification;
    private ImageView main_gride_imglanguage;
    private TextView main_gride_language;
    public List<MainBean.DataBean.HotcategoryBean> hotcategoryBeen;
    public List<MainBean.DataBean.HotcourseBean> hotcourseBeen;
    private RecyclerView recy;
    public List<MainBean.DataBean.IndexrecommendBean.TopBean> topBean;
    private ImageView main_recommended_memory;
    private ImageView main_recommended_love;
    private List<MainBean.DataBean.IndexrecommendBean.ListviewBean> listviewBeen;
    private RecyclerView main_recommended_rv;
    private List<MainBean.DataBean.IndexothersBean> indexothersBeen;
    private RecyclerView main_learn_rv;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    @Override
    protected void initData() {
        super.initData();
        HttpManger.getMethod(ConstantUtils.USER_MAIN, new MyCallBack() {


            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                mainBean = gson.fromJson(response, MainBean.class);
                //轮播数据
                sliderBeen = mainBean.getData().getSlider();
                //多彩生活
                hotcategoryBeen = mainBean.getData().getHotcategory();
                //最强思路
                adlistBeen = mainBean.getData().getAdlist();
                //热门课程
                hotcourseBeen = mainBean.getData().getHotcourse();
                //小编推荐
                topBean = mainBean.getData().getIndexrecommend().getTop();
                listviewBeen = mainBean.getData().getIndexrecommend().getListview();
                //大家都在学
                indexothersBeen = mainBean.getData().getIndexothers();
                initView();

            }
        });
    }

    private void initView() {
        view = View.inflate(getActivity(), R.layout.mainfragment, null);
        //轮播图
        initShuffling();
        //多彩生活
        initFile();
        //最强思路
        initStrongest();
        //热门课程
        initHot();
        //小编推荐
        initRecommended();
        //大家都在学
        initLearn();


        //展示请求
        requestShowNormalView(view);
    }

    private void initLearn() {
        main_learn_rv = (RecyclerView) view.findViewById(R.id.main_learn_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        main_learn_rv.setLayoutManager(layoutManager);
        main_learn_rv.setAdapter(new CommonAdapter<MainBean.DataBean.IndexothersBean>(getContext(), R.layout.main_recommended_rv_item, indexothersBeen) {

            @Override
            protected void convert(ViewHolder holder, MainBean.DataBean.IndexothersBean indexothersBean, int position) {
                ImageView main_recommended_img = (ImageView) holder.itemView.findViewById(R.id.main_recommended_img);
                Glide.with(getContext()).load(indexothersBeen.get(position).getCourse_pic()).into(main_recommended_img);
                holder.setText(R.id.main_recommended_course_name, indexothersBeen.get(position).getCourse_name());
                holder.setText(R.id.main_recommended_school_name, indexothersBeen.get(position).getSchool_name());
                String course_price = indexothersBeen.get(position).getCourse_price();
                if (course_price.equals("0.00")) {
                    holder.setText(main_recommended_price, "免费").setTextColor(main_recommended_price, Color.parseColor("#92C549"));
                } else {
                    holder.setText(main_recommended_price, "¥" + indexothersBeen.get(position).getCourse_price()).setTextColor(main_recommended_price, Color.RED);
                }
                holder.setText(R.id.main_recommended_usercount, indexothersBeen.get(position).getUsercount() + "人在学");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getActivity(), DetailsActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void initRecommended() {
        main_recommended_memory = (ImageView) view.findViewById(R.id.main_recommended_memory);
        main_recommended_love = (ImageView) view.findViewById(R.id.main_recommended_love);
        Glide.with(getActivity()).load(topBean.get(0).getCourse_pic()).into(main_recommended_memory);
        Glide.with(getActivity()).load(topBean.get(1).getCourse_pic()).into(main_recommended_love);
        main_recommended_rv = (RecyclerView) view.findViewById(R.id.main_recommended_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        main_recommended_rv.setLayoutManager(layoutManager);
        main_recommended_rv.setAdapter(new CommonAdapter<MainBean.DataBean.IndexrecommendBean.ListviewBean>(getContext(), R.layout.main_recommended_rv_item, listviewBeen) {

            @Override
            protected void convert(ViewHolder holder, MainBean.DataBean.IndexrecommendBean.ListviewBean listviewBean, int position) {
                ImageView main_recommended_img = (ImageView) holder.itemView.findViewById(R.id.main_recommended_img);
                Glide.with(getContext()).load(listviewBeen.get(position).getCourse_pic()).into(main_recommended_img);
                holder.setText(R.id.main_recommended_course_name, listviewBeen.get(position).getCourse_name());
                holder.setText(R.id.main_recommended_school_name, listviewBeen.get(position).getSchool_name());
                String course_price = listviewBeen.get(position).getCourse_price();
                if (course_price.equals("0.00")) {
                    holder.setText(main_recommended_price, "免费").setTextColor(main_recommended_price, Color.parseColor("#92C549"));
                } else {
                    holder.setText(main_recommended_price, "¥" + listviewBeen.get(position).getCourse_price()).setTextColor(main_recommended_price, Color.RED);
                }
                holder.setText(R.id.main_recommended_usercount, listviewBeen.get(position).getUsercount() + "人在学");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "我是Recycler", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    private void initFile() {
        //多彩生活
        main_gride_imglife = (ImageView) view.findViewById(R.id.main_gride_imglife);
        main_gride_life = (TextView) view.findViewById(R.id.main_gride_life);
        //兴趣爱好
        main_gride_imghobby = (ImageView) view.findViewById(R.id.main_gride_imghobby);
        main_gride_hobby = (TextView) view.findViewById(R.id.main_gride_hobby);
        //职场提升
        main_gride_imgtworkplace = (ImageView) view.findViewById(R.id.main_gride_imgtworkplace);
        main_gride_workplace = (TextView) view.findViewById(R.id.main_gride_workplace);
        //考试考级
        main_gride_imgtest = (ImageView) view.findViewById(R.id.main_gride_imgtest);
        main_gride_test = (TextView) view.findViewById(R.id.main_gride_test);
        //语言学习
        main_gride_imglanguage = (ImageView) view.findViewById(R.id.main_gride_imglanguage);
        main_gride_language = (TextView) view.findViewById(R.id.main_gride_language);
        //全部分类
        main_gride_imgclassification = (ImageView) view.findViewById(R.id.main_gride_imgclassification);
        main_gride_classification = (TextView) view.findViewById(R.id.main_gride_classification);
//----------------------------------------塞数据------------------------------------------------
        Glide.with(getActivity()).load(hotcategoryBeen.get(0).getImg()).into(main_gride_imglife);
        main_gride_life.setText(hotcategoryBeen.get(0).getCname());

        Glide.with(getActivity()).load(hotcategoryBeen.get(1).getImg()).into(main_gride_imghobby);
        main_gride_hobby.setText(hotcategoryBeen.get(1).getCname());

        Glide.with(getActivity()).load(hotcategoryBeen.get(2).getImg()).into(main_gride_imgtworkplace);
        main_gride_workplace.setText(hotcategoryBeen.get(2).getCname());

        Glide.with(getActivity()).load(hotcategoryBeen.get(3).getImg()).into(main_gride_imgtest);
        main_gride_test.setText(hotcategoryBeen.get(3).getCname());

        Glide.with(getActivity()).load(hotcategoryBeen.get(4).getImg()).into(main_gride_imglanguage);
        main_gride_language.setText(hotcategoryBeen.get(4).getCname());

        Glide.with(getActivity()).load(hotcategoryBeen.get(5).getImg()).into(main_gride_imgclassification);
        main_gride_classification.setText(hotcategoryBeen.get(5).getCname());

    }

    private void initHot() {
        recy = (RecyclerView) view.findViewById(R.id.main_hot_rv);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recy.setLayoutManager(layoutManager);
        recy.setAdapter(new CommonAdapter<MainBean.DataBean.HotcourseBean>(getActivity(), R.layout.main_hot_vp_item, hotcourseBeen) {
            @Override
            protected void convert(ViewHolder holder, MainBean.DataBean.HotcourseBean hotcourseBean, int position) {
                ImageView main_hot_rv_img = holder.getView(R.id.main_hot_rv_img);
                Glide.with(getContext()).load(hotcourseBeen.get(position).getImg()).into(main_hot_rv_img);
                holder.setText(R.id.main_hot_rv_title, hotcourseBeen.get(position).getTitle());
                holder.setText(R.id.main_hot_rv_name, hotcourseBeen.get(position).getName());
            }
        });

    }

    private void initStrongest() {
        main_strongest_mentality = (TextView) view.findViewById(R.id.main_strongest_mentality);
        main_strongest_get = (TextView) view.findViewById(R.id.main_strongest_get);
        main_strongest_img = (ImageView) view.findViewById(R.id.main_strongest_img);

        main_running_run = (TextView) view.findViewById(R.id.main_running_run);
        main_running_pace = (TextView) view.findViewById(R.id.main_running_pace);
        main_running_img = (ImageView) view.findViewById(R.id.main_running_img);

        main_thinking_think = (TextView) view.findViewById(R.id.main_thinking_think);
        main_thinking_revolution = (TextView) view.findViewById(R.id.main_thinking_revolution);
        main_thinking_img = (ImageView) view.findViewById(R.id.main_thinking_img);

        main_strongest_mentality.setText(adlistBeen.get(0).getName());
        main_strongest_get.setText(adlistBeen.get(0).getTitle());
        Glide.with(getActivity()).load(adlistBeen.get(0).getImg()).into(main_strongest_img);

        main_running_run.setText(adlistBeen.get(1).getName());
        main_running_pace.setText(adlistBeen.get(1).getTitle());
        Glide.with(getActivity()).load(adlistBeen.get(1).getImg()).into(main_running_img);

        main_thinking_think.setText(adlistBeen.get(2).getName());
        main_thinking_revolution.setText(adlistBeen.get(2).getTitle());
        Glide.with(getActivity()).load(adlistBeen.get(2).getImg()).into(main_thinking_img);
    }

    private void initShuffling() {
        final Banner banner = (Banner) view.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //banner设置方法全部调用完毕时最后调用
        imglist = new ArrayList<>();
        for (int i = 0; i < sliderBeen.size(); i++) {
            imglist.add(sliderBeen.get(i).getImg());
        }
        Log.i(TAG, "--------" + sliderBeen.size());
        banner.setImages(imglist);
        banner.start();
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getContext(), "被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
