package com.example.latitude.tomato;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Notification extends AppCompatActivity {
    TextView txttitle, txttext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Dismiss Notification
        notificationmanager.cancel(0);

        Intent i = getIntent();

       String title = i.getStringExtra("title");
        String text  = i.getStringExtra("text");

        // Locate the TextView
        txttitle = (TextView) findViewById(R.id.nv_title);
        txttext = (TextView) findViewById(R.id.nv_details);

        // Set the data into TextView
        txttitle.setText(title);
        txttext.setText(text);
    }
}
