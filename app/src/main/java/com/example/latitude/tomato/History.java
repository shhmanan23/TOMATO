package com.example.latitude.tomato;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class History extends Fragment {
    public History(){}

    public View onCreateView(LayoutInflater layoutInflater  , ViewGroup con , Bundle SBI) {
        View v = layoutInflater.inflate(R.layout.fav,con,false);
        final ArrayList r_id = new ArrayList();
        final ArrayList o_sum = new ArrayList();
        final ArrayList o_time = new ArrayList();
        final ArrayList o_id = new ArrayList();
        final RecyclerView F_rv = v.findViewById(R.id.fav_recycle);
        if(Str.User!=null){
            final FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("Orders").whereEqualTo("user", Str.User).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                        r_id.add(queryDocumentSnapshot.getString("R_Name"));
                        o_sum.add(queryDocumentSnapshot.get("total"));
                        o_time.add(DateFormat.getInstance().format(queryDocumentSnapshot.getDate("time")));
                        o_id.add(queryDocumentSnapshot.getId());
                    }
                    if(o_id.size()==0) {Toast.makeText(getContext(), "No order history", Toast.LENGTH_SHORT).show();}
                    else{
                        LinearLayoutManager lm1 = new LinearLayoutManager(getActivity().getApplicationContext());
                    F_rv.setLayoutManager(lm1);
                    HistoryAdapter HA = new HistoryAdapter(getActivity().getApplicationContext(),r_id,o_time,o_sum, o_id);
                    //HistoryAdapter HA = new HistoryAdapter(getActivity().getApplicationContext());
                    F_rv.setAdapter(HA);}
                }
            });
        }
        else {
            Toast.makeText(getActivity(), "Please Login first to view your orders", Toast.LENGTH_SHORT).show();
        }

        return  v;
    }
}
