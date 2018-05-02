package com.example.latitude.tomato;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;

public class Total extends android.app.Fragment {
    ArrayList IT =new ArrayList();
    ArrayList Q =new ArrayList();

    public View onCreateView(LayoutInflater lf, ViewGroup vg, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String OID = Str.OID;
        final View view = lf.inflate(R.layout.display, vg, false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Orders").document(OID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                IT = (ArrayList)documentSnapshot.get("Items");
                Q = (ArrayList)documentSnapshot.get("Quantity");
                RecyclerView rv=view.findViewById(R.id.rdisplay);
                LinearLayoutManager lm=new LinearLayoutManager(getActivity());
                rv.setLayoutManager(lm);
                TA ta=new TA(view.getContext(),IT,Q);
                rv.setAdapter(ta);
            }
        });
        return  view;
    }
}
