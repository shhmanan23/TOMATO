package com.example.latitude.tomato;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MVH> {
    private final Context context;
    ArrayList r_image;
    ArrayList r_name;
    ArrayList r_add;

    public CustomAdapter(Context context, ArrayList r_image, ArrayList r_name, ArrayList r_add){
        this.context = context;
        this.r_image = r_image;
        this.r_name = r_name;
        this.r_add = r_add;
    }

    public class MVH extends  RecyclerView.ViewHolder{
        TextView name , add;
        ImageView I;
        public  MVH (View itemView){
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            add = (TextView)itemView.findViewById(R.id.address);
            I = (ImageView)itemView.findViewById(R.id.I1);


        }

    }

    @Override
    public MVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlist, parent, false);
        MVH vh = new MVH(v);
        return  vh;


    }

    @Override
    public void onBindViewHolder(final MVH holder, final int position) {
        holder.name.setText(r_name.get(position).toString());
        holder.add.setText(r_add.get(position).toString());
        holder.I.setImageResource(r_image.get(position).hashCode());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String res_name = holder.name.getText().toString();
                String res_add = holder.add.getText().toString();
                Intent in = new Intent(context,rdetails.class);
                in.putExtra("RESNAME",res_name);
                in.putExtra("RESADD",res_add);
                // Toast.makeText(context, r_name.get(position)+"", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return r_name.size();
    }
}
