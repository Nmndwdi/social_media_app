package com.example.social_media_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.social_media_app.Adapters.search_screen_adapter_recyclerview;
import com.example.social_media_app.Adapters.user_profile_adapter_recyclerview;
import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentUserProfileFragmentBinding;
import com.example.social_media_app.model_classes.user_profile_model_class;

import java.util.ArrayList;


public class user_profile_fragment extends Fragment {
    FragmentUserProfileFragmentBinding binding;
    ArrayList<user_profile_model_class>arrayList=new ArrayList<>();
    public user_profile_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentUserProfileFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();

        binding.userProfileRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        arrayList.add(new user_profile_model_class());
        user_profile_adapter_recyclerview user_profile_adapter_recyclerview=new user_profile_adapter_recyclerview(getContext(),this.arrayList);
        binding.userProfileRecyclerview.setAdapter(user_profile_adapter_recyclerview);
        return view;
    }
}