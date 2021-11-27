package com.example.social_media_app.signing;

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
import com.example.social_media_app.databasing;
import com.example.social_media_app.databinding.FragmentSignupFragmentBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class signup_fragment extends Fragment {
    FragmentSignupFragmentBinding binding;

    public signup_fragment() {
        // Required empty public constructor
    }


        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentSignupFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
            login_fragment login_fragment=new login_fragment();
            FragmentTransaction transaction=getParentFragmentManager().beginTransaction();

            String username=getArguments().getString("username");
            String fullname=getArguments().getString("fullname");

            binding.login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    transaction.replace(R.id.linear,login_fragment)
                            .addToBackStack(null)
                            .commit();
                }
            });
            FirebaseAuth auth=FirebaseAuth.getInstance();
            binding.signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    auth.createUserWithEmailAndPassword(binding.Email.getText().toString(),binding.Password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getContext(), "user created successfully", Toast.LENGTH_SHORT).show();
                                databasing databasing=new databasing(username,fullname,binding.Email.getText().toString(),binding.Password.getText().toString());
                                databasing.create_account();
                                Intent intent=new Intent(getContext(), MainActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getContext(), "user cant be created,pls try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });

            GoogleSignInClient googleSignInClient =username_fragment.getInstance().create_request();
            binding.google.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    username_fragment.getInstance().signIn(googleSignInClient);
                }
            });

            return view;
    }




}