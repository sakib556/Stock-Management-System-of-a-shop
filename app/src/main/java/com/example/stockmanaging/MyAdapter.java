package com.example.stockmanaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Products> {
    Context context;
    List<Products> arrayListProducts;

    public MyAdapter(@NonNull Context context, List<Products> arrayListProducts) {
        super(context,R.layout.custom_list_item,arrayListProducts);
        this.context=context;
        this.arrayListProducts=arrayListProducts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);
        TextView tvid=view.findViewById(R.id.proid);
        TextView tvname=view.findViewById(R.id.pnameid);
        tvid.setText(arrayListProducts.get(position).getId());
        tvname.setText(arrayListProducts.get(position).getPname());

        return view;
    }
}
