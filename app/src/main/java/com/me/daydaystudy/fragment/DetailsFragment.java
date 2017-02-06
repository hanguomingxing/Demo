package com.me.daydaystudy.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseFragment;
import com.me.daydaystudy.bean.CatalogBean;
import com.me.daydaystudy.bean.XQBean;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;


/**
 * autour: 孙运泰
 * date: 2017/1/16 14:25
 * update: 2017/1/16
 */
public class DetailsFragment extends BaseFragment {
    private View view;
    private static String TAG = "DetailsFragment";
    private RecyclerView mulu;
    private List<CatalogBean.DataBean.NodesBean> nodesBean;
    private String title;
    private int num = 1;
    private XQBean xqBean;

    @Override
    protected void initData() {
        super.initData();
        String id = getArguments().getString("id");
        title = getArguments().getString("title");
        if (title.equals("详情")) {
            requestData(id, ConstantUtils.USER_XQ);
        } else if (title.equals("目录")) {
            requestData(id, ConstantUtils.USER_ML);
        }
    }

    private void requestData(String id, String path) {
        Map<String, String> map = new HashMap<>();

        map.put("courseid", id);

        HttpManger.postMethod(path, map, new MyCallBack() {

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                switch (title) {
                    case "详情":
                        xqBean = gson.fromJson(response, XQBean.class);

                        initView1();
                        break;
                    case "目录":
                        CatalogBean catalogBean = gson.fromJson(response, CatalogBean.class);
                        nodesBean = catalogBean.getData().get(0).getNodes();
                        initView();
                        break;

                }


            }
        });
    }

    private void initView1() {
        view = View.inflate(getActivity(), R.layout.details_xq, null);

        TextView teacher = (TextView) view.findViewById(R.id.teacher);
        TextView school = (TextView) view.findViewById(R.id.school);
        teacher.setText("讲师:" + xqBean.getData().getCourse_tname());
        school.setText("学校:" + xqBean.getData().getSchool_name());
        TextView kecheng = (TextView) view.findViewById(R.id.kecheng);
        Spanned spanned= Html.fromHtml(xqBean.getData().getDes());
        kecheng.setText(spanned.toString());
        requestShowNormalView(view);
    }


    private void initView() {


        view = View.inflate(getActivity(), R.layout.detailsfragment, null);
        recycler();

        //展示请求
        requestShowNormalView(view);
    }

    private void recycler() {
        mulu = (RecyclerView) view.findViewById(R.id.mulu);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mulu.setLayoutManager(layoutManager);

        mulu.setAdapter(new CommonAdapter<CatalogBean.DataBean.NodesBean>(getContext(), R.layout.course_catalog_item_listview_item, nodesBean) {
            @Override
            protected void convert(ViewHolder holder, CatalogBean.DataBean.NodesBean nodesBean, final int position) {
                holder.setText(R.id.tv_name, nodesBean.getSections_name());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                String format = simpleDateFormat.format(nodesBean.getVtime());
                holder.setText(R.id.tv_time, format);
//                TextView iv_index = (TextView) view.findViewById(R.id.iv_index);
                holder.setText(R.id.iv_index, "1-" + num);
                num++;

            }
        });
    }
}
