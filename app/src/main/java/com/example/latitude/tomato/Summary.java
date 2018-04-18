package com.example.latitude.tomato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Intent intent = getIntent();
        boolean b[]=intent.getBooleanArrayExtra("bestseller_check");
        /*Bundle b=intent.getExtras();
        ArrayList a=b.getStringArrayList("bestseller_name");
        for (Object o : a) {
            Toast.makeText(this, ((String) o),Toast.LENGTH_SHORT).show();
        }*/
        /*boolean B[]=b.getBooleanArray("bestseller_check");*/
        for (boolean b1 : b) {
            String s = b1? "true":"false";
            Toast.makeText(this, s,Toast.LENGTH_SHORT).show();
        }
    }
}
