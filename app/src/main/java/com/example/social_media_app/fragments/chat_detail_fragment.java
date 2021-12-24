package com.example.social_media_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentChatDetailFragmentBinding;

public class chat_detail_fragment extends Fragment {

    FragmentChatDetailFragmentBinding binding;

    public chat_detail_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentChatDetailFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();

        return view;
    }
}