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

import java.util.Stack;

public class profile_holder_fragment extends Fragment {

    FragmentProfileHolderFragmentBinding binding;
    Stack<Fragment>profile_fragment_stack=new Stack<>();
    private static profile_holder_fragment instance;

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
        instance=this;
        return view;
    }
    public static profile_holder_fragment getInstance()
    {
        return instance;
    }
    public void profile_screen_profile_fragment_push()
    {
        profile_fragment profile_fragment=new profile_fragment();
        profile_fragment_stack.push(profile_fragment);
    }
    public int profile_screen_stack_size()
    {
        int size=profile_fragment_stack.size();
        return size;
    }
    public void profile_screen_pop()
    {
        Fragment fragment=profile_fragment_stack.pop();
        FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.profile_holder,fragment).commit();
    }
}