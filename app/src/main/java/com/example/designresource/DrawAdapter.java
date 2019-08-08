package com.example.designresource;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DrawAdapter extends BaseAdapter {

    private List<ModeDraw> listDraw;
    private Context context;

    public DrawAdapter(List<ModeDraw> listDraw, Context context) {
        this.listDraw = listDraw;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listDraw.size();
    }

    @Override
    public ModeDraw getItem(int position) {
        return listDraw.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inf = LayoutInflater.from(context);
            view = inf.inflate(R.layout.list_adapter, parent, false);
        }
        TextView tittle = view.findViewById(R.id.textView);
        TextView description = view.findViewById(R.id.textView2);

        tittle.setText(getItem(position).getTittle());
        description.setText(getItem(position).getDescription());

        return view;
    }
}
