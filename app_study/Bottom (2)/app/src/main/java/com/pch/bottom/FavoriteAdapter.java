package com.pch.bottom;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



import java.util.ArrayList;

import vo.FavoriteVO;

public class FavoriteAdapter extends ArrayAdapter<FavoriteVO> {

    Context context;
    int resource;
    FavoriteVO vo;
    ArrayList<FavoriteVO> list;
    ListView myListview;


    public FavoriteAdapter( Context context, int resource, ArrayList<FavoriteVO> list, ListView myListview) {
        super(context, resource, list);

        this.context = context;
        this.resource = resource;
        this.list = list;
        this.myListview = myListview;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        LayoutInflater linf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = linf.inflate(resource, null);


        TextView title = convertView.findViewById(R.id.restaurant_title);
        TextView address = convertView.findViewById(R.id.address);
        TextView category = convertView.findViewById(R.id.category);
        TextView img = convertView.findViewById(R.id.restaurant_img);



        title.setText(vo.getR_title());
        address.setText(vo.getAddress());
        category.setText(vo.getCategory());

        return convertView;
    }
}
