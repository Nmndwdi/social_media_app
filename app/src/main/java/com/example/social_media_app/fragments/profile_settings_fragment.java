package com.example.social_media_app.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.social_media_app.MainActivity;
import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentProfileSettingsFragmentBinding;
import com.example.social_media_app.signing.signup_activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile_settings_fragment extends BottomSheetDialogFragment {
    FragmentProfileSettingsFragmentBinding binding;
    int logout_count=0,delete_count=0;
    FirebaseAuth auth;
    FirebaseUser user;
    public profile_settings_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentProfileSettingsFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        auth=FirebaseAuth.getInstance();
        binding.profileLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(logout_count>=1)
                {
                    auth.signOut();
                    Intent intent=new Intent(getContext(), signup_activity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast toast=Toast.makeText(getContext(),"Click again to Logout",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    logout_count++;
                    Thread thread=new Thread()
                    {
                        @Override
                        public void run() {
                            try {
                                sleep(2000);
                                logout_count=0;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };thread.start();
                }
            }
        });
        binding.profileDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(delete_count>=1)
                {
                    user=auth.getCurrentUser();
                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getContext(), "Account deleted successfully", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getContext(), signup_activity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getContext(), "Error,user cant be deleted", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast toast=Toast.makeText(getContext(),"Click again to Delete Account",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    delete_count++;
                    Thread thread=new Thread()
                    {
                        @Override
                        public void run() {
                            try {
                                sleep(2000);
                                delete_count=0;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };thread.start();
                }
            }
        });
        return view;
    }
}