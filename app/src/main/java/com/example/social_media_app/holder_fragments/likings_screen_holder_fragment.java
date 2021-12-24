package com.example.social_media_app.holder_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentLikingsScreenHolderFragmentBinding;
import com.example.social_media_app.fragments.likings_fragment;
import com.example.social_media_app.fragments.user_profile_fragment;

import java.util.Stack;

public class likings_screen_holder_fragment extends Fragment {

    Stack<Fragment>likings_screen_fragment_stack=new Stack<>();

    FragmentLikingsScreenHolderFragmentBinding binding;
    private static likings_screen_holder_fragment instance;

    public likings_screen_holder_fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentLikingsScreenHolderFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        likings_fragment likings_fragment=new likings_fragment();
        FragmentTransaction transaction=getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.likings_screen_holder,likings_fragment).commit();
        instance=this;
        return view;
    }

    public static likings_screen_holder_fragment getInstance()
    {
        return instance;
    }
    public void likings_screen_liking_fragment_push()
    {
        likings_fragment likings_fragment=new likings_fragment();
        likings_screen_fragment_stack.push(likings_fragment);
    }

    public int likings_fragment_stack_size()
    {
        int size=likings_screen_fragment_stack.size();
        return size;
    }

    public void likings_screen_pop()
    {
        Fragment fragment=likings_screen_fragment_stack.pop();
        FragmentTransaction transaction=getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.likings_screen_holder,fragment).commit();
    }

    public void likings_screen_user_profile_push()
    {
        user_profile_fragment user_profile_fragment=new user_profile_fragment();
        likings_screen_fragment_stack.push(user_profile_fragment);
    }

}