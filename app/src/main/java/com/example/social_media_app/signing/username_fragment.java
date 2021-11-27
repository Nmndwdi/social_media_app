package com.example.social_media_app.signing;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentUsernameFragmentBinding;


public class username_fragment extends Fragment {
    FragmentUsernameFragmentBinding binding;

    public username_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentUsernameFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        login_fragment login_fragment=new login_fragment();
        signup_fragment signup_fragment=new signup_fragment();
        FragmentTransaction transaction=getParentFragmentManager().beginTransaction();
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("username",binding.username.getText().toString());
                bundle.putString("fullname",binding.fullname.getText().toString());
                signup_fragment.setArguments(bundle);
                transaction.replace(R.id.linear,signup_fragment)
                        .addToBackStack(null).
                        commit();
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.linear,login_fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}