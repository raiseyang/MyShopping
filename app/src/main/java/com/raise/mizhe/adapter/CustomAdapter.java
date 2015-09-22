package com.raise.mizhe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by yu on 2015/9/2.
 */
public abstract class CustomAdapter<Type> extends BaseAdapter {

    private int layout_id;
    private Context m_context;
    private ArrayList<Type> m_datas;

    public CustomAdapter(Context context, int layout_id) {
        this.layout_id = layout_id;
        m_context = context;
    }

    public void setDates(ArrayList<Type> datas){
        m_datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return m_datas == null ? 0 : m_datas.size();
    }

    @Override
    public Type getItem(int position) {
        return m_datas == null ? null : m_datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(m_context).inflate(layout_id, parent, false);
        }
        showView(getItem(position), convertView, position);
        return convertView;
    }

    public abstract void showView(Type item, View view, int position);
}
