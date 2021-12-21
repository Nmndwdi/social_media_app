package com.example.social_media_app.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.social_media_app.Adapters.main_screen_adapter_recyclerview;
import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentMainScreenFragmentBinding;
import com.example.social_media_app.holder_fragments.main_screen_holder_fragment;
import com.example.social_media_app.model_classes.main_screen_model_class;

import java.util.ArrayList;


public class main_screen_fragment extends Fragment {
    FragmentMainScreenFragmentBinding binding;
    ArrayList<main_screen_model_class>arrayList=new ArrayList<>();
    private static main_screen_fragment instance;

    public main_screen_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_screen_menu,menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
//                    case R.id.post:
//                        post_fragment post_fragment=new post_fragment();
//                        FragmentTransaction transaction=getParentFragmentManager().beginTransaction();
//                        transaction.replace(R.id.main_screen_holder,post_fragment).commit();
//                        Toast.makeText(getContext(), "create the post", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.story:
//                        Toast.makeText(getContext(), "create the story", Toast.LENGTH_SHORT).show();
//                        return true;
                    case R.id.chats:
                        Toast.makeText(getContext(), "create the chats", Toast.LENGTH_SHORT).show();
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentMainScreenFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();

        binding.toolbar.inflateMenu(R.menu.main_screen_menu);

        binding.mainScreenRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        main_screen_model_class main_screen_model_class1=new main_screen_model_class();
        main_screen_model_class1.setUsername("amnydv");
        main_screen_model_class1.setFullname("Aman yadav");
        main_screen_model_class1.setLastmessage("Hello i am on your app");
        main_screen_model_class main_screen_model_class2=new main_screen_model_class();
        main_screen_model_class2.setUsername("prbhtsni");
        main_screen_model_class2.setFullname("Prabhat Saini");
        main_screen_model_class2.setLastmessage("Me too is using your app");
        arrayList.add(main_screen_model_class1);
        arrayList.add(main_screen_model_class2);
//        arrayList.add(new main_screen_model_class("n1","f1","hi",1,2));
//        arrayList.add(new main_screen_model_class("n1","f1","hi",1,2));
//        arrayList.add(new main_screen_model_class("n1","f1","hi",1,2));
//        arrayList.add(new main_screen_model_class("n1","f1","hi",1,2));
//        arrayList.add(new main_screen_model_class("n1","f1","hi",1,2));
//        arrayList.add(new main_screen_model_class("n1","f1","hi",1,2));
//        arrayList.add(new main_screen_model_class("n1","f1","hi",1,2));
//        arrayList.add(new main_screen_model_class("n1","f1","hi",1,2));
//        arrayList.add(new main_screen_model_class("n1","f1","hi",1,2));
        main_screen_adapter_recyclerview main_screen_adapter_recyclerview=new main_screen_adapter_recyclerview(getContext(),this.arrayList);
        binding.mainScreenRecycler.setAdapter(main_screen_adapter_recyclerview);

        instance=this;
        return  view;
    }
    public static main_screen_fragment getInstance()
    {
        return instance;
    }
    public void switch_fragment()
    {
        user_profile_fragment user_profile_fragment=new user_profile_fragment();
        main_screen_holder_fragment.getInstance().main_screen_main_fragment_push();
        //main_screen_fragment main_screen_fragment=new main_screen_fragment();
        FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
        //transaction.hide(main_screen_fragment);
//        transaction.replace(((ViewGroup)(getView().getParent())).getId(),user_profile_fragment).addToBackStack(null).commit();
        transaction.replace(R.id.main_screen_holder,user_profile_fragment).commit();
    }
}