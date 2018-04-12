package com.example.latitude.tomato;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class LAdapter extends RecyclerView.Adapter {
    ArrayList i_name;
    ArrayList i_price;
    Context context;
    public LAdapter(Context context, ArrayList i_name, ArrayList i_price){
        this.context = context;
        this.i_name = i_name;
        this.i_price = i_price;
    }
    public class LVH extends RecyclerView.ViewHolder implements View.OnClickListener{
        CheckBox Iadd;
        TextView Iprice, Iname;
        public LVH(View itemView){
            super(itemView);
            Iname = itemView.findViewById(R.id.itemname);
            Iadd = itemView.findViewById(R.id.additem);
            Iprice = itemView.findViewById(R.id.price);

        }


        @Override
        public void onClick(View v) {

        }
    }
    @Override
    public LVH  onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewmenu_row, parent, false);
        return new LVH(v);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((LVH)holder).Iprice.setText(i_price.get(position).toString());
        ((LVH)holder).Iname.setText(i_name.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return i_name.size();
    }


}
