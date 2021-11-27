package com.example.social_media_app.holder_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentProfileHolderFragmentBinding;
import com.example.social_media_app.fragments.profile_fragment;

public class profile_holder_fragment extends Fragment {

    FragmentProfileHolderFragmentBinding binding;
    public profile_holder_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentProfileHolderFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        profile_fragment profile_fragment=new profile_fragment();
        FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.profile_holder,profile_fragment).commit();
        return view;
    }
}