package com.example.social_media_app.fragments;

import static android.app.Activity.RESULT_OK;
import static com.theartofdev.edmodo.cropper.CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.social_media_app.Adapters.profile_adapter_recyclerview;
import com.example.social_media_app.Adapters.user_profile_adapter_recyclerview;
import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentProfileFragmentBinding;
import com.example.social_media_app.holder_fragments.profile_holder_fragment;
import com.example.social_media_app.model_classes.profile_model_class;
import com.example.social_media_app.model_classes.user_profile_model_class;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;

public class profile_fragment extends Fragment {

    FragmentProfileFragmentBinding binding;
    ArrayList<profile_model_class> arrayList=new ArrayList<>();
    Uri crop_uri=null;
    public profile_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.profile_screen_menu,menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.profileToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.post:
                        select_image();
                        Toast.makeText(getContext(), "create the post", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.story:
                        Toast.makeText(getContext(), "create the story", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.profile_menu:
                        profile_settings_fragment profile_settings_fragment=new profile_settings_fragment();
                        profile_settings_fragment.show(getParentFragmentManager(),profile_settings_fragment.getTag());
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
        binding= FragmentProfileFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();

        binding.profileToolbar.inflateMenu(R.menu.profile_screen_menu);

        binding.profileRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        arrayList.add(new profile_model_class());
        profile_adapter_recyclerview profile_adapter_recyclerview=new profile_adapter_recyclerview(getContext(),this.arrayList);
        binding.profileRecyclerview.setAdapter(profile_adapter_recyclerview);

        binding.editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_holder_fragment.getInstance().profile_screen_profile_fragment_push();
                edit_profile_fragment edit_profile_fragment=new edit_profile_fragment();
                FragmentTransaction transaction=getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.profile_holder,edit_profile_fragment).commit();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            crop_uri = result.getUri();
            Bundle bundle = new Bundle();
            bundle.putString("string_uri", String.valueOf(crop_uri));
            profile_holder_fragment.getInstance().profile_screen_profile_fragment_push();
            post_fragment post_fragment = new post_fragment();
            post_fragment.setArguments(bundle);
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.profile_holder, post_fragment).commit();
        }
    }

    public void select_image()
    {
        CropImage.activity().start(getContext(),this);
    }
}