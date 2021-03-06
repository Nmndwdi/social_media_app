package com.example.social_media_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.social_media_app.R;
import com.example.social_media_app.fragments.likings_fragment;
import com.example.social_media_app.model_classes.likings_vertical_model_class;

import java.util.ArrayList;
import java.util.Map;

public class likings_vertical_adapter_recyclerview extends RecyclerView.Adapter<likings_vertical_adapter_recyclerview.likings_vertical_viewholder>{

    Context context;
    ArrayList<likings_vertical_model_class>arrayList=new ArrayList<>();

    public likings_vertical_adapter_recyclerview(Context context, ArrayList<likings_vertical_model_class> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public likings_vertical_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.likings_vertical_screen,parent,false);
        return new likings_vertical_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull likings_vertical_viewholder holder, int position) {

        String fullname =arrayList.get(position).getFullname();
        String userid=arrayList.get(position).getUserid();
        String username=arrayList.get(position).getUsername();
        String user_description=arrayList.get(position).getUser_description();
        holder.fullname.setText(fullname);
        holder.username.setText(username);
        String profile_pic=arrayList.get(position).getProfile_pic();
        ArrayList<Map<String,Object>>posts=arrayList.get(position).getPosts();
        Glide.with(context).load(profile_pic).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likings_fragment.getInstance().switch_fragment(userid,fullname,user_description,profile_pic,posts);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class likings_vertical_viewholder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView username,fullname,last_chat;
        public likings_vertical_viewholder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.likings_profile_image);
            username=itemView.findViewById(R.id.likings_username);
            fullname=itemView.findViewById(R.id.likings_fullname);
            last_chat=itemView.findViewById(R.id.likings_last_chat);
        }
    }
}
