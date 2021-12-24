package com.example.social_media_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.social_media_app.Adapters.likings_horizontal_adapter_recyclerview;
import com.example.social_media_app.Adapters.likings_vertical_adapter_recyclerview;
import com.example.social_media_app.R;
import com.example.social_media_app.databinding.FragmentLikingsFragmentBinding;
import com.example.social_media_app.holder_fragments.likings_screen_holder_fragment;
import com.example.social_media_app.model_classes.likings_horizontal_model_class;
import com.example.social_media_app.model_classes.likings_vertical_model_class;

import java.util.ArrayList;

import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout;

public class likings_fragment extends Fragment {

    ArrayList<likings_horizontal_model_class>arrayList_horizontal=new ArrayList<>();
    ArrayList<likings_vertical_model_class>arrayList_vertical=new ArrayList<>();
    FragmentLikingsFragmentBinding binding;
    private static likings_fragment instance;

    public likings_fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentLikingsFragmentBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        arrayList_horizontal.add(new likings_horizontal_model_class());
        arrayList_horizontal.add(new likings_horizontal_model_class());
        arrayList_horizontal.add(new likings_horizontal_model_class());
        arrayList_horizontal.add(new likings_horizontal_model_class());
        arrayList_horizontal.add(new likings_horizontal_model_class());
        arrayList_horizontal.add(new likings_horizontal_model_class());
        arrayList_horizontal.add(new likings_horizontal_model_class());
        arrayList_horizontal.add(new likings_horizontal_model_class());
        arrayList_horizontal.add(new likings_horizontal_model_class());
        arrayList_horizontal.add(new likings_horizontal_model_class());
        arrayList_horizontal.add(new likings_horizontal_model_class());

        binding.likingsHorizontalRecyclerview.setLayoutManager(new ZoomRecyclerLayout(getContext(),LinearLayoutManager.HORIZONTAL,false));
//        binding.likingsHorizontalRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.likingsHorizontalRecyclerview.setAdapter(new likings_horizontal_adapter_recyclerview(getContext(),this.arrayList_horizontal));
        SnapHelper snapHelper=new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.likingsHorizontalRecyclerview);


        arrayList_vertical.add(new likings_vertical_model_class());
        arrayList_vertical.add(new likings_vertical_model_class());
        arrayList_vertical.add(new likings_vertical_model_class());
        arrayList_vertical.add(new likings_vertical_model_class());
        arrayList_vertical.add(new likings_vertical_model_class());
        arrayList_vertical.add(new likings_vertical_model_class());
        arrayList_vertical.add(new likings_vertical_model_class());
        arrayList_vertical.add(new likings_vertical_model_class());
        arrayList_vertical.add(new likings_vertical_model_class());
        arrayList_vertical.add(new likings_vertical_model_class());
        arrayList_vertical.add(new likings_vertical_model_class());
        arrayList_vertical.add(new likings_vertical_model_class());
        arrayList_vertical.add(new likings_vertical_model_class());
        arrayList_vertical.add(new likings_vertical_model_class());
        arrayList_vertical.add(new likings_vertical_model_class());
        arrayList_vertical.add(new likings_vertical_model_class());
        arrayList_vertical.add(new likings_vertical_model_class());

        binding.likingsVerticalRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.likingsVerticalRecyclerview.setAdapter(new likings_vertical_adapter_recyclerview(getContext(),this.arrayList_vertical));
        instance=this;
        return view;
    }

    public static likings_fragment getInstance()
    {
        return instance;
    }

    public void switch_fragment()
    {
        Bundle bundle=new Bundle();
        bundle.putString("index_fragment","likings_fragment");
        likings_screen_holder_fragment.getInstance().likings_screen_liking_fragment_push();
        user_profile_fragment user_profile_fragment=new user_profile_fragment();
        user_profile_fragment.setArguments(bundle);
        FragmentTransaction transaction=getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.likings_screen_holder,user_profile_fragment).commit();
    }
}