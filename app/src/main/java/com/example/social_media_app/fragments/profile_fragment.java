package com.example.social_media_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.social_media_app.Adapters.profile_adapter_recyclerview;
import com.example.social_media_app.Adapters.user_profile_adapter_recyclerview;
import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentProfileFragmentBinding;
import com.example.social_media_app.model_classes.profile_model_class;
import com.example.social_media_app.model_classes.user_profile_model_class;

import java.util.ArrayList;

public class profile_fragment extends Fragment {

    FragmentProfileFragmentBinding binding;
    ArrayList<profile_model_class> arrayList=new ArrayList<>();
    public profile_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentProfileFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();

        binding.profileRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        profile_adapter_recyclerview profile_adapter_recyclerview=new profile_adapter_recyclerview(getContext(),this.arrayList);
        binding.profileRecyclerview.setAdapter(profile_adapter_recyclerview);

        return view;
    }
}