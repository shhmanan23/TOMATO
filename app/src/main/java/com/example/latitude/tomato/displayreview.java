package com.example.latitude.tomato;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;

public class displayreview extends Fragment {

    ArrayList username=new ArrayList();

    ArrayList rev =new ArrayList();

    ArrayList ratingss =new ArrayList();

    public View onCreateView(LayoutInflater lf, ViewGroup vg, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view=lf.inflate(R.layout.display,vg,false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Ratings").whereEqualTo("restaurant", Str.restaurant)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                    String rating=queryDocumentSnapshot.get("Rating").toString();
                    String review=(String)queryDocumentSnapshot.get("Review");
                    String user = (String)queryDocumentSnapshot.get("user");
                    username.add(user);
                    rev.add(review);
                    ratingss.add(rating);
                }
                RecyclerView rv=view.findViewById(R.id.rdisplay);
                LinearLayoutManager lm=new LinearLayoutManager(getActivity());
                rv.setLayoutManager(lm);
                DA da=new DA(view.getContext(),username,rev,ratingss);
                rv.setAdapter(da);
            }
        });
        return view;
    }
}