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
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("Boys").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String uid = document.getId();
                            if (userid.equals(uid)) {
                                Intent intent = new Intent(signup_activity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    } else {
                        Log.d("Error", "Error during gettin document");
                    }
                }
            });
            db.collection("Girls").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String uid = document.getId();
                            if (userid.equals(uid)) {
                                Intent intent = new Intent(signup_activity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    } else {
                        Log.d("Error", "Error during gettin document");
                    }
                }
            });

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
}