package com.example.social_media_app.signing;

import android.content.Context;
import android.content.Intent;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class login_fragment extends Fragment {
    FragmentLoginFragmentBinding binding;
    FirebaseAuth auth;

    FirebaseFirestore db;

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
        db=FirebaseFirestore.getInstance();

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
                            String userid=auth.getCurrentUser().getUid();
                            DocumentReference documentReference=db.collection("Boys").document(userid);
                            DocumentReference documentReference1=db.collection("Girls").document(userid);
                            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if(task.isSuccessful())
                                    {
                                        DocumentSnapshot document= task.getResult();
                                        if(document.exists())
                                        {
                                            String gender= document.getString("gender");
                                            Toast.makeText(getContext(), "login success", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getContext(), MainActivity.class);
                                            intent.putExtra("gender_key",gender);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            documentReference1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if(task.isSuccessful())
                                                    {
                                                        DocumentSnapshot document= task.getResult();
                                                        if(document.exists())
                                                        {
                                                            String gender= document.getString("gender");
                                                            Toast.makeText(getContext(), "login success", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(getContext(), MainActivity.class);
                                                            intent.putExtra("gender_key",gender);
                                                            startActivity(intent);
                                                        }
                                                        else
                                                        {

                                                        }
                                                    }
                                                    else
                                                    {
                                                        Log.d("Error", "Error during gettin document");
                                                    }
                                                }
                                            });
                                        }
                                    }
                                    else
                                    {
                                        Log.d("Error", "Error during gettin document");
                                    }
                                }
                            });
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