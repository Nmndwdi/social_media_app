package com.example.social_media_app.holder_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentSearchScreenHolderFragmentBinding;
import com.example.social_media_app.fragments.Search_fragment;
import com.example.social_media_app.fragments.user_profile_fragment;

import java.nio.file.attribute.FileTime;
import java.util.Stack;

public class search_screen_holder_fragment extends Fragment {

    FragmentSearchScreenHolderFragmentBinding binding;
    Stack<Fragment>seach_screen_fragment_stack=new Stack<>();
    private static search_screen_holder_fragment instance;
    public search_screen_holder_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentSearchScreenHolderFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();

        Search_fragment search_fragment=new Search_fragment();
        FragmentTransaction transaction=getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.search_screen_holder,search_fragment).commit();
        instance=this;
        return view;
    }
    public static search_screen_holder_fragment getInstance()
    {
        return instance;
    }
    public void search_screen_search_fragment_push()
    {
        Search_fragment search_fragment=new Search_fragment();
        seach_screen_fragment_stack.push(search_fragment);
    }
    public int search_screen_stack_size()
    {
        int size=seach_screen_fragment_stack.size();
        return size;
    }
    public void search_screen_pop()
    {
        Fragment fragment=seach_screen_fragment_stack.pop();
        FragmentTransaction transaction=getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.search_screen_holder,fragment).commit();
    }
    public void search_screen_user_profile_fragment_push()
    {
        user_profile_fragment user_profile_fragment=new user_profile_fragment();
        seach_screen_fragment_stack.push(user_profile_fragment);
    }
}