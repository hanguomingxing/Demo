package com.me.daydaystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.me.daydaystudy.R;
import com.me.daydaystudy.base.BaseFragment;
import com.me.daydaystudy.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/11.
 */

public class HotFragment extends BaseFragment implements OnBannerClickListener {

    private View inflate;
    private Banner banner;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inflate = inflater.inflate(R.layout.hot_fragment_layout, null);
        banner = (Banner) inflate.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //banner设置方法全部调用完毕时最后调用
        banner.setOnBannerClickListener(this);
        banner.setBannerAnimation(Transformer.Accordion);

        requestCircleData();
        //设置图片集合
//        banner.setImages(list);
        return inflate;
//        super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 请求网络
     */
    private void requestCircleData() {

    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getActivity(), "第" + position + "个", Toast.LENGTH_SHORT).show();
    }
}
