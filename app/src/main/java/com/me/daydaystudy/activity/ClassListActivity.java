package com.me.daydaystudy.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.bean.ClassListBean;
import com.me.daydaystudy.bean.SortBean;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;
import com.me.daydaystudy.utils.TitleHelp;
import com.me.daydaystudy.utils.ViewUtils;
import com.me.daydaystudy.view.AddFriendView;
import com.me.daydaystudy.view.BackActivityView;
import com.me.daydaystudy.view.PostInfoView;
import com.me.daydaystudy.view.SearchView;
import com.me.daydaystudy.view.ShareView;
import com.me.daydaystudy.view.TransmitView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.me.daydaystudy.R.id.class_name_rb;


public class ClassListActivity extends BaseActivity {
    @Bind(R.id.mine_title)
    TextView mineTitle;
    @Bind(R.id.title_back)
    BackActivityView titleBack;
    @Bind(R.id.title_addFriend)
    AddFriendView titleAddFriend;
    @Bind(R.id.title_left_group)
    AutoLinearLayout titleLeftGroup;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.title_home)
    ImageView titleHome;
    @Bind(R.id.title_tab)
    TabLayout titleTab;
    @Bind(R.id.title_center_group)
    AutoRelativeLayout titleCenterGroup;
    @Bind(R.id.title_search)
    SearchView titleSearch;
    @Bind(R.id.title_transmit)
    TransmitView titleTransmit;
    @Bind(R.id.title_share)
    ShareView titleShare;
    @Bind(R.id.title_postInfo)
    PostInfoView titlePostInfo;
    @Bind(R.id.title_right_group)
    AutoLinearLayout titleRightGroup;
    @Bind(R.id.common_title)
    AutoLinearLayout commonTitle;
    @Bind(R.id.class_name_rb)
    RadioButton classNameRb;
    @Bind(R.id.class_name)
    AutoRelativeLayout className;
    @Bind(R.id.class_free_rb)
    RadioButton classFreeRb;
    @Bind(R.id.class_free)
    AutoRelativeLayout classFree;
    @Bind(R.id.class_order_rb)
    RadioButton classOrderRb;
    @Bind(R.id.class_order)
    AutoRelativeLayout classOrder;
    @Bind(R.id.class_list_rv)
    RecyclerView classListRv;
    @Bind(R.id.activity_class_list)
    AutoLinearLayout activityClassList;
    private String id;
    private int currentPage;
    private String isfree = "";
    private String order = "";
    private ArrayList<SortBean> sortBean;
    private ListView firstList;
    private ListView secondList;
    private ListView thirdlyList;
    private ArrayList<ClassListBean.DatalistBean> classListBeanArrayList = new ArrayList<>();
    private CommonAdapter<ClassListBean.DatalistBean> commonAdapter;
    private PopupWindow popupWindowName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        ButterKnife.bind(this);
        initTitle();
        initData();
        initAdapter();
    }

    /**
     * 最主要的适配器
     */
    private void initAdapter() {
        classListRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        commonAdapter = new CommonAdapter<ClassListBean.DatalistBean>(getActivity(), R.layout.class_list_item, classListBeanArrayList) {

            @Override
            protected void convert(ViewHolder holder, final ClassListBean.DatalistBean datalistBean, int position) {
                ImageView home_recomm_iv_main = holder.getView(R.id.home_recomm_iv_main);
                Glide.with(getActivity()).load(datalistBean.getCourse_pic()).into(home_recomm_iv_main);

                ((TextView) holder.getView(R.id.home_recomm_tv2)).setText(datalistBean.getCourse_name());
                ((TextView) holder.getView(R.id.home_recomm_tv3)).setText(datalistBean.getSchool_name());
                ((TextView) holder.getView(R.id.home_recomm_tv4)).setText(datalistBean.getCourse_price().equals("0.00") ? "免费" : "￥" + datalistBean.getCourse_price());
                ((TextView) holder.getView(R.id.home_recomm_tv5)).setText(datalistBean.getCourse_paycount() + "人在学");
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, datalistBean.getCid(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        };
        classListRv.setAdapter(commonAdapter);
    }


    private void cleanAndRefresh() {
        currentPage = 1;
        classListBeanArrayList.clear();
        getNewData();
    }


    private void resetAndRefresh() {
        isfree = "";
        classFreeRb.setText("筛选");
        order = "";
        classOrderRb.setText("排序");
        cleanAndRefresh();
    }


    /**
     * 获取初始化的数据
     */
    private void initData() {
        id = getIntent().getStringExtra("id");
        classNameRb.setText(getIntent().getStringExtra("title"));
        SortBean[] datas = (SortBean[]) getIntent().getSerializableExtra("data");
        sortBean = new ArrayList<>();
        for (int i = 1; i < datas.length; i++) {
            sortBean.add(datas[i]);
        }
        SortBean sortBean = new SortBean();
        sortBean.setCname("其他课程");
        ArrayList<SortBean.NodesBean> nodes = new ArrayList<>();
        nodes.add(new SortBean.NodesBean("", "全部", "310"));
        nodes.add(new SortBean.NodesBean("311", "K12", "310"));
        nodes.add(new SortBean.NodesBean("315", "亲子", "310"));
        sortBean.setNodes(nodes);
        this.sortBean.add(sortBean);
        getNewData();
    }

    /**
     * 初始化title
     */
    private void initTitle() {
        TitleHelp titleHelp = new TitleHelp(this);
        titleHelp.setCommonTitle("课程列表");
        titleHelp.showSearch();
    }

    /**
     * 获取新数据
     */
    private void getNewData() {
        Map<String, String> map = new HashMap<>();
        map.put("cid", id);
        map.put("order", order);       //排序方式 course_ctime 最近更新  course_paycount 学习人数  ""综合排序
        map.put("p", currentPage + "");       //页数
        map.put("isfree", isfree);           //1收费，2免费
        map.put("by", "desc");
        // map.put("by", true ? "asc" : "desc");
        HttpManger.postMethod(ConstantUtils.ClassListUrl, map, new MyCallBack() {
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ClassListActivity.this, "刷新失败" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                classListBeanArrayList.addAll(new Gson().fromJson(response, ClassListBean.class).getDatalist());
                commonAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.class_name, R.id.class_free, R.id.class_order, class_name_rb, R.id.class_free_rb, R.id.class_order_rb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.class_name:
            case class_name_rb:
                showTitleListPop();
                break;
            case R.id.class_free:
            case R.id.class_free_rb:
                showFreeListPop();
                break;
            case R.id.class_order:
            case R.id.class_order_rb:
                showOrderListPop();
                break;
        }
    }

    /**
     * 是否收费的pop
     */
    private void showFreeListPop() {
        classFreeRb.setChecked(true);
        PopupWindow popupWindow = new PopupWindow(this);
        ListView inflate = new ListView(this);
        freeData(inflate, popupWindow);
        popupWindow.setContentView(inflate);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setWidth(FrameLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(FrameLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //关闭时设置为不选中
                classFreeRb.setChecked(false);
            }
        });
        popupWindow.showAsDropDown(className);
    }


    /**
     * 排序方式的pop
     */
    private void showOrderListPop() {
        classOrderRb.setChecked(true);
        PopupWindow popupWindow = new PopupWindow(this);
        ListView inflate = new ListView(this);
        orderData(inflate, popupWindow);
        popupWindow.setContentView(inflate);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setWidth(FrameLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(FrameLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //关闭时设置为不选中
                classOrderRb.setChecked(false);
            }
        });
        popupWindow.showAsDropDown(className);
    }

    /**
     * 显示第一个的pop
     */
    private void showTitleListPop() {
        classNameRb.setChecked(true);
        popupWindowName = new PopupWindow(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.class_list_pop, null);
        initFirstView(inflate);
        popupWindowName.setContentView(inflate);
        popupWindowName.setOutsideTouchable(true);
        popupWindowName.setBackgroundDrawable(new ColorDrawable());
        popupWindowName.setWidth(FrameLayout.LayoutParams.MATCH_PARENT);
        popupWindowName.setHeight(FrameLayout.LayoutParams.WRAP_CONTENT);
        popupWindowName.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //关闭时设置为不选中
                classNameRb.setChecked(false);
            }
        });
        popupWindowName.showAsDropDown(className);
    }

    /**
     * 免费的数据
     *
     * @param listView
     * @param popupWindow
     */
    private void freeData(ListView listView, final PopupWindow popupWindow) {
        listView.setBackgroundColor(0xFFFFFFFF);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, new String[]{"免费", "收费"}));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    isfree = "2";
                    classFreeRb.setText("免费");
                } else if (position == 1) {
                    isfree = "1";
                    classFreeRb.setText("收费");
                }
                popupWindow.dismiss();
                cleanAndRefresh();
            }
        });
    }


    /**
     * 排序的数据
     *
     * @param listView
     * @param popupWindow
     */
    private void orderData(ListView listView, final PopupWindow popupWindow) {
        listView.setBackgroundColor(0xFFFFFFFF);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, new String[]{"综合排序", "最近更新", "学习人数"}));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    order = "course_ctime";
                    classOrderRb.setText("最近更新");
                } else if (position == 2) {
                    order = "course_paycount";
                    classOrderRb.setText("学习人数");
                } else {
                    classOrderRb.setText("综合排序");
                    order = "";
                }
                popupWindow.dismiss();
                cleanAndRefresh();
            }
        });
    }


    private void initFirstView(View view) {
        //设置三个listView
        firstList = (ListView) view.findViewById(R.id.listView);
        secondList = (ListView) view.findViewById(R.id.subListView);
        thirdlyList = (ListView) view.findViewById(R.id.threeListView);

        firstList.setAdapter(new com.me.daydaystudy.adapter.BaseAdapter<SortBean>(sortBean) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = ViewUtils.inflate(getActivity(), android.R.layout.simple_list_item_1);
                textView.setText(sortBean.get(position).getCname());
                setFirstListListen(textView, position);
                return textView;
            }
        });
        setSecondAdapter(0);

    }

    /**
     * 设置第一个listView的点击事件
     *
     * @param textView
     */
    private void setFirstListListen(TextView textView, final int parentPosition) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSecondAdapter(parentPosition);
            }
        });
    }

    /**
     * 设置第二个listView的适配器
     *
     * @param parentPosition
     */
    private void setSecondAdapter(final int parentPosition) {
        final ArrayList<SortBean.NodesBean> nodes = sortBean.get(parentPosition).getNodes();
        secondList.setAdapter(new com.me.daydaystudy.adapter.BaseAdapter<SortBean.NodesBean>(nodes) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = ViewUtils.inflate(getActivity(), android.R.layout.simple_list_item_1);
                textView.setText(nodes.get(position).getCategory_name());
                setSecondListListen(textView, parentPosition, position);
                return textView;
            }
        });
    }

    /**
     * 设置第二个listView的监听
     *
     * @param textView
     */
    private void setSecondListListen(TextView textView, final int grandPosition, final int parentPosition) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parentPosition == 0) {
                    id = sortBean.get(grandPosition).getNodes().get(1).getCategory_fid();
                    popupWindowName.dismiss();
                    classNameRb.setText(sortBean.get(grandPosition).getCname());
                    resetAndRefresh();
                } else {
//                thirdlyList.setAdapter();
                }
            }
        });
    }
}
