package com.example.latitude.tomato;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class rdetails extends AppCompatActivity {
    ToggleButton TB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rdetails);
        Bundle b;
        b = getIntent().getExtras();
        final String rest_id = b.getString("RESID");
        //String rest_add = b.getString("RESADD");

        final TextView resname = findViewById(R.id.NAME);
        final TextView resadd = findViewById(R.id.AD);
        final TextView resrate = findViewById(R.id.RATING);
        final ImageView pic = findViewById(R.id.IMAGEVIEW);
        Button view = findViewById(R.id.VIEWMENU);

       // TB.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pink));
        Button rate = findViewById(R.id.REVIEW);
        //resname.setText(rest_name);
        //resadd.setText(rest_add);
        // write code to get data of address/rating from firebase
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docref = db.collection("Restaurants").document(rest_id);
        docref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                resname.setText((String)documentSnapshot.get("Name"));
                resadd.setText((String)documentSnapshot.get("Address"));
                resrate.setText(documentSnapshot.get("AvgRating").toString());
                StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("Restaurants/"+documentSnapshot.get("photo").toString());
                storageRef.getBytes(2048000).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        pic.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                    }
                });
            }
        });


        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        displayreview c=new displayreview();
        ft.replace(R.id.fragment,new displayreview());
        ft.commit();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(rdetails.this, viewmenu.class);
                in.putExtra("RESID",rest_id);
                startActivity(in);

            }
        });
        //TB.setOnCheckedChangeListener(new C);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent in = new Intent(rdetails.this, rate.class);
                in.putExtra("RESID",rest_id);
                startActivity(in);


            }
        });
    }
}