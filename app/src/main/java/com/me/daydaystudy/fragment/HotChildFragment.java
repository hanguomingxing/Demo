package com.me.daydaystudy.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseFragment;
import com.me.daydaystudy.bean.HotContentData;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;
import com.me.daydaystudy.utils.DividerItemDecoration;
import com.me.daydaystudy.view.ShowDialog;
import com.melnykov.fab.FloatingActionButton;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/12.
 */

/**
 * 热门的十一个fragment
 */
public class HotChildFragment extends BaseFragment {

    private int page = 1;
    ArrayList<HotContentData.DataBean> contentList = new ArrayList<>();
    private RecyclerView hot_child_recyclerView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void initData() {
        super.initData();
        String titleTid = getArguments().getString("titleTid");
        Map<String, String> map = new HashMap<String, String>();
        map.put("tid", titleTid);
        map.put("page", page + "");
        HttpManger.postMethod(ConstantUtils.CircleHotContentUrl, map, new MyCallBack() {

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                initView();
                HotContentData hotData = new Gson().fromJson(response, HotContentData.class);
                List<HotContentData.DataBean> data = hotData.getData();
                if (data != null && data.size() > 0) {
                    contentList.addAll(data);
                }
                addContentData();

            }
        });


    }

    /**
     * 展示数据
     */
    private void addContentData() {

        hot_child_recyclerView.setAdapter(new CommonAdapter<HotContentData.DataBean>(getActivity(), R.layout.hot_recycler_item, contentList) {

            private String[] imageData;

            @Override
            protected void convert(ViewHolder holder, HotContentData.DataBean dataBean, int position) {
                View xian = holder.getView(R.id.xian);

                if (position == 0) {
                    xian.setVisibility(View.GONE);
                }
                //第三方分享
                holder.getView(R.id.hot_share_iv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new ShowDialog().showBottomSheetDialog(getContext());
                    }
                });
                //用户头像
                ImageView hot_userIcon = holder.getView(R.id.hot_userIcon);
                Glide.with(HotChildFragment.this).load(dataBean.getUser_small_log()).into(hot_userIcon);

                //用户名称
                holder.setText(R.id.hot_userName, dataBean.getUser_name());
                //事物描述
                holder.setText(R.id.hot_pTitle, dataBean.getP_title());
                //小标题
                TextView hot_pLeaderette = holder.getView(R.id.hot_pLeaderette);
                if (TextUtils.isEmpty(dataBean.getP_leaderette())) {
                    hot_pLeaderette.setVisibility(View.GONE);
                } else {
                    holder.setText(R.id.hot_pLeaderette, dataBean.getP_leaderette());
                }
                //喜欢人数
                holder.setText(R.id.hot_likeCount, dataBean.getP_iscommend());
                holder.setText(R.id.hot_shareCount, dataBean.getP_sharecount());
                holder.setText(R.id.hot_messageCount, dataBean.getP_replycount());
                //类型标签
                TextView hot_type = holder.getView(R.id.hot_type);
                Spanned spanned = Html.fromHtml(dataBean.getP_tids());
                hot_type.setText(spanned);
                ImageView hot_first_iv = holder.getView(R.id.hot_first_iv);
                ImageView hot_two_iv1 = holder.getView(R.id.hot_two_iv1);
                ImageView hot_two_iv2 = holder.getView(R.id.hot_two_iv2);
                ImageView hot_iv1 = holder.getView(R.id.hot_iv1);
                ImageView hot_iv2 = holder.getView(R.id.hot_iv2);
                ImageView hot_iv3 = holder.getView(R.id.hot_iv3);
                AutoLinearLayout view = holder.getView(R.id.hot_llt_three);
                //图片类型 解析
                if (!TextUtils.isEmpty(dataBean.getSource())) {
                    imageData = new Gson().fromJson(dataBean.getSource(), String[].class);
                    if (imageData.length > 0) {
                        view.setVisibility(View.VISIBLE);
                        if (imageData.length == 1) {
                            hot_first_iv.setVisibility(View.VISIBLE);
                            Glide.with(HotChildFragment.this).load(imageData[0]).animate(R.anim.load_image).placeholder(R.drawable.default_one).error(R.drawable.default_one).into(hot_first_iv);
                        } else {
                            hot_first_iv.setVisibility(View.GONE);
                        }
                        if (imageData.length == 2) {
                            Glide.with(HotChildFragment.this).load(imageData[0]).animate(R.anim.load_image).placeholder(R.drawable.default_two).error(R.drawable.default_two).into(hot_two_iv1);
                            Glide.with(HotChildFragment.this).load(imageData[1]).animate(R.anim.load_image).placeholder(R.drawable.default_two).error(R.drawable.default_two).into(hot_two_iv2);

                            hot_two_iv1.setVisibility(View.VISIBLE);
                            hot_two_iv2.setVisibility(View.VISIBLE);
                        } else {
                            hot_two_iv1.setVisibility(View.GONE);
                            hot_two_iv2.setVisibility(View.GONE);
                        }
                        if (imageData.length >= 3) {
                            Glide.with(HotChildFragment.this).load(imageData[0]).animate(R.anim.load_image).placeholder(R.drawable.default_three).error(R.drawable.default_three).into(hot_iv1);
                            Glide.with(HotChildFragment.this).load(imageData[1]).animate(R.anim.load_image).placeholder(R.drawable.default_three).error(R.drawable.default_three).into(hot_iv2);
                            Glide.with(HotChildFragment.this).load(imageData[2]).animate(R.anim.load_image).placeholder(R.drawable.default_three).error(R.drawable.default_three).into(hot_iv3);
                            hot_iv1.setVisibility(View.VISIBLE);
                            hot_iv2.setVisibility(View.VISIBLE);
                            hot_iv3.setVisibility(View.VISIBLE);
                        } else {
                            hot_iv1.setVisibility(View.GONE);
                            hot_iv2.setVisibility(View.GONE);
                            hot_iv3.setVisibility(View.GONE);
                        }
                    } else {
                        view.setVisibility(View.GONE);
                    }
                }
            }
        });

    }

    /**
     * 初始化控件
     */
    private void initView() {
        View view = View.inflate(getActivity(), R.layout.hot_child_fragmnet, null);
        hot_child_recyclerView = (RecyclerView) view.findViewById(R.id.hot_Child_RecyclerView);
        hot_child_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        hot_child_recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        ////设置recyclerView滑动监听
        hot_child_recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //通过判断滑动的长度让floatingActionButton显示隐藏
                if (dy > 0) {
                    floatingActionButton.hide();
                }
                if (dy < 0) {
                    floatingActionButton.show();
                }
            }
        });
        requestShowNormalView(view);
    }


}
