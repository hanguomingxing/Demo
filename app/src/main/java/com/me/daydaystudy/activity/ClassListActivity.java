package com.me.daydaystudy.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.interfaces.ConstantUtils;
import com.me.daydaystudy.manager.HttpManger;
import com.me.daydaystudy.manager.MyCallBack;
import com.me.daydaystudy.utils.TitleHelp;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

public class ClassListActivity extends BaseActivity {

    private String id;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        initTitle();
        initData();
    }

    private void initData() {
        id = getIntent().getStringExtra("id");
        getNewData();
    }

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
        map.put("order", "");       //排序方式 course_ctime 最近更新  course_paycount 学习人数  ""综合排序
        map.put("p", currentPage + "");       //页数
        map.put("isfree", true ? "1" : "2");           //1收费，2免费
        map.put("by", "desc");
        // map.put("by", true ? "asc" : "desc");
        HttpManger.postMethod(ConstantUtils.ClassListUrl, map, new MyCallBack() {
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ClassListActivity.this, "刷新失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
            }
        });
    }
}
