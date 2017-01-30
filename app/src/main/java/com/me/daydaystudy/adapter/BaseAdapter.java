package com.me.daydaystudy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.me.daydaystudy.bean.SortBean;

import java.util.ArrayList;

/**
 * @author :   郗琛
 * @date :   2017/1/24
 */

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    ArrayList<T> list = new ArrayList<>();

    public BaseAdapter(ArrayList<T> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
