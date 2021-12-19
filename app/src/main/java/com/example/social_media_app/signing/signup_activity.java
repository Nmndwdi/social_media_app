package com.example.social_media_app.signing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.social_media_app.MainActivity;
import com.example.social_media_app.R;
import com.example.social_media_app.databinding.ActivitySignupBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.concurrent.atomic.AtomicLong;

public class signup_activity extends AppCompatActivity {
    ActivitySignupBinding binding;
    FirebaseAuth auth;
    int backButtonCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //getSupportActionBar().hide();
        username_fragment username_fragment = new username_fragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.linear, username_fragment);
        transaction.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String userid = user.getUid();

            String email = "";
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
            if (account != null) {
                email = account.getEmail();
            }
            google_sign_in_info_fragment google_sign_in_info_fragment = new google_sign_in_info_fragment();
            Bundle bundle = new Bundle();
            bundle.putString("userid", userid);
            bundle.putString("email", email);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            google_sign_in_info_fragment.setArguments(bundle);
            transaction.replace(R.id.linear, google_sign_in_info_fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (backButtonCount >= 1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            backButtonCount = 0;
            startActivity(intent);
        } else {
            Toast.makeText(signup_activity.this, "Press again to exit", Toast.LENGTH_SHORT).show();
            backButtonCount++;
            Thread thread=new Thread()
            {
                public void run()
                {
                    try {
                        sleep(2000);
                        backButtonCount=0;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }
}