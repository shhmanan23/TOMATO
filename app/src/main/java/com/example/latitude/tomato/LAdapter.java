package com.example.latitude.tomato;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class LAdapter extends RecyclerView.Adapter  {
    final ArrayList i_name=new ArrayList();
    final ArrayList i_price=new ArrayList();
    final ArrayList i_id = new ArrayList();
    Context context;
    ArrayList check;
    int callingClass;

    public interface Adderb{
        void Addb(ArrayList i_name, ArrayList i_price, ArrayList i_id,ArrayList check);
    }
    public interface Adderi{
        void Addi(ArrayList i_name, ArrayList i_price, ArrayList i_id, ArrayList check);
    }
    public interface Adderc{
        void Addc(ArrayList i_name, ArrayList i_price, ArrayList i_id, ArrayList check);
    }
    private Adderc adderc=new viewmenu();
    private Adderi adderi=new viewmenu();
    private Adderb adderb=new viewmenu();

    public LAdapter(final Context context, String rest_id, final int CallingClass){
        this.context = context;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        switch (CallingClass){
            case 0:
                db.collection("Menu").whereEqualTo("Restaurant", rest_id)
                        .whereEqualTo("Cat", "Bestsellers")
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Toast.makeText(context, "best", Toast.LENGTH_SHORT).show();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                            i_name.add(queryDocumentSnapshot.get("Name"));
                            i_price.add(queryDocumentSnapshot.getLong("Price"));
                            i_id.add(queryDocumentSnapshot.getId());
                        }
                        notifyDataSetChanged();
                        check = new ArrayList(getItemCount());
                        for (int i = 0; i < getItemCount(); i++) {
                             check.add(false);
                        }
                        callingClass = CallingClass;
                    }
                });
                break;
            case 1:
                db.collection("Menu").whereEqualTo("Restaurant", rest_id)
                        .whereEqualTo("Cat", "Indian")
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                            i_name.add(queryDocumentSnapshot.get("Name"));
                            i_price.add(queryDocumentSnapshot.getLong("Price"));
                            i_id.add(queryDocumentSnapshot.getId());
                        }
                        notifyDataSetChanged();
                        check = new ArrayList(getItemCount());
                        for (int i = 0; i < getItemCount(); i++) {
                            check.add(false);
                        }
                        callingClass = CallingClass;
                    }
                });
                break;
            case 2:
                db.collection("Menu").whereEqualTo("Restaurant", rest_id)
                        .whereEqualTo("Cat", "Continental")
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                            i_name.add(queryDocumentSnapshot.get("Name"));
                            i_price.add(queryDocumentSnapshot.getLong("Price"));
                            i_id.add(queryDocumentSnapshot.getId());
                        }
                        notifyDataSetChanged();
                        check = new ArrayList(getItemCount());
                        for (int i = 0; i < getItemCount(); i++) {
                            check.add(false);
                        }
                        callingClass = CallingClass;
                    }
                });
                break;
        }
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
                check.set(position,((LVH)holder).Iadd.isChecked());
                switch (callingClass){
                    case 0:
                        adderb.Addb(i_name, i_price, i_id, check);
                        break;
                    case 1:
                        adderi.Addi(i_name, i_price, i_id, check);
                        break;
                    case 2:
                        adderc.Addc(i_name, i_price, i_id, check);
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