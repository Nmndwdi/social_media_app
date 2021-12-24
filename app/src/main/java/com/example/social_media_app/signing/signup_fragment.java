package com.example.social_media_app.signing;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.social_media_app.MainActivity;
import com.example.social_media_app.R;
import com.example.social_media_app.databasing.databasing_write;
import com.example.social_media_app.databinding.FragmentSignupFragmentBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class signup_fragment extends Fragment {
    FragmentSignupFragmentBinding binding;
    String gender="";

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
                    String email=binding.Email.getText().toString();
                    String password=binding.Password.getText().toString();
                    String fullname=binding.fullname.getText().toString();
                    String username=binding.username.getText().toString();
                    int age= Integer.parseInt(binding.age.getText().toString());
                    int checked_gender_id=binding.gender.getCheckedRadioButtonId();
                    String st_gen=Integer.toString(checked_gender_id);
                    if(st_gen.equals("1"))
                    {
                        gender="Boys";
                    }
                    else if(st_gen.equals("2"))
                    {
                        gender="Girls";
                    }
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getContext(), "user created successfully", Toast.LENGTH_SHORT).show();
                                FirebaseUser user=auth.getCurrentUser();
                                String userid=user.getUid();
                                databasing_write databasing_write=new databasing_write(username,fullname,email,password,gender,age,userid);
                                databasing_write.create_account();
                                Intent intent=new Intent(getContext(), MainActivity.class);
                                intent.putExtra("gender_key",gender);
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

//            GoogleSignInClient googleSignInClient =username_fragment.getInstance().create_request();
//            binding.google.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    username_fragment.getInstance().signIn(googleSignInClient);
//                }
//            });

            return view;
    }



}
