package com.example.social_media_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.social_media_app.Adapters.chats_show_adapter_recyclerview;
import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentChatsFragmentBinding;
import com.example.social_media_app.model_classes.chat_screen_model_class;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class chats_fragment extends BottomSheetDialogFragment {

    FragmentChatsFragmentBinding binding;
    ArrayList<chat_screen_model_class>arrayList=new ArrayList<>();
    public chats_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentChatsFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());
        arrayList.add(new chat_screen_model_class());

        binding.chatsRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.chatsRecyclerview.setAdapter(new chats_show_adapter_recyclerview(getContext(),this.arrayList));
        return view;
    }
}