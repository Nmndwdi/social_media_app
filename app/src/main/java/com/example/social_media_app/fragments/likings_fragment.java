package com.example.social_media_app.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.social_media_app.Adapters.likings_horizontal_adapter_recyclerview;
import com.example.social_media_app.Adapters.likings_vertical_adapter_recyclerview;
import com.example.social_media_app.MainActivity;
import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentLikingsFragmentBinding;
import com.example.social_media_app.holder_fragments.likings_screen_holder_fragment;
import com.example.social_media_app.model_classes.likings_horizontal_model_class;
import com.example.social_media_app.model_classes.likings_vertical_model_class;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout;

public class likings_fragment extends Fragment {

    ArrayList<likings_horizontal_model_class> arrayList_horizontal = new ArrayList<>();
    ArrayList<likings_vertical_model_class> arrayList_vertical = new ArrayList<>();
    FragmentLikingsFragmentBinding binding;
    private static likings_fragment instance;
    FirebaseAuth auth;
    FirebaseFirestore db;

    public likings_fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLikingsFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        String userid = auth.getCurrentUser().getUid();
        String gender_key= MainActivity.getInstance().getGender_key();
        db.collection(gender_key).whereEqualTo(userid,true).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d("likings_read", "likings_read_failed");
                    return;
                } else {
                    arrayList_horizontal.clear();
                    arrayList_vertical.clear();
                    for (QueryDocumentSnapshot doc : value) {
                        likings_horizontal_model_class likings_horizontal_model_class = new likings_horizontal_model_class();
                        likings_vertical_model_class likings_vertical_model_class = new likings_vertical_model_class();
                        String username = doc.getString("Username");
                        String fullname = doc.getString("Fullname");
                        String profile_pic = doc.getString("Profile_image");
                        String last_pic = doc.getString("latest_pic");
                        String user_description=doc.getString("Description");
                        ArrayList<Map<String,Object>> posts=(ArrayList<Map<String,Object>>) doc.get("Posts");
                        likings_horizontal_model_class.setPosts(posts);
                        likings_horizontal_model_class.setFullname(fullname);
                        likings_horizontal_model_class.setLatest_pic(last_pic);
                        likings_horizontal_model_class.setProfile_pic(profile_pic);
                        likings_horizontal_model_class.setUserid(doc.getId());
                        likings_horizontal_model_class.setUser_description(user_description);
                        likings_vertical_model_class.setPosts(posts);
                        likings_vertical_model_class.setFullname(fullname);
                        likings_vertical_model_class.setUserid(doc.getId());
                        likings_vertical_model_class.setUsername(username);
                        likings_vertical_model_class.setProfile_pic(profile_pic);
                        likings_vertical_model_class.setUser_description(user_description);
                        arrayList_vertical.add(likings_vertical_model_class);
                        arrayList_horizontal.add(likings_horizontal_model_class);
                    }
                    binding.likingsHorizontalRecyclerview.setLayoutManager(new ZoomRecyclerLayout(getContext(), LinearLayoutManager.HORIZONTAL, false));
//                  binding.likingsHorizontalRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                    binding.likingsHorizontalRecyclerview.setAdapter(new likings_horizontal_adapter_recyclerview(getContext(), arrayList_horizontal));

                    binding.likingsVerticalRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.likingsVerticalRecyclerview.setAdapter(new likings_vertical_adapter_recyclerview(getContext(), arrayList_vertical));
                }
            }
        });

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.likingsHorizontalRecyclerview);

        instance = this;
        return view;
    }

    public static likings_fragment getInstance() {
        return instance;
    }

    public void switch_fragment(String userid,String fullname,String user_description,String profile_pic,ArrayList<Map<String,Object>>posts) {
        Bundle bundle = new Bundle();
        bundle.putString("index_fragment", "likings_fragment");
        bundle.putString("index_userid",userid);
        bundle.putString("index_fullname",fullname);
        bundle.putString("index_profile_pic",profile_pic);
        bundle.putString("index_user_description",user_description);
        bundle.putSerializable("index_posts",posts);
        likings_screen_holder_fragment.getInstance().likings_screen_liking_fragment_push();
        user_profile_fragment user_profile_fragment = new user_profile_fragment();
        user_profile_fragment.setArguments(bundle);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.likings_screen_holder, user_profile_fragment).commit();
    }
}