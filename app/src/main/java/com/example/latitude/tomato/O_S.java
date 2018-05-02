package com.example.latitude.tomato;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;

public class O_S extends AppCompatActivity {
    TextView amount, ordernum , rest , dt, status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o__s);
        Bundle b = getIntent().getExtras();
        final String OID = b.getString("orderid");
        Str.OID = OID;
        amount = findViewById(R.id.total);
        ordernum = findViewById(R.id.orderid);
        rest = findViewById(R.id.restname);
        dt = findViewById(R.id.datetime);
        status = findViewById(R.id.status1);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Orders").document(OID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                amount.setText(documentSnapshot.getString("total"));
                dt.setText(DateFormat.getInstance().format(documentSnapshot.getDate("time")));
                ordernum.setText(OID);
                rest.setText(documentSnapshot.getString("R_Name"));
                int x = Integer.parseInt(documentSnapshot.getString("status"));
                switch (x){
                    case 0:
                        status.setText("Your order has been placed");
                        break;
                    case 1:
                        status.setText("Your order is being prapared");
                        break;
                    case 2:
                        status.setText("Your order is being delivered");
                        break;
                    case 3:
                        status.setText("Your order is delivered successfully");
                        break;
                }
            }
        });
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.frag,new Total());
    //    set text for text view
    }
}
