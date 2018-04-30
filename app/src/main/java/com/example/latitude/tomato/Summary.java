package com.example.latitude.tomato;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.latitude.tomato.NotificationUtils.ANDROID_CHANNEL_ID;

public class Summary extends AppCompatActivity implements OrderAdapter.total{
    private NotificationUtils mNotificationUtils;
    RecyclerView R1;
    long total=0;
    static TextView tot;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Button order = findViewById(R.id.button2);
        tot = findViewById(R.id.tot);
        Bundle B_order = getIntent().getExtras();
        ArrayList b_name = B_order.getStringArrayList("bestseller_name");
        ArrayList c_name = B_order.getStringArrayList("continental_name");
        ArrayList i_name = B_order.getStringArrayList("indian_name");
        ArrayList b_price = B_order.getStringArrayList("bestseller_price");
        ArrayList c_price= B_order.getStringArrayList("continental_price");
        ArrayList i_price= B_order.getStringArrayList("indian_price");
        ArrayList b_check = (ArrayList)B_order.get("bestseller_check");
        ArrayList c_check= (ArrayList)B_order.get("continental_check");
        ArrayList i_check= (ArrayList)B_order.get("indian_check");
        final ArrayList ans_name=new ArrayList();
        final ArrayList ans_price=new ArrayList();
        if(b_name!=null) {
            if (c_name != null) {
                b_name.addAll(c_name);
                b_price.addAll(c_price);
                b_check.addAll(c_check);
            }
            if (i_name != null) {
                b_name.addAll(i_name);
                b_price.addAll(i_price);
                b_check.addAll(i_check);
            }
        }else{
            if(c_name!=null){
                if(i_name!=null){
                    c_name.addAll(i_name);
                    c_price.addAll(i_price);
                    c_check.addAll(i_check);
                }
                b_name=c_name;
                b_price=c_price;
                b_check=c_check;
            }
            else {
                b_name=i_name;
                b_price=i_price;
                b_check=i_check;
            }
        }
        ans_name.clear();
        ans_price.clear();
        for (int i = 0; i < b_check.size(); i++) {
            if((boolean)b_check.get(i)){
                ans_name.add(b_name.get(i));
                ans_price.add(b_price.get(i));
            }
        }

        R1 = findViewById(R.id.order_details);

        LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext());
        R1.setLayoutManager(lm);
        OrderAdapter OD = new OrderAdapter(this,ans_name,ans_price);
        R1.setAdapter(OD);




        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                if(firebaseAuth.getCurrentUser()==null){
                    Toast.makeText(getApplicationContext(), "Please login first to complete the order", Toast.LENGTH_SHORT).show();
                }
                else{
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                    Map <String, Object>h = new HashMap();
                    h.put("item", ans_name);
                    //To be added
                    db.collection("Orders").add(h).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            send("TOMATO ORDER DETAILS","Dear customer your order has been successfully placed. Enjoy your meal.");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "error in placing order", Toast.LENGTH_SHORT).show();
                        }
                    });
            }}
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public  void  send(String title , String message){

        mNotificationUtils = new NotificationUtils(this);
        Notification.Builder nb = mNotificationUtils.getAndroidChannelNotification(title, message);
        mNotificationUtils.getManager().notify(101,nb.build());


    }

    @Override
    public void Total(ArrayList amt) {
        total = 0;
        for (Object o : amt) {
            total += (long)o;
        }
        tot.setText("Total :"+total);
    }
}
