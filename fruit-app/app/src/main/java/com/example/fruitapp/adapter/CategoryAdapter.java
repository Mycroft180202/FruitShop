package com.example.fruitapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fruitapp.R;
import com.example.fruitapp.model.Category;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    private List<Category> categories;
    private LayoutInflater inflater;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.categories = categories;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Category getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categories.get(position).getCategoryId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
            holder = new ViewHolder();
            holder.categoryName = convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Category category = categories.get(position);
        holder.categoryName.setText(category.getCategoryName());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    static class ViewHolder {
        TextView categoryName;
    }

    public void updateCategories(List<Category> newCategories) {
        this.categories.clear();
        this.categories.addAll(newCategories);
        notifyDataSetChanged();
    }
}