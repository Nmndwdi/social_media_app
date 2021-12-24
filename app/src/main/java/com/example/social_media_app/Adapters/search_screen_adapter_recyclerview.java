package com.example.social_media_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.social_media_app.MainActivity;
import com.example.social_media_app.R;
import com.example.social_media_app.fragments.Search_fragment;
import com.example.social_media_app.fragments.user_profile_fragment;
import com.example.social_media_app.model_classes.search_screen_model_class;
import com.example.social_media_app.signing.signup_activity;

import java.util.ArrayList;

public class search_screen_adapter_recyclerview extends RecyclerView.Adapter<search_screen_adapter_recyclerview.search_screen_viewholder>{

    Context context;
    ArrayList<search_screen_model_class>arrayList=new ArrayList<>();

    public search_screen_adapter_recyclerview(Context context, ArrayList<search_screen_model_class> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public search_screen_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_screen,parent,false);
        return new search_screen_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull search_screen_viewholder holder, int position) {
        search_screen_model_class search_screen_model_class=arrayList.get(position);
        holder.search_image.setImageResource(search_screen_model_class.getSearch_image());
        holder.search_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Search_fragment.getInstance().switch_user_profile_fragment();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class search_screen_viewholder extends RecyclerView.ViewHolder
    {

        ImageView search_image;
        public search_screen_viewholder(@NonNull View itemView)
        {
            super(itemView);
            search_image=(ImageView) itemView.findViewById(R.id.search_image);
        }
        public ImageView getSearch_image()
        {
            return search_image;
        }
    }
}
