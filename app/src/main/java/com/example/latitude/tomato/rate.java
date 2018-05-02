package com.example.latitude.tomato;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class rate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        Bundle B = getIntent().getExtras();
        final String rest_id = B.getString("RESID");

        final RatingBar rb = findViewById(R.id.ratingBar);
        final EditText e1 = findViewById(R.id.writereview);
        TextView t1 = findViewById(R.id.restn);
        Button b1 = findViewById(R.id.post);
        final ProgressBar pb = findViewById(R.id.progressBarRate);
        rb.setMax(5);
        rb.setStepSize(1);
        rb.setNumStars(5);
        t1.setText("Enter your review");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                if(user!=null){
                    pb.setVisibility(View.VISIBLE);
                String uname = user.getDisplayName();
                float F = rb.getRating(); // rating
                String opi = e1.getText().toString(); // the written opinion of users

                // write firebase code to save review of a particular restaurant
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String, Object> m = new HashMap<>();
                m.put("user", uname);
                m.put("Rating", F);
                m.put("Review", opi);
                m.put("Time", new Date());
                m.put("restaurant", rest_id);
                db.collection("Ratings").add(m).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Your review have been saved successfully", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(rate.this, rdetails.class);
                            in.putExtra("RESID", rest_id);
                            startActivity(in);
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Error rating the restaurant", Toast.LENGTH_SHORT).show();
                        }
                        pb.setVisibility(View.GONE);
                    }
                });
            }
            else {
                    Toast.makeText(getApplicationContext(),"Please login to rate this restaurant", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
