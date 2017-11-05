package com.example.thevillain.mathforbaby.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thevillain.mathforbaby.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Huy on 05/11/2017.
 */

public class ListAdapter extends ArrayAdapter<Units> {
    public ListAdapter(Context context, int resource, List<Units> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.list_itemunit, null);
        }
        Units p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txtTitle = (TextView) view.findViewById(R.id.textUnits);
            txtTitle.setText(p.getUnit_name()+"");

            ImageView img = (ImageView) view.findViewById(R.id.imageUnits);
            Picasso.with(getContext()).load(p.getUnit_img()).into(img);
        }
        return view;
    }
}
