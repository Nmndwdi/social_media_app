package com.example.social_media_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.social_media_app.R;
import com.example.social_media_app.fragments.user_profile_fragment;
import com.example.social_media_app.model_classes.user_profile_model_class;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class user_profile_adapter_recyclerview extends RecyclerView.Adapter<user_profile_adapter_recyclerview.user_profile_viewholder> {

    Context context;
    ArrayList<user_profile_model_class>arrayList=new ArrayList<>();

    public user_profile_adapter_recyclerview(Context context, ArrayList<user_profile_model_class> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public user_profile_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_profile_screen,parent,false);
        return new user_profile_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull user_profile_viewholder holder, int position) {
        String user_posts=arrayList.get(position).getUser_posts();
//        Picasso.get().load(profile_image).into(holder.user_profile_image);
        Glide.with(context).load(user_posts).into(holder.user_post);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class user_profile_viewholder extends RecyclerView.ViewHolder{

        ImageView user_post;
        public user_profile_viewholder(@NonNull View itemView) {
            super(itemView);
            user_post=(ImageView) itemView.findViewById(R.id.user_post);
        }
    }
}
