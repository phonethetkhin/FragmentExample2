package com.example.fragmentexample2.Adapters;

import android.content.Context;
import android.media.CamcorderProfile;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fragmentexample2.Models.CategoryModel;
import com.example.fragmentexample2.R;

import java.util.List;

public class SpnAdapter extends ArrayAdapter {
    List<CategoryModel> categoryModelList;

    public SpnAdapter(@NonNull Context context, int resource, int textViewResourceId, List<CategoryModel> categoryModelList) {
        super(context, resource, textViewResourceId);
        this.categoryModelList = categoryModelList;
    }

    @Override
    public int getCount() {
        return categoryModelList.size();
    }


public View getCustomView(int i, View v,ViewGroup parent)
{
    TextView tvCategoryName;

     v= LayoutInflater.from(parent.getContext()).inflate(R.layout.spinnerlistitem,parent,false);
    tvCategoryName=v.findViewById(R.id.tvCategoryName);
tvCategoryName.setText(categoryModelList.get(i).getCategoryName());
     return v;
}

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        return getCustomView(i,view,viewGroup);
    }
}
