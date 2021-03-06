package com.example.latitude.tomato;

import android.content.Intent;
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
    NavigationView nv;
    android.support.v4.app.FragmentTransaction ft;
   // ArrayList r_image = new ArrayList<>(Arrays.asList(R.drawable.r1,R.drawable.r2));
    //ArrayList r_name = new ArrayList<>(Arrays.asList("Blue Roof Top","I love Sandwich House","avc"));
    //ArrayList r_add = new ArrayList<>(Arrays.asList("4.5","3","7"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.recycle);

        LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(lm);
        CustomAdapter ca = new CustomAdapter(main.this, true);
        rv.setAdapter(ca);



        mDrawerlayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);

        mtoolbar = findViewById(R.id.n_action);
        setSupportActionBar(mtoolbar);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();

        nv = findViewById(R.id.navi);
        View hv = nv.getHeaderView(0);
        profilepic = hv.findViewById(R.id.ppic);
        nav_email = hv.findViewById(R.id.nav_email);
        nav_name = hv.findViewById(R.id.nav_Name);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerlayout.closeDrawers();
                switch (item.getItemId())
                {
                    case R.id.home:
                        //Code for home fragment
                        Intent i = new Intent(main.this,main.class);
                        startActivity(i);
                        getSupportActionBar().setTitle("Home");
                        item.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;

                    case R.id.login:
                        if(user==null){
                        rv.setVisibility(View.GONE);
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.main,new Login());
                        ft.commit();
                        getSupportActionBar().setTitle("LOGIN");
                        item.setChecked(true);}
                        else{
                            mAuth.signOut();
                            item.setTitle("Log in");
                            Str.User=null;
                            startActivity(new Intent(main.this, main.class));
                            finish();
                        }
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
                    case R.id.nearby:
                        rv.setVisibility(View.GONE);
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.main,new Near());
                        ft.commit();
                        getSupportActionBar().setTitle("NEAR BY RESTAURANT");
                        item.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;
                    case R.id.about:
                        rv.setVisibility(View.GONE);
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.main,new About());
                        ft.commit();
                        getSupportActionBar().setTitle("ABOUT US");
                        item.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;
                    case R.id.history:
                        rv.setVisibility(View.GONE);
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.main,new History());
                        ft.commit();
                        getSupportActionBar().setTitle("ORDER HISTORY");
                        item.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;

                }
                return false;
            }
        });


    }

    @Override
    protected void onStart(){
        super.onStart();
        if(user==null) {
            Toast.makeText(getApplicationContext(), "please login", Toast.LENGTH_SHORT).show();
        }
        else{
            Str.User=user.getUid();
            Toast.makeText(getApplicationContext(), user.getEmail(), Toast.LENGTH_SHORT).show();
            nv.getMenu().getItem(1).setTitle("Log out");
            if(user.getPhotoUrl()!=null){
                Glide.with(this).load(user.getPhotoUrl()).into(profilepic);
            }
            if(user.getDisplayName()!=null){
                nav_name.setText("welcome, "+user.getDisplayName());
            }
            nav_email.setText(user.getEmail());
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){

        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
