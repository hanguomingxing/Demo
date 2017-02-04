package com.me.daydaystudy.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.me.daydaystudy.R;
import com.me.daydaystudy.activity.ClassListActivity;
import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.base.BaseFragment;
import com.me.daydaystudy.bean.SortBean;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;
import com.me.daydaystudy.utils.CommonUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * @author :   郗琛
 * @date :   2017/1/10
 */

public class CategoryFragment extends BaseFragment {

    private LinearLayout viewRoot;
    private int[] sortLeftIcon = new int[]{R.drawable.heart, R.drawable.coffee, R.drawable.diamond, R.drawable.portfolio, R.drawable.hat, R.drawable.language};
    private int[] sortRightIcon = new int[]{R.drawable.heart_s, R.drawable.coffee_s, R.drawable.diamond_s, R.drawable.heart_s, R.drawable.hat_s, R.drawable.language_s};
    private int[] sortCenterTvColor = new int[]{0xFFF95D51, 0xFFFF9C00, 0xFF7DCD43, 0xFFF95D51, 0xFF38B5EE, 0xFF90369A};
    private int defaultSortRightIcon = R.drawable.down;
    private int defaultSortCenterTvColor = 0xFF333333;
    private Map<Integer, View> viewMap = new HashMap<>();
    private SortBean[] sortBean;

    @Override
    protected void initData() {
        super.initData();
        HttpManger.getMethod(ConstantUtils.sort, new MyCallBack() {


            @Override
            public void onFailure(Call<String> call, Throwable t) {
                requestChangeViewStatus(ERROR_VIEW);
            }

            @Override
            public void onResponse(String response) {
                sortBean = new Gson().fromJson(response, SortBean[].class);
                requestShowNormalView(initView(sortBean));
            }
        });
    }

    /**
     * 编辑布局
     *
     * @param sortBean
     * @return
     */
    private View initView(SortBean[] sortBean) {
        viewRoot = (LinearLayout) View.inflate(getActivity(), R.layout.fragment_category, null);
        //添加视图
        for (int i = 0; i < sortBean.length; i++) {
            //查找视图
            View categoryView = getCategoryView(sortBean[i], i);
            //设置监听
            final int finalI = i;
            categoryView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setCurrentSelectView(finalI);
                }
            });
            //添加到根布局
            viewRoot.addView(categoryView);
        }
        //设置默认选中条目
        setCurrentSelectView(0);
        //返回创建的视图
        return viewRoot;
    }

    /**
     * 创建对应的view
     *
     * @param sortBean
     * @param position
     * @return
     */
    private View getCategoryView(final SortBean sortBean, int position) {
        View view = viewMap.get(position);
        if (view != null)
            return view;
        view = View.inflate(getActivity(), R.layout.category_item_center, null);
        TextView tv = (TextView) view.findViewById(R.id.sort_tv1);
        tv.setText(sortBean.getCname());
        ImageView ivLeft = (ImageView) view.findViewById(R.id.sort_icon_left);
        ivLeft.setImageResource(sortLeftIcon[position]);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.category_rv);

        //添加一个全部按钮
        if (position != 0) {
            SortBean.NodesBean nodesBean = new SortBean.NodesBean();
            nodesBean.setCategory_name("全部");
            sortBean.getNodes().add(0, nodesBean);
        }

        //设置分割线
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view,
                                       RecyclerView parent, RecyclerView.State state) {
                outRect.top = (parent.getChildPosition(view) < 3) ? 0 : 10;
//                outRect.right = 10;
                outRect.right = (parent.getChildPosition(view) % 3 != 2) ? 10 : 0;

            }
        });
        //设置管理者
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        });
        //设置适配器
        recyclerView.setAdapter(new CommonAdapter<SortBean.NodesBean>(getActivity(), R.layout.category_item, sortBean.getNodes()) {
            @Override
            protected void convert(ViewHolder holder, final SortBean.NodesBean nodesBean, int position) {
                //设置点击跳转
                TextView category_tv = holder.getView(R.id.category_tv);
                category_tv.setText(nodesBean.getCategory_name());
                category_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //传送id
                        Intent intent = new Intent(getActivity(), ClassListActivity.class);
                        intent.putExtra("data", CategoryFragment.this.sortBean);
                        intent.putExtra("title", nodesBean.getCategory_name()   );
                        intent.putExtra("id", nodesBean.getId());
                        getActivity().startActivity(intent);
                    }
                });
            }

        });
        viewMap.put(position, view);
        return view;
    }

    /**
     * 设置当前选中的条目
     *
     * @param position
     */
    private void setCurrentSelectView(int position) {
        for (int i = 0; i < viewMap.size(); i++) {
            View view = viewMap.get(i);

            TextView tv = (TextView) view.findViewById(R.id.sort_tv1);
            ImageView ivRight = (ImageView) view.findViewById(R.id.sort_icon_right);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.category_rv);
            boolean b = position == i;
            if (recyclerView.getVisibility() == View.VISIBLE) {
                b = false;
            }
            tv.setTextColor(b ? sortCenterTvColor[position] : defaultSortCenterTvColor);
            ivRight.setImageResource(b ? sortRightIcon[position] : defaultSortRightIcon);
            recyclerView.setVisibility(b ? View.VISIBLE : View.GONE);
        }

    }
}
