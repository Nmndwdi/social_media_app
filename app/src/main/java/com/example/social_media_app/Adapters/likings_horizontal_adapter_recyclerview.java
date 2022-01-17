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
import com.example.social_media_app.animationss.DepthPageTransformer;
import com.example.social_media_app.fragments.likings_fragment;
import com.example.social_media_app.model_classes.likings_horizontal_model_class;

import java.util.ArrayList;

public class likings_horizontal_adapter_recyclerview extends RecyclerView.Adapter<likings_horizontal_adapter_recyclerview.likings_horizontal_viewholder>{
    Context context;
    ArrayList<likings_horizontal_model_class>arrayList=new ArrayList<>();

    public likings_horizontal_adapter_recyclerview(Context context, ArrayList<likings_horizontal_model_class> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public likings_horizontal_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.likings_horizontal_screen,parent,false);
        return new likings_horizontal_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull likings_horizontal_viewholder holder, int position) {

        String fullname =arrayList.get(position).getFullname();
        holder.fullname.setText(fullname);
        String userid =arrayList.get(position).getUserid();
        String user_description=arrayList.get(position).getUser_description();
        String profile_pic=arrayList.get(position).getProfile_pic();
        String last_pic=arrayList.get(position).getLast_pic();
        Glide.with(context).load(last_pic).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likings_fragment.getInstance().switch_fragment(userid,fullname,user_description,profile_pic);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class likings_horizontal_viewholder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView fullname;
        public likings_horizontal_viewholder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.likings_latest_image);
            fullname=itemView.findViewById(R.id.likings_horizontal_fullname);
        }
    }
}
