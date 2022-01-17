package com.example.social_media_app.fragments;

import static android.app.Activity.RESULT_OK;
import static com.theartofdev.edmodo.cropper.CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.social_media_app.MainActivity;
import com.example.social_media_app.animationss.custom_progress_dialog;
import com.example.social_media_app.databasing.databasing_write;
import com.example.social_media_app.databasing.firebase_storage;
import com.example.social_media_app.databinding.FragmentEditProfileFragmentBinding;
import com.example.social_media_app.holder_fragments.profile_holder_fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;
import java.util.Map;

public class edit_profile_fragment extends Fragment {

    FragmentEditProfileFragmentBinding binding;
    Uri image_uri=null;
    Map<String,Object>map=new HashMap<>();
    FirebaseAuth auth;
    String userid;

    private static edit_profile_fragment instance;

    custom_progress_dialog custom_progress_dialog;

    public edit_profile_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentEditProfileFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        binding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_holder_fragment.getInstance().profile_screen_pop();
            }
        });
        binding.ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                map.put("Country",binding.ccp.getSelectedCountryName());
            }
        });

        custom_progress_dialog=new custom_progress_dialog(getContext());

        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth=FirebaseAuth.getInstance();
                userid=auth.getUid();
                if(binding.editName.getText().toString().trim().length()!=0)
                {
                    map.put("Fullname",binding.editName.getText().toString());
                }
                if(binding.editAge.getText().toString().trim().length()!=0)
                {
                    map.put("age",binding.editAge.getText().toString());
                }
                if(binding.editDescription.getText().toString().trim().length()!=0)
                {
                    map.put("Description",binding.editDescription.getText().toString());
                }
                if(binding.editState.getText().toString().trim().length()!=0)
                {
                    map.put("State",binding.editState.getText().toString());
                }
                if(binding.editCity.getText().toString().trim().length()!=0)
                {
                    map.put("City",binding.editCity.getText().toString());
                }
                if(!map.isEmpty()) {
                    custom_progress_dialog.show();
                    String gender=MainActivity.getInstance().getGender();
                    databasing_write databasing_write = new databasing_write(map,userid,gender);
                    databasing_write.update_account(String.valueOf(image_uri));
                }
                if(image_uri!=null) {
                    custom_progress_dialog.show();
                    String gender= MainActivity.getInstance().getGender();
                    firebase_storage firebase_storage = new firebase_storage(String.valueOf(image_uri), userid,gender);
                    firebase_storage.profile_picture();
                }
            }
        });
        binding.pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickimage();
            }
        });

        instance=this;
        return view;
    }

    public static edit_profile_fragment getInstance(){
        return instance;
    }

    public void cancel_progress_dialog()
    {
        custom_progress_dialog.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK)
        {
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
            image_uri=result.getUri();
            binding.editProfileImage.setImageURI(image_uri);
        }
        else
        {
            Toast.makeText(getContext(), "pls try again to upload image", Toast.LENGTH_SHORT).show();
        }
    }

    public void pickimage()
    {
        CropImage.activity().start(getContext(),this);
    }

}