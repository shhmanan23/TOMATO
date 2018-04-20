package com.example.latitude.tomato;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter {
    ArrayList b_name, b_price , b_check;
    Context summary;
    public OrderAdapter(Summary summary, ArrayList b_name, ArrayList b_price, ArrayList b_check) {
        this.summary = summary;
        this.b_check = b_check;
        this.b_name = b_name;
        this.b_price = b_price;
    }

    public class ODH extends RecyclerView.ViewHolder {
        // init the item view's
        TextView I_N , I_P ;
        EditText Q;

        public ODH(View itemView) {
            super(itemView);
            // get the reference of item view's
            I_N = (TextView) itemView.findViewById(R.id.I_N);
            I_P = (TextView) itemView.findViewById(R.id.I_P);
            Q = (EditText) itemView.findViewById(R.id.Q);
        }
    }

    @Override
    public ODH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_sum, parent, false);
       OrderAdapter.ODH vh = new OrderAdapter.ODH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ODH)holder).I_N.setText(b_name.get(position).toString());
        ((ODH)holder).I_P.setText(b_price.get(position).toString());
       // ((ODH)holder).rate.setText(ratingss.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return b_name.size();
    }
}
