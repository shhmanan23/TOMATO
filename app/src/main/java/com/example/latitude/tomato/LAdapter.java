package com.example.latitude.tomato;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

public class LAdapter extends RecyclerView.Adapter {
    ArrayList i_name;
    ArrayList i_price;
    Context context;
    boolean check[];
    int callingClass;
    public interface Adderb{
        void Addb(ArrayList i_name, ArrayList i_price, boolean[] check);
    }
    public interface Adderi{
        void Addi(ArrayList i_name, ArrayList i_price, boolean[] check);
    }
    public interface Adderc{
        void Addc(ArrayList i_name, ArrayList i_price, boolean[] check);
    }
    private Adderc adderc=new viewmenu();
    private Adderi adderi=new viewmenu();
    private Adderb adderb=new viewmenu();
    public LAdapter(){}

    public LAdapter(Context context, ArrayList i_name, ArrayList i_price, int callingClass){
        this.context = context;
        this.i_name = i_name;
        this.i_price = i_price;
        this.check = new boolean[getItemCount()];
        this.callingClass = callingClass;
    }
    public class LVH extends RecyclerView.ViewHolder{
        CheckBox Iadd;
        TextView Iprice, Iname;
        public LVH(View itemView){
            super(itemView);
            Iname = itemView.findViewById(R.id.itemname);
            Iadd = itemView.findViewById(R.id.additem);
            Iprice = itemView.findViewById(R.id.price);
        }

    }
    @Override
    public LVH  onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewmenu_row, parent, false);
        return new LVH(v);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((LVH)holder).Iprice.setText(i_price.get(position).toString());
        ((LVH)holder).Iname.setText(i_name.get(position).toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LVH)holder).Iadd.toggle();
                check[position]=((LVH)holder).Iadd.isChecked();
                //check.set(position, ((LVH)holder).Iadd.isChecked()?"true":"false");
                switch (callingClass){
                    case 0:
                        adderb.Addb(i_name, i_price, check);
                        break;
                    case 1:
                        adderi.Addi(i_name, i_price, check);
                        break;
                    case 2:
                        adderc.Addc(i_name, i_price, check);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return i_name.size();
    }


}
