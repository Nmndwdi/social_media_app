package com.example.social_media_app.signing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.social_media_app.MainActivity;
import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentLoginFragmentBinding;
import com.example.social_media_app.splash_activity;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class login_fragment extends Fragment {
    FragmentLoginFragmentBinding binding;
    FirebaseAuth auth;

    public login_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentLoginFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        username_fragment username_fragment=new username_fragment();
        FragmentTransaction transaction=getParentFragmentManager().beginTransaction();

        auth=FirebaseAuth.getInstance();

        binding.create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.linear,username_fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signInWithEmailAndPassword(binding.Email.getText().toString(),binding.Password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            SharedPreferences sharedPreferences= getContext().getSharedPreferences("gender_file",Context.MODE_PRIVATE);
                            if(sharedPreferences.contains("signin_with_google")) {
                                Toast.makeText(getContext(), "Login success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                FirebaseFirestore db=FirebaseFirestore.getInstance();
                                String userid=auth.getCurrentUser().getUid();
                                DocumentReference documentReference=db.collection("Boys").document(userid);
                                DocumentReference documentReference1=db.collection("Girls").document(userid);
                                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if(task.isSuccessful())
                                        {
                                            DocumentSnapshot document=task.getResult();
                                            if(document.exists())
                                            {
                                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                                editor.putString("gender_key","Girls");
                                                editor.putString("user_gender_key","Boys");
                                                editor.putBoolean("signin_with_google",true);
                                                editor.apply();
                                                Toast.makeText(getContext(), "Login success", Toast.LENGTH_SHORT).show();
                                                Intent intent=new Intent(getContext(),MainActivity.class);
                                                startActivity(intent);
                                            }
                                            else
                                            {
                                                documentReference1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                        if(task.isSuccessful())
                                                        {
                                                            DocumentSnapshot document=task.getResult();
                                                            if(document.exists())
                                                            {
                                                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                                                editor.putString("gender_key","Boys");
                                                                editor.putString("user_gender_key","Girls");
                                                                editor.putBoolean("signin_with_google",true);
                                                                editor.apply();
                                                                Toast.makeText(getContext(), "Login success", Toast.LENGTH_SHORT).show();
                                                                Intent intent=new Intent(getContext(),MainActivity.class);
                                                                startActivity(intent);
                                                            }
                                                            else
                                                            {
                                                                Toast.makeText(getContext(), "No user find with these credentials", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {

                                                    }
                                                });
                                            }
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        
                                    }
                                });
                            }
                        }
                        else
                        {
                            Toast.makeText(getContext(), "Error ! login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        GoogleSignInClient googleSignInClient=username_fragment.getInstance().create_request();
        binding.google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username_fragment.getInstance().signIn(googleSignInClient);
            }
        });
        return view;
    }
}