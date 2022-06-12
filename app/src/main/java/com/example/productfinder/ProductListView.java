package com.example.productfinder;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

class ProductListView extends ArrayAdapter<String> {
    ArrayList<String> list;
    Context context;

    public ProductListView(Context context, ArrayList<String> items) {
        super(context, R.layout.list_row, items);
        this.context = context;
        list = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_row, null);
            TextView name = convertView.findViewById(R.id.name);
            ImageView remove = convertView.findViewById(R.id.remove);
            ImageView copy = convertView.findViewById(R.id.copy);
            TextView number = convertView.findViewById(R.id.number);

            number.setText(position + 1 + ".");
            name.setText(list.get(position));

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    product_list.removeItem(position);
                }
            });
            copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    product_list.addItem(list.get(position));
                }
            });
        }
        return convertView;
    }

}
