package com.me.daydaystudy.factory;

import com.me.daydaystudy.fragment.HotChildFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/16.
 */

public class HotChildFragmentFactory {
    private static Map<String, HotChildFragment> map = new HashMap<>();

    public static HotChildFragment getHotChildFragmentInstance(String titleTid) {
        HotChildFragment hotChildFragment = map.get(titleTid);
        if (hotChildFragment != null) {
            return hotChildFragment;
        }
        hotChildFragment = HotChildFragment.getHotChildFragmentInstance(titleTid);
        map.put(titleTid, hotChildFragment);
        return hotChildFragment;
    }

}
