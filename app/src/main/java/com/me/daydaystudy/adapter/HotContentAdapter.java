package com.me.daydaystudy.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.daydaystudy.R;
import com.me.daydaystudy.activity.HotDetailsActivity;
import com.me.daydaystudy.bean.HotContentData;
import com.me.daydaystudy.bean.SpannedBean;
import com.me.daydaystudy.view.ShowDialog;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/16.
 */

public class HotContentAdapter extends CommonAdapter<HotContentData.DataBean> {

    private final Context context;
    private final int layoutId;
    private final List<HotContentData.DataBean> datas;
    private String[] imageData;
    private TextView textView;
    private LinearLayout.LayoutParams layoutParams;
    private AutoLinearLayout type_llt;

    public HotContentAdapter(Context context, int layoutId, List<HotContentData.DataBean> datas) {
        super(context, layoutId, datas);
        this.context = context;
        this.layoutId = layoutId;
        this.datas = datas;
    }


    @Override
    protected void convert(ViewHolder holder, final HotContentData.DataBean dataBean, final int position) {
        //跳转
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HotDetailsActivity.class);
                intent.putExtra("pid", datas.get(position).getPid());
                intent.putExtra("userName", datas.get(position).getUser_name());
                intent.putExtra("head", datas.get(position).getUser_small_log());
                intent.putExtra("time", datas.get(position).getP_time());
                context.startActivity(intent);
            }
        });


        View xian = holder.getView(R.id.xian);

        if (position == 0) {
            xian.setVisibility(View.GONE);
        }
        //第三方分享
        holder.getView(R.id.hot_share_llt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ShowDialog(context, dataBean.getP_title());
            }
        });
        final CheckBox hot_like_iv = holder.getView(R.id.hot_like_iv);
        holder.getView(R.id.guanzhu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hot_like_iv.setBackgroundResource(R.drawable.like_selector);
            }
        });
        //用户头像
        ImageView hot_userIcon = holder.getView(R.id.hot_userIcon);
        Glide.with(context).load(dataBean.getUser_small_log()).into(hot_userIcon);

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
        type_llt = holder.getView(R.id.type_llt);
        if (!TextUtils.isEmpty(dataBean.getP_tids())) {
            Spanned spanned = Html.fromHtml(dataBean.getP_tids());
            final SpannedBean[] spannedBean = new Gson().fromJson(spanned.toString(), SpannedBean[].class);
            type_llt.removeAllViews();

            for (int i = 0; i < spannedBean.length; i++) {
                textView = new TextView(context);
                textView.setText("#" + spannedBean[i].getTname() + "#");
                textView.setTextColor(Color.parseColor("#F74D40"));
                final int finalI = i;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, spannedBean[finalI].getTname() + "———" + finalI, Toast.LENGTH_SHORT).show();
                    }
                });
                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(10, 0, 10, 0);
            }
            if (textView != null) {
                type_llt.addView(textView, layoutParams);
            }
        }

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
                    Glide.with(context).load(imageData[0]).animate(R.anim.load_image).placeholder(R.drawable.default_one).error(R.drawable.default_one).into(hot_first_iv);
                } else {
                    hot_first_iv.setVisibility(View.GONE);
                }
                if (imageData.length == 2) {
                    Glide.with(context).load(imageData[0]).animate(R.anim.load_image).placeholder(R.drawable.default_two).error(R.drawable.default_two).into(hot_two_iv1);
                    Glide.with(context).load(imageData[1]).animate(R.anim.load_image).placeholder(R.drawable.default_two).error(R.drawable.default_two).into(hot_two_iv2);
                    hot_two_iv1.setVisibility(View.VISIBLE);
                    hot_two_iv2.setVisibility(View.VISIBLE);
                } else {
                    hot_two_iv1.setVisibility(View.GONE);
                    hot_two_iv2.setVisibility(View.GONE);
                }
                if (imageData.length >= 3) {
                    Glide.with(context).load(imageData[0]).animate(R.anim.load_image).placeholder(R.drawable.default_three).error(R.drawable.default_three).into(hot_iv1);
                    Glide.with(context).load(imageData[1]).animate(R.anim.load_image).placeholder(R.drawable.default_three).error(R.drawable.default_three).into(hot_iv2);
                    Glide.with(context).load(imageData[2]).animate(R.anim.load_image).placeholder(R.drawable.default_three).error(R.drawable.default_three).into(hot_iv3);
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

}
