package com.example.social_media_app.holder_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentMainScreenHolderFragmentBinding;
import com.example.social_media_app.fragments.chat_detail_fragment;
import com.example.social_media_app.fragments.main_screen_fragment;
import com.example.social_media_app.fragments.user_profile_fragment;

import java.util.Stack;

public class main_screen_holder_fragment extends Fragment {
    FragmentMainScreenHolderFragmentBinding binding;
    Stack<Fragment>main_screen_fragment_stack=new Stack<>();
    private static main_screen_holder_fragment instance;
    public main_screen_holder_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentMainScreenHolderFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();

        main_screen_fragment main_screen_fragment=new main_screen_fragment();
        FragmentTransaction transaction=getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.main_screen_holder,main_screen_fragment).commit();
        instance=this;
        return view;
    }
    public static main_screen_holder_fragment getInstance()
    {
        return instance;
    }
    public void main_screen_main_fragment_push()
    {
        main_screen_fragment main_screen_fragment=new main_screen_fragment();
        main_screen_fragment_stack.push(main_screen_fragment);
    }
    public int main_screen_stack_size()
    {
        int size=main_screen_fragment_stack.size();
        return size;
    }
    public void main_screen_pop()
    {
        Fragment fragment=main_screen_fragment_stack.pop();
        FragmentTransaction transaction=getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.main_screen_holder,fragment).commit();
    }
    public void main_screen_user_profile_fragment_push()
    {
        user_profile_fragment user_profile_fragment=new user_profile_fragment();
        main_screen_fragment_stack.push(user_profile_fragment);
    }
}