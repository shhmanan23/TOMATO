package com.example.latitude.tomato;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TA extends RecyclerView.Adapter {

    ArrayList it,q;
    Context context;
    public TA(Context context, ArrayList it, ArrayList q) {

        this.context = context;
        this.it = it;
        this.q = q;
    }

    public class MyVH extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name,quant;

        public MyVH(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = itemView.findViewById(R.id.itemm);
            quant = itemView.findViewById(R.id.quantity);
        }
    }


    @Override
    public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.total_row, parent, false);
        MyVH vh = new MyVH(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((MyVH)holder).name.setText(it.get(position).toString());
        ((MyVH)holder).quant.setText(q.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return it.size();
    }
}
