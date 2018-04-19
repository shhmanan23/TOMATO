package com.example.latitude.tomato;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class rate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        Bundle B = new Bundle();
        B = getIntent().getExtras();
        String n1 = B.getString("R");


        final RatingBar rb = (RatingBar)findViewById(R.id.ratingBar);
        final EditText e1 = (EditText)findViewById(R.id.writereview);
        TextView t1 = (TextView)findViewById(R.id.restn);
        Button b1 = (Button)findViewById(R.id.post);
        rb.setMax(5);
        rb.setStepSize(1);
        rb.setNumStars(5);
        t1.setText(n1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float F = rb.getRating(); // rating
                String opi = e1.getText().toString(); // the written opinion of users

                // write firebase code to save review of a particular restaurant
            }
        });
    }
}
