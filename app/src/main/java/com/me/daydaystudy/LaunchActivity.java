package com.me.daydaystudy;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.me.daydaystudy.base.BaseActivity;
import com.me.daydaystudy.activity.MainActivity;
import com.me.daydaystudy.utils.CommonUtils;
import com.me.daydaystudy.utils.SharedPreferencesUtils;

import static android.os.Build.VERSION_CODES.M;

public class LaunchActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        if (SharedPreferencesUtils.getBoolean("isNoFirstUse", false)) {
            secondUse();
        } else {
            firstUse();
        }
    }

    /**
     * 第一次使用
     */
    private void firstUse() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new PagerAdapter() {
            private int[] imageList = new int[]{R.drawable.guide1, R.drawable.guide2, R.drawable.guide3};

            @Override
            public int getCount() {
                return imageList.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(getActivity());
                imageView.setImageResource(imageList[position]);
                if (position == imageList.length - 1) {
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            jumpMainActivity();
                            SharedPreferencesUtils.saveBoolean("isNoFirstUse", true);
                        }
                    });
                }
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }

    /**
     * 第二次使用
     */
    private void secondUse() {
        jumpMainActivity();
    }


    /**
     * 跳转到主页面
     */
    private void jumpMainActivity() {
        jumpActivity(MainActivity.class);
        finish();
    }
}
