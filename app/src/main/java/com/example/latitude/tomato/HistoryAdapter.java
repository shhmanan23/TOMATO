package com.example.latitude.tomato;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter {
    ArrayList r_id, o_time, o_sum, o_id;
    Context context;

    public HistoryAdapter(Context context, ArrayList r_id, ArrayList o_sum, ArrayList o_time, ArrayList o_id) {
        this.context = context;
        this.o_time=o_time;
        this.o_sum=o_sum;
        this.r_id=r_id;
        this.o_id = o_id;
    }

    public class HH extends RecyclerView.ViewHolder {
        // init the item view's
        TextView r_id , o_time, o_sum ;
        String OrderId;

        public HH(View itemView) {
            super(itemView);
            // get the reference of item view's
            r_id = itemView.findViewById(R.id.r_id);
            o_time = itemView.findViewById(R.id.o_time);
            o_sum= itemView.findViewById(R.id.o_sum);

        }
    }


    @Override
    public HH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_row, parent, false);
        HistoryAdapter.HH vh = new HistoryAdapter.HH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        ((HH)holder).r_id.setText(r_id.get(position).toString());
        ((HH)holder).o_time.setText(o_time.get(position).toString());
        ((HH)holder).o_sum.setText(o_sum.get(position).toString());
        ((HH)holder).OrderId = o_id.get(position).toString();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context,O_S.class);
                in.putExtra("orderid",((HH) holder).OrderId);
                Str.OID=((HH) holder).OrderId;
                context.startActivity(in);

            }
        });
    }

    @Override
    public int getItemCount() {
        return r_id.size();
    }
}
