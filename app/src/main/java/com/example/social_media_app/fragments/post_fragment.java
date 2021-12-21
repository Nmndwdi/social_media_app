package com.example.social_media_app.fragments;

import static android.app.Activity.RESULT_OK;
import static com.theartofdev.edmodo.cropper.CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentPostFragmentBinding;
import com.example.social_media_app.holder_fragments.profile_holder_fragment;
import com.theartofdev.edmodo.cropper.CropImage;

public class post_fragment extends Fragment {
    FragmentPostFragmentBinding binding;
    Uri uri;
    public post_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentPostFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        String string_uri=getArguments().getString("string_uri");
        uri= Uri.parse(string_uri);
        binding.postImage.setImageURI(uri);
        binding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_holder_fragment.getInstance().profile_screen_pop();
            }
        });
        binding.post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_holder_fragment.getInstance().profile_screen_pop();
                }
        });
        binding.reselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reselect_image();
            }
        });
        return view;
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK)
        {
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
            uri=result.getUri();
            binding.postImage.setImageURI(uri);
        }
        else
        {
            Toast.makeText(getContext(), "Please try again...", Toast.LENGTH_SHORT).show();
            profile_holder_fragment.getInstance().profile_screen_pop();
            profile_fragment profile_fragment=new profile_fragment();
            FragmentTransaction transaction=getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.profile_holder,profile_fragment).commit();
        }
    }
    public void reselect_image()
    {
        CropImage.activity().start(getContext(),this);
    }
}