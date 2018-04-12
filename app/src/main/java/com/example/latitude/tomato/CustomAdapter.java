package com.example.latitude.tomato;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MVH> {
    private final Context context;
    private ArrayList r_image=new ArrayList();
    private ArrayList r_name=new ArrayList();
    private ArrayList r_add=new ArrayList();
    protected ArrayList id=new ArrayList();

    public CustomAdapter(Context c){
        this.context=c;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        db.collection("Restaurants")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                r_name.add(document.get("Name"));
                                r_add.add(document.get("Address"));
                                r_image.add(null);
                                id.add(document.getId());
                                notifyDataSetChanged();
                            }
                            for(final QueryDocumentSnapshot document : task.getResult()){
                                StorageReference pathReference = storageRef.child("Restaurants/"+document.get("photo").toString());
                                pathReference.getBytes(2048000).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                    @Override
                                    public void onSuccess(byte[] bytes) {
                                        int mnn=id.indexOf(document.getId());
                                        r_image.set(mnn, BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                                        notifyDataSetChanged();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } else {
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public class MVH extends  RecyclerView.ViewHolder{
        TextView name , add;
        ImageView I;
        public  MVH (View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name);
            add = itemView.findViewById(R.id.address);
            I = itemView.findViewById(R.id.I1);
        }
    }

    @Override
    public MVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlist, parent, false);
        return new MVH(v);
    }

    @Override
    public void onBindViewHolder(final MVH holder, final int position) {
        holder.name.setText(r_name.get(position).toString());
        holder.add.setText(r_add.get(position).toString());
        holder.I.setImageBitmap((Bitmap)r_image.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String res_name = holder.name.getText().toString();
                String res_add = holder.add.getText().toString();
                Intent in = new Intent(context,rdetails.class);
                in.putExtra("RESNAME",res_name);
                in.putExtra("RESADD",res_add);
            }
        });
    }

    @Override
    public int getItemCount() {
        return r_name.size();
    }
}
