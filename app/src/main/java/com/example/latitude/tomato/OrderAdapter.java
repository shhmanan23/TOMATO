package com.example.latitude.tomato;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter {
    interface total{
        void Total(ArrayList amt);
    }
    ArrayList b_name, b_price , amt;
    Context summary;
    public OrderAdapter(Summary summary, ArrayList b_name, ArrayList b_price) {
        this.summary = summary;
        this.b_name = b_name;
        this.b_price = b_price;
        this.amt = (ArrayList) b_price.clone();
    }
    total t = new Summary();
//    public interface price{
//
//        int getprice(ArrayList b_price , ArrayList quantity);
//    }

    public class ODH extends RecyclerView.ViewHolder {
        // init the item view's
        TextView I_N , I_P, Amount ;
        EditText Qe;

        public ODH(View itemView) {
            super(itemView);
            // get the reference of item view's
            I_N = itemView.findViewById(R.id.I_N);
            I_P = itemView.findViewById(R.id.I_P);
            Qe = itemView.findViewById(R.id.Q);
            Amount = itemView.findViewById(R.id.Amount);
            Qe.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
        }
    }

    @Override
    public ODH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_sum, parent, false);
       OrderAdapter.ODH vh = new OrderAdapter.ODH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position) {
        ((ODH)holder).I_N.setText(b_name.get(position).toString());
        ((ODH)holder).I_P.setText(b_price.get(position).toString());
        ((ODH)holder).Amount.setText(b_price.get(position).toString());
        ((ODH)holder).Qe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                long p= (long)b_price.get(position);

                long q = (s.toString().equals(""))?1:Long.valueOf(s.toString());
                q=q*p;
                ((ODH)holder).Amount.setText(Long.toString(q));
                amt.set(position, q);
                t.Total(amt);
                //Toast.makeText(summary, Long.toString(p*q), Toast.LENGTH_SHORT).show();
                //int Amt = (Integer.parseInt((String)b_price.get(position)))*(Integer.parseInt((String)s));
                //Toast.makeText(summary, Integer.toString(Amt), Toast.LENGTH_SHORT).show();
                //((ODH)holder).Amount.setText(Integer.parseInt((String)b_price.get(position))*Integer.parseInt((String)s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
       // ((ODH)holder).rate.setText(ratingss.get(position).toString());
       // ((ODH) holder).Qe.setText(Qe.[holder.getAdapterPosition()]);
      //  holder.Qe.setText(Qe[holder.getAdapterPosition()]);
       // holder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());
        //((ODH) holder).Qe.setText(b_name[holder.getAdapterPosition()]);

    }

    @Override
    public int getItemCount() {
        return b_name.size();
    }
}
