package com.example.latitude.tomato;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;


/**
 * Created by LATITUDE on 03-04-2018.
 */

public class Login extends Fragment {
    public Login(){}
    private FirebaseAuth mAuth;
    private ProgressBar Pb;
    public View onCreateView(LayoutInflater   layoutInflater  , ViewGroup con , Bundle SBI)
    {
        View view = layoutInflater.inflate(R.layout.login, con,false);
        final EditText uemail   = view.findViewById(R.id.email);
        final EditText upwd = view.findViewById(R.id.pass);
        Button signup = view.findViewById(R.id.signup);
        Button login = view.findViewById(R.id.log);
        Button google = view.findViewById(R.id.google);
        Pb = view.findViewById(R.id.loginpb);
        Pb.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();

        google.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),Signup.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail=uemail.getText().toString().trim();
                String userpassword=upwd.getText().toString().trim();
                // write your auth firebase code for login
                if(useremail==null){
                    uemail.setError("email is required");
                    uemail.requestFocus();
                    return;
                }
                if(userpassword==null){
                    upwd.setError("password is required");
                    upwd.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){
                    uemail.setError("Enter valid Email address");
                    uemail.requestFocus();
                    return;
                }
                if(userpassword.length()<6){
                    upwd.setError("password is too short");
                    upwd.requestFocus();
                    return;
                }
                Pb.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(useremail, userpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Pb.setVisibility(View.GONE);
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Str.User=user.getUid();
                                    Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    NavigationView navigationView = getActivity().findViewById(R.id.navi);
                                    Intent i = new Intent(getActivity(),main.class);
                                    startActivity(i);
                                    getActivity().finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    if(task.getException() instanceof FirebaseAuthInvalidUserException){
                                        Toast.makeText(getContext(), "User does not exist!", Toast.LENGTH_SHORT).show();
                                        uemail.requestFocus();
                                    }
                                    else if(task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                                    {
                                        Toast.makeText(getContext(), "Invalid credentials!", Toast.LENGTH_SHORT).show();
                                        upwd.requestFocus();
                                    }
                                    else {
                                        Toast.makeText(getContext(), "Some Error occurred during sign in", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        }
                        });
            }
        });
        return view;

    }
}
