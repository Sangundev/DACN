package com.example.shopeeappconnections.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListcategoryAdapter extends BaseAdapter {
    private Context context;
    private List<String> categories;

    public ListcategoryAdapter(Context context, List<String> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories != null ? categories.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return categories != null ? categories.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        String category = (String) getItem(position);

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(category);

        return convertView;
    }
}
