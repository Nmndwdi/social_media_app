package com.example.social_media_app.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.social_media_app.R;
import com.example.social_media_app.fragments.main_screen_fragment;
import com.example.social_media_app.model_classes.main_screen_model_class;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class main_screen_adapter_recyclerview extends RecyclerView.Adapter<main_screen_adapter_recyclerview.main_screen_viewholder> {
    Context context;
    ArrayList<main_screen_model_class> arrayList = new ArrayList<>();

    public main_screen_adapter_recyclerview(Context context, ArrayList<main_screen_model_class> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public main_screen_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_screen, parent, false);
        return new main_screen_viewholder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull main_screen_viewholder holder, int position) {
        Picasso.get().load(arrayList.get(position).getUploaded_image()).into(holder.uploaded_image);
        holder.image_description.setText(arrayList.get(position).getImage_description());
        String userid = arrayList.get(position).getUserid();
        String username = arrayList.get(position).getUsername();
        String fullname=arrayList.get(position).getFullname();
        String profile_pic = arrayList.get(position).getProfile_image();
        String user_description = arrayList.get(position).getUser_description();
        holder.username.setText(username);
        holder.fullname.setText(fullname);
        Picasso.get().load(profile_pic).into(holder.profile_image);


        holder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_screen_fragment.getInstance().switch_user_profile_fragment(userid, fullname , profile_pic, user_description);
            }
        });
        holder.fullname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_screen_fragment.getInstance().switch_user_profile_fragment(userid, fullname, profile_pic, user_description);
            }
        });
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_screen_fragment.getInstance().switch_chat_detail_fragment();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class main_screen_viewholder extends RecyclerView.ViewHolder {
        TextView username, fullname, lastmessage, image_description;
        ImageView profile_image, uploaded_image, message;

        public main_screen_viewholder(@NonNull View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.Username);
            fullname = (TextView) itemView.findViewById(R.id.Fullname);
            lastmessage = (TextView) itemView.findViewById(R.id.lastmessage);
            profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            uploaded_image = (ImageView) itemView.findViewById(R.id.uploaded_image);
            message = (ImageView) itemView.findViewById(R.id.message_main_screen);
            image_description = (TextView) itemView.findViewById(R.id.image_description);
        }
    }
}
