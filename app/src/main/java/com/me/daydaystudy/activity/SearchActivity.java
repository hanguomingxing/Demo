package com.me.daydaystudy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseActivity;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @Bind(R.id.search_s)
    ImageView searchS;
    @Bind(R.id.autotv_search)
    EditText autotvSearch;
    @Bind(R.id.search_d)
    ImageView searchD;
    @Bind(R.id.tv_search_exit)
    TextView tvSearchExit;
    @Bind(R.id.progressbar_change_photo)
    ProgressBar progressbarChangePhoto;
    @Bind(R.id.id_tv_loadingmsg)
    TextView idTvLoadingmsg;
    @Bind(R.id.btn_search_1)
    RadioButton btnSearch1;
    @Bind(R.id.btn_search_2)
    RadioButton btnSearch2;
    @Bind(R.id.btn_search_3)
    RadioButton btnSearch3;
    @Bind(R.id.rg_search_1)
    RadioGroup rgSearch1;
    @Bind(R.id.btn_search_4)
    RadioButton btnSearch4;
    @Bind(R.id.btn_search_5)
    RadioButton btnSearch5;
    @Bind(R.id.btn_search_6)
    RadioButton btnSearch6;
    @Bind(R.id.rg_search_2)
    RadioGroup rgSearch2;
    @Bind(R.id.lv_search_history)
    ListView lvSearchHistory;
    @Bind(R.id.frame_search)
    FrameLayout frameSearch;
    @Bind(R.id.iv)
    ImageView iv;
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.tv_search_result_count)
    TextView tvSearchResultCount;
    @Bind(R.id.listview_search)
    ListView listviewSearch;
    @Bind(R.id.linear_search_result)
    AutoLinearLayout linearSearchResult;
    @Bind(R.id.framelayout_search_result)
    AutoFrameLayout framelayoutSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.search_s, R.id.autotv_search, R.id.search_d, R.id.tv_search_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_s:
                break;
            case R.id.autotv_search:
                break;
            case R.id.search_d:
                break;
            case R.id.tv_search_exit:
                finish();
                break;
        }
    }
}
