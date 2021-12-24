package com.example.social_media_app.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.social_media_app.Adapters.search_screen_adapter_recyclerview;
import com.example.social_media_app.Adapters.user_profile_adapter_recyclerview;
import com.example.social_media_app.MainActivity;
import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentUserProfileFragmentBinding;
import com.example.social_media_app.model_classes.user_profile_model_class;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class user_profile_fragment extends Fragment {
    FragmentUserProfileFragmentBinding binding;
    ArrayList<user_profile_model_class>arrayList=new ArrayList<>();
    private static user_profile_fragment instance;
    String index_fragment;

    FirebaseAuth auth;
    FirebaseFirestore db;

    public user_profile_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentUserProfileFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();

        String index_userid=getArguments().getString("index_userid");
        String index_fullname=getArguments().getString("index_fullname");
        String index_profile_pic=getArguments().getString("index_profile_pic");
        String index_user_description=getArguments().getString("index_user_description");

        Picasso.get().load(index_profile_pic).into(binding.userProfileImage);
        binding.userProfileFullname.setText(index_fullname);
        binding.userProfileDescription.setText(index_user_description);

        binding.userProfileRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));

        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        db.collection("images").document("posts").collection(index_userid).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                if(e!=null)
                {
                    Log.d("user_profile_read","user_profile_read_failed");
                    return;
                }
                else
                {
                    arrayList.clear();
                    for(QueryDocumentSnapshot doc:value)
                    {
                        user_profile_model_class user_profile_model_class=new user_profile_model_class();
                        String user_profile_image=doc.getString("post");
                        user_profile_model_class.setUser_profile_image(user_profile_image);
                        arrayList.add(user_profile_model_class);
                    }
                    user_profile_adapter_recyclerview user_profile_adapter_recyclerview=new user_profile_adapter_recyclerview(getContext(),arrayList);
                    binding.userProfileRecyclerview.setAdapter(user_profile_adapter_recyclerview);
                }
            }
        });

        instance=this;
        binding.messageUserScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch_fragment();
            }
        });

        return view;
    }
    public static user_profile_fragment getInstance(){
        return instance;
    }
    public void switch_fragment()
    {
        index_fragment=getArguments().getString("index_fragment");
        if(index_fragment.equals("main_fragment"))
        {
            MainActivity.getInstance().switch_user_chat_fragment();
        }
        else if(index_fragment.equals("search_fragment"))
        {
            MainActivity.getInstance().switch_chat_detail_fragment();
        }
        else if(index_fragment.equals("likings_fragment"))
        {
            MainActivity.getInstance().switch_liking_chat_fragment();
        }
    }
}