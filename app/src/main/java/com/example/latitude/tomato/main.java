package com.example.latitude.tomato;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;


public class main extends AppCompatActivity {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private RecyclerView rv;
    private Toolbar mtoolbar;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private ImageView profilepic;
    private TextView nav_name;
    private TextView nav_email;
   // android.support.v4.app.FragmentTransaction ft;
    android.support.v4.app.FragmentTransaction ft;
    ArrayList r_image = new ArrayList<>(Arrays.asList(R.drawable.r1,R.drawable.r2));
    ArrayList r_name = new ArrayList<>(Arrays.asList("Blue Roof Top","I love Sandwhich House"));
    ArrayList r_add = new ArrayList<>(Arrays.asList("4.5","3"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView)findViewById(R.id.recycle);
        LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(lm);
        CustomAdapter ca = new CustomAdapter(main.this,r_image,r_name,r_add);
        rv.setAdapter(ca);



        mDrawerlayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);

        mtoolbar = (Toolbar)findViewById(R.id.n_action);
        setSupportActionBar(mtoolbar);
        mDrawerlayout.addDrawerListener(mToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToggle.syncState();

        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.main, new Login());
        ft.commit();
        getSupportActionBar().setTitle("LOGIN");

        NavigationView nv =  (NavigationView)findViewById(R.id.navi);
        profilepic = findViewById(R.id.ppic);
        nav_email = findViewById(R.id.nav_email);
        nav_name = findViewById(R.id.nav_Name);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            RecyclerView rv = (RecyclerView)findViewById(R.id.recycle);
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerlayout.closeDrawers();
                switch (item.getItemId())
                {
                    case R.id.login:
                        //Toast.makeText(getApplicationContext(), "5absv", Toast.LENGTH_SHORT).show();

                        rv.setVisibility(View.GONE);
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.main,new Login());
                        ft.commit();
                        getSupportActionBar().setTitle("LOGIN");
                        item.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;
                    case R.id.fav:

                        rv.setVisibility(View.GONE);
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.main,new Fav());
                        ft.commit();
                        getSupportActionBar().setTitle("MY FAVOURITES");
                        item.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;

                }
                return false;
            }
        });


    }

    @Override
   /* protected void onStart(){
        super.onStart();
        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        //nav_email.setText(user.getEmail().toString());
        Toast.makeText(getApplicationContext(), user.getPhotoUrl().toString(), Toast.LENGTH_SHORT).show();

        *//*if (user!=null){
            if(user.getPhotoUrl()!=null){
                Toast.makeText(getApplicationContext(), user.getPhotoUrl().toString(), Toast.LENGTH_SHORT).show();
                //Glide.with(this).load(user.getPhotoUrl()).into(profilepic);
            }
            if(user.getDisplayName()!=null){
                nav_name.setText("welcome, "+user.getDisplayName());
            }
            nav_email.setText(user.getEmail());
        }*//*
    }
*/
    public boolean onOptionsItemSelected(MenuItem item){

        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
