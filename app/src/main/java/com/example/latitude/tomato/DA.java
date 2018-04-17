package com.example.latitude.tomato;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DA extends RecyclerView.Adapter {
    ArrayList username,rev,ratingss;
    Context context;
    public DA(Context context, ArrayList username, ArrayList rev, ArrayList ratingss) {

        this.context = context;
        this.username = username;
        this.ratingss = ratingss;
        this.rev = rev;
    }

    public class MyVH extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name,rev,rate;

        public MyVH(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.user);
            rev = (TextView) itemView.findViewById(R.id.rev);
            rate = (TextView) itemView.findViewById(R.id.rating);
        }
    }


    @Override
    public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_rdisplay, parent, false);
        MyVH vh = new MyVH(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        ((MyVH)holder).name.setText(username.get(position).toString());
        ((MyVH)holder).rev.setText(rev.get(position).toString());
        ((MyVH)holder).rate.setText(ratingss.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return username.size();
    }
}
