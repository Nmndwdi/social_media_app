package com.example.social_media_app.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.social_media_app.R;
import com.example.social_media_app.model_classes.profile_model_class;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class profile_adapter_recyclerview extends RecyclerView.Adapter<profile_adapter_recyclerview.profile_viewholder>{

    Context context;
    ArrayList<profile_model_class>arrayList=new ArrayList<>();

    public profile_adapter_recyclerview(Context context, ArrayList<profile_model_class> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public profile_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_screen,parent,false);
        return new profile_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull profile_viewholder holder, int position) {
        String profile_image=arrayList.get(position).getPost();
//        Picasso.get().load(profile_image).into(holder.profile_image);
        Glide.with(context).load(profile_image).into(holder.profile_post);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class profile_viewholder extends RecyclerView.ViewHolder {
        ImageView profile_post;
        public profile_viewholder(@NonNull View itemView) {
            super(itemView);
            profile_post =(ImageView)itemView.findViewById(R.id.profile_post);
        }

    }
}
