package com.example.latitude.tomato;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.PatternMatcher;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class Signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText uemail;
    private EditText upwd;
    private EditText name;
    private ProgressBar Pb;
    private ImageView I;
    private Uri ProfileImage;
    private String Name;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == RESULT_OK && data != null && data.getData() !=null){
            ProfileImage = data.getData();
            try {
                Bitmap bmp= MediaStore.Images.Media.getBitmap(getContentResolver(), ProfileImage);
                I.setImageBitmap(bmp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button confirm;
        uemail =  (EditText)findViewById(R.id.Uemail);
        upwd =  (EditText)findViewById(R.id.Upwd);
        confirm =  (Button) findViewById(R.id.confirm);
        name = findViewById(R.id.name);
        Pb = (ProgressBar) findViewById(R.id.progress);
        Pb.setVisibility(View.GONE);
        I = findViewById(R.id.upload);
        I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I=new Intent();
                I.setType("image/*");
                I.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(I,"Select profile image"), 101);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail  = uemail.getText().toString().trim();
                String userpassword = upwd.getText().toString().trim();
                Name = name.getText().toString().trim();
                if(Name==null){
                    name.setError("name is required");
                    name.requestFocus();
                    return;
                }
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
                mAuth = FirebaseAuth.getInstance();
                Pb.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(useremail, userpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Pb.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    UserProfileChangeRequest profileUpdates;
                                    if(ProfileImage!=null) {
                                        profileUpdates = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(Name)
                                                .setPhotoUri(ProfileImage)
                                                .build();
                                    }
                                    else{
                                        profileUpdates = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(Name)
                                                .build();
                                    }

                                    user.updateProfile(profileUpdates)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                                                        finish();
                                                        startActivity(new Intent(Signup.this, main.class));
                                                    }
                                                    else{
                                                        Toast.makeText(getApplicationContext(), "Error!!!", Toast.LENGTH_SHORT).show();

                                                    }
                                                }
                                            });
                                } else {
                                    // If sign in fails, display a message to the user.
                                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                        Toast.makeText(getApplicationContext(), "User already exists!", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    uemail.setText(null, null);
                                    upwd.setText(null, null);
                                }

                                // ...
                            }
                        });
            }
        });

    }
}
