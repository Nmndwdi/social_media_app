package com.example.social_media_app.signing;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.social_media_app.MainActivity;
import com.example.social_media_app.R;
import com.example.social_media_app.databasing.databasing_write;
import com.example.social_media_app.databinding.FragmentGoogleSignInInfoFragmentBinding;

public class google_sign_in_info_fragment extends Fragment {

    FragmentGoogleSignInInfoFragmentBinding binding;
    String gender="";

    public google_sign_in_info_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentGoogleSignInInfoFragmentBinding.inflate(inflater, container, false);
        View view =binding.getRoot();

        String userid= getArguments().getString("userid");
        String email= getArguments().getString("email");

        binding.proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=binding.username.getText().toString();
                String fullname=binding.fullname.getText().toString();
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
                databasing_write databasing_write=new databasing_write(username,fullname,email,gender,userid,age);
                databasing_write.create_account_google();
                Intent intent=new Intent(getContext(), MainActivity.class);
                intent.putExtra("gender_key",gender);
                startActivity(intent);
            }
        });

        return view;
    }
}