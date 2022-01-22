package com.example.social_media_app.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.social_media_app.Adapters.main_screen_adapter_recyclerview;
import com.example.social_media_app.MainActivity;
import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentMainScreenFragmentBinding;
import com.example.social_media_app.holder_fragments.main_screen_holder_fragment;
import com.example.social_media_app.model_classes.main_screen_model_class;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;


public class main_screen_fragment extends Fragment {
    FragmentMainScreenFragmentBinding binding;
    ArrayList<main_screen_model_class>arrayList=new ArrayList<>();
    private static main_screen_fragment instance;

    FirebaseAuth auth;
    FirebaseFirestore db;

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
                    case R.id.chats:
                        chats_fragment chats_fragment=new chats_fragment();
                        chats_fragment.show(getParentFragmentManager(),chats_fragment.getTag());
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

        String gender_key= MainActivity.getInstance().getGender_key();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

//        db.collection(gender_key).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if(task.isSuccessful())
//                {
//                    arrayList.clear();
//                    for (QueryDocumentSnapshot doc : task.getResult()) {
//                        main_screen_model_class main_screen_model_class = new main_screen_model_class();
//                        Boolean saved_profile=false;
//                        if(doc.contains(auth.getCurrentUser().getUid()))
//                        {
//                            if(doc.getBoolean(auth.getCurrentUser().getUid())) {
//                                saved_profile = true;
//                            }
//                        }
//                        String username = doc.getString("Username");
//                        String fullname = doc.getString("Fullname");
//                        String post_description = doc.getString("post_description");
//                        String latest_pic = doc.getString("latest_pic");
//                        String profile_image = doc.getString("Profile_image");
//                        String user_description=doc.getString("Description");
//                        ArrayList<Map<String,Object>> posts=(ArrayList<Map<String,Object>>) doc.get("Posts");
//                        main_screen_model_class.setPosts(posts);
//                        main_screen_model_class.setUsername(username);
//                        main_screen_model_class.setFullname(fullname);
//                        main_screen_model_class.setImage_description(post_description);
//                        main_screen_model_class.setUploaded_image(latest_pic);
//                        main_screen_model_class.setProfile_image(profile_image);
//                        main_screen_model_class.setUserid(doc.getId());
//                        main_screen_model_class.setUser_description(user_description);
//                        main_screen_model_class.setSaved_profile(saved_profile);
//                        arrayList.add(main_screen_model_class);
//                    }
//                    main_screen_adapter_recyclerview main_screen_adapter_recyclerview=new main_screen_adapter_recyclerview(getContext(),arrayList);
//                    binding.mainScreenRecycler.setAdapter(main_screen_adapter_recyclerview);
//                }
//                else
//                {
//                    Log.d("main_read", "main_read_failed");
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });

        db.collection(gender_key).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d("main_read", "main_read_failed");
                    return;
                } else {
                    arrayList.clear();
                    for (QueryDocumentSnapshot doc : value) {
                        main_screen_model_class main_screen_model_class = new main_screen_model_class();
                        Boolean saved_profile=false;
                        if(doc.contains(auth.getCurrentUser().getUid()))
                        {
                            if(doc.getBoolean(auth.getCurrentUser().getUid())) {
                                saved_profile = true;
                            }
                        }
                        String username = doc.getString("Username");
                        String fullname = doc.getString("Fullname");
                        String post_description = doc.getString("post_description");
                        String latest_pic = doc.getString("latest_pic");
                        String profile_image = doc.getString("Profile_image");
                        String user_description=doc.getString("Description");
                        ArrayList<Map<String,Object>> posts=(ArrayList<Map<String,Object>>) doc.get("Posts");
                        main_screen_model_class.setPosts(posts);
                        main_screen_model_class.setUsername(username);
                        main_screen_model_class.setFullname(fullname);
                        main_screen_model_class.setImage_description(post_description);
                        main_screen_model_class.setUploaded_image(latest_pic);
                        main_screen_model_class.setProfile_image(profile_image);
                        main_screen_model_class.setUserid(doc.getId());
                        main_screen_model_class.setUser_description(user_description);
                        main_screen_model_class.setSaved_profile(saved_profile);
                        arrayList.add(main_screen_model_class);
                    }
                    main_screen_adapter_recyclerview main_screen_adapter_recyclerview=new main_screen_adapter_recyclerview(getContext(),arrayList);
                    binding.mainScreenRecycler.setAdapter(main_screen_adapter_recyclerview);
                }
            }
        });


        instance=this;
        return  view;
    }
    public static main_screen_fragment getInstance()
    {
        return instance;
    }

    public void switch_user_profile_fragment(String userid,String fullname,String profile_pic,String user_description,ArrayList<Map<String,Object>>posts)
    {
        Bundle bundle=new Bundle();
        bundle.putString("index_fragment","main_fragment");
        bundle.putString("index_userid",userid);
        bundle.putString("index_fullname",fullname);
        bundle.putString("index_profile_pic",profile_pic);
        bundle.putString("index_user_description",user_description);
        bundle.putSerializable("index_posts",posts);
        user_profile_fragment user_profile_fragment=new user_profile_fragment();
        main_screen_holder_fragment.getInstance().main_screen_main_fragment_push();
        user_profile_fragment.setArguments(bundle);
        FragmentTransaction transaction= getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.main_screen_holder,user_profile_fragment).commit();
    }
    public void switch_chat_detail_fragment()
    {
        chat_detail_fragment chat_detail_fragment=new chat_detail_fragment();
        main_screen_holder_fragment.getInstance().main_screen_main_fragment_push();
        FragmentTransaction transaction=getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.main_screen_holder,chat_detail_fragment).commit();
    }

}