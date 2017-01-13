package com.me.daydaystudy.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
                Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
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
                //用户头像
                ImageView hot_userIcon = holder.getView(R.id.hot_userIcon);
                setImage(dataBean.getUser_small_log(), hot_userIcon);
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
             /*   Spanned spanned = Html.fromHtml(dataBean.getP_tids());
                hot_type.setText();*/

                //图片类型 解析
                if (!TextUtils.isEmpty(dataBean.getSource())) {
                    imageData = new Gson().fromJson(dataBean.getSource(), String[].class);
                    //大图布局
                    AutoLinearLayout hot_llt_first = holder.getView(R.id.hot_llt_first);
                    //两张图
                    AutoLinearLayout hot_llt_two = holder.getView(R.id.hot_llt_two);
                    //三张图以上
                    AutoLinearLayout hot_llt_three = holder.getView(R.id.hot_llt_three);
                    if (imageData.length > 0) {
                        if (imageData.length == 1) {
                            hot_llt_first.setVisibility(View.VISIBLE);
                            ImageView hot_first_iv = holder.getView(R.id.hot_first_iv);
                            setImage(imageData[0], hot_first_iv);
                        } else if (imageData.length == 2) {
                            hot_llt_two.setVisibility(View.VISIBLE);
                            ImageView hot_two_iv1 = holder.getView(R.id.hot_two_iv1);
                            ImageView hot_two_iv2 = holder.getView(R.id.hot_two_iv2);
                            Glide.with(HotChildFragment.this).load(imageData[0]).into(hot_two_iv1);
                            Glide.with(HotChildFragment.this).load(imageData[1]).into(hot_two_iv2);
                        } else if (imageData.length >= 3) {
                            hot_llt_three.setVisibility(View.VISIBLE);
                            ImageView hot_iv1 = holder.getView(R.id.hot_iv1);
                            ImageView hot_iv2 = holder.getView(R.id.hot_iv2);
                            ImageView hot_iv3 = holder.getView(R.id.hot_iv3);
                            Glide.with(HotChildFragment.this).load(imageData[0]).into(hot_iv1);
                            Glide.with(HotChildFragment.this).load(imageData[1]).into(hot_iv2);
                            Glide.with(HotChildFragment.this).load(imageData[2]).into(hot_iv3);
                        }
                    }
                }
            }
        });


    }

    public void setImage(String url, ImageView imageView) {
        Glide.with(HotChildFragment.this).load(url).into(imageView);
    }

    private void initView() {

        View view = View.inflate(getActivity(), R.layout.hot_child_fragmnet, null);
        hot_child_recyclerView = (RecyclerView) view.findViewById(R.id.hot_Child_RecyclerView);
        hot_child_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        requestShowNormalView(view);
    }
}
