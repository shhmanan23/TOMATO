package com.example.latitude.tomato;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class rdetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rdetails);
        Bundle b;
        b = getIntent().getExtras();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final String rest_id = b.getString("RESID");
        final ToggleButton TB = findViewById(R.id.fav);
        final TextView resname = findViewById(R.id.NAME);
        final TextView resadd = findViewById(R.id.AD);
        final TextView resrate = findViewById(R.id.RATING);
        final ImageView pic = findViewById(R.id.IMAGEVIEW);
        Button view = findViewById(R.id.VIEWMENU);
        resrate.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        TB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(Str.User==null){
                        Toast.makeText(getApplicationContext(), "Please Login first to add restaurants to favourites", Toast.LENGTH_SHORT).show();
                        TB.setChecked(false);
                    }else {
                        db.collection("users").document(Str.User).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                ArrayList favs = (ArrayList)documentSnapshot.get("Favourites");
                                favs.add(rest_id);
                                Map<String, ArrayList>h=new HashMap<>();
                                h.put("Favourites", favs);
                                db.collection("users").document(Str.User).set(h);
                                TB.setBackground(getDrawable(R.drawable.filled));
                            }
                        });
                    }
                }else{
                    if(Str.User!=null){
                        db.collection("users").document(Str.User).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                ArrayList favs = (ArrayList)documentSnapshot.get("Favourites");
                                favs.remove(rest_id);
                                Map<String, ArrayList>h=new HashMap<>();
                                h.put("Favourites", favs);
                                db.collection("users").document(Str.User).set(h);
                                TB.setBackground(getDrawable(R.drawable.empty));
                            }
                        });
                    }
                }
            }
        });
        Button rate = findViewById(R.id.REVIEW);


        if (Str.User!=null){
            db.collection("users").document(Str.User).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    ArrayList favs = (ArrayList)documentSnapshot.get("Favourites");
                    if(favs.contains(rest_id)){
                        TB.setChecked(true);
                        TB.setBackground(getDrawable(R.drawable.filled));
                    }
                }
            });
        }
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