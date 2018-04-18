package com.example.latitude.tomato;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;



import java.util.ArrayList;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Bundle b=getIntent().getExtras();
        Button order = (Button)findViewById(R.id.button2);
        boolean [] B=b.getBooleanArray("bestseller_check");
        for (boolean b1 : B) {
            String s = b1? "true":"false";
            Toast.makeText(this, s,Toast.LENGTH_SHORT).show();
        }

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Noti();

            }
        });
    }

    public void Noti(){
        RemoteViews remoteViews = new RemoteViews(getPackageName(),
                R.layout.customnotification);

        // Set Notification Title
        String strtitle = getString(R.string.customnotificationtitle);
        // Set Notification Text
        String strtext = getString(R.string.customnotificationtext);

        // Open NotificationView Class on Notification Click
        Intent intent = new Intent(this, Notification.class);
        // Send data to NotificationView Class
        intent.putExtra("title", strtitle);
        intent.putExtra("text", strtext);
        // Open Notification.java Activity
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                // Set Icon
                .setSmallIcon(R.drawable.tom)
                // Set Ticker Message
                .setTicker(getString(R.string.customnotificationticker))
                // Dismiss Notification
                .setAutoCancel(true)
                // Set PendingIntent into Notification
                .setContentIntent(pIntent)
                // Set RemoteViews into Notification
                .setContent(remoteViews);

        // Locate and set the Image into customnotificationtext.xml ImageViews
        //remoteViews.setImageViewResource(R.id.imagenotileft,R.drawable.ic_launcher);
        remoteViews.setImageViewResource(R.id.tom,R.drawable.tom);

        // Locate and set the Text into customnotificationtext.xml TextViews
        remoteViews.setTextViewText(R.id.n_title,getString(R.string.customnotificationtitle));
        remoteViews.setTextViewText(R.id.n_details,getString(R.string.customnotificationtext));

        // Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Build Notification with Notification Manager
        notificationmanager.notify(0, builder.build());
    }
}
