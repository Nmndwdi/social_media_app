package com.example.social_media_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.social_media_app.databinding.ActivitySplashBinding;
import com.example.social_media_app.signing.signup_activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class splash_activity extends AppCompatActivity {

    ActivitySplashBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onStart() {
        super.onStart();
        Thread thread=new Thread()
        {
            public void run()
            {
                try {
                    sleep(1000);
                    auth = FirebaseAuth.getInstance();
                    FirebaseUser user = auth.getCurrentUser();
                    if (user != null) {
                        SharedPreferences sharedPreferences=getSharedPreferences("gender_file",MODE_PRIVATE);
                        if(sharedPreferences.contains("signin_with_google")){
                            Intent intent=new Intent(splash_activity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent=new Intent(splash_activity.this,signup_activity.class);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        Intent intent = new Intent(splash_activity.this, signup_activity.class);
                        startActivity(intent);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

}