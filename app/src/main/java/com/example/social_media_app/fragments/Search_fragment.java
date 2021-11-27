package com.example.social_media_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.social_media_app.Adapters.search_screen_adapter_recyclerview;
import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentSearchFragmentBinding;
import com.example.social_media_app.holder_fragments.search_screen_holder_fragment;
import com.example.social_media_app.model_classes.search_screen_model_class;

import java.util.ArrayList;

public class Search_fragment extends Fragment {
    FragmentSearchFragmentBinding binding;
    ArrayList<search_screen_model_class>arrayList=new ArrayList<>();
    private static Search_fragment instance;

    public Search_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();

        binding.searchScreenRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        arrayList.add(new search_screen_model_class(R.drawable.account));
        arrayList.add(new search_screen_model_class(R.drawable.avengers));
        arrayList.add(new search_screen_model_class(R.drawable.add_main_screen));
        arrayList.add(new search_screen_model_class(R.drawable.google));
        arrayList.add(new search_screen_model_class(R.drawable.facebook));
        arrayList.add(new search_screen_model_class(R.drawable.chat));
        arrayList.add(new search_screen_model_class(R.drawable.save));
        search_screen_adapter_recyclerview search_screen_adapter_recyclerview=new search_screen_adapter_recyclerview(getContext(),this.arrayList);
        binding.searchScreenRecyclerView.setAdapter(search_screen_adapter_recyclerview);
        instance=this;

//        binding.btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                user_profile_fragment user_profile_fragment=new user_profile_fragment();
//                FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
//                transaction.replace(R.id.linearrrrrr,user_profile_fragment).addToBackStack(null).commit();
//            }
//        });

        return view;
    }

    public static Search_fragment getInstance()
    {
        return instance;
    }
    public void switch_fragment()
    {
        user_profile_fragment user_profile_fragment=new user_profile_fragment();
        search_screen_holder_fragment.getInstance().search_screen_search_fragment_push();
        //Search_fragment search_fragment=new Search_fragment();
        FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
        //transaction.hide(search_fragment);
//        transaction.replace(((ViewGroup)(getView().getParent())).getId(),user_profile_fragment).addToBackStack(null).commit();
        transaction.replace(R.id.search_screen_holder,user_profile_fragment).commit();
    }
}