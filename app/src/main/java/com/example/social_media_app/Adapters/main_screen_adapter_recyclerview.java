package com.example.social_media_app.Adapters;

import android.content.Context;
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

import java.util.ArrayList;

public class main_screen_adapter_recyclerview extends RecyclerView.Adapter<main_screen_adapter_recyclerview.main_screen_viewholder>  {
    Context context;
    ArrayList<main_screen_model_class>arrayList=new ArrayList<>();

    public main_screen_adapter_recyclerview(Context context, ArrayList<main_screen_model_class> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public main_screen_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_screen,parent,false);
        return new main_screen_viewholder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull main_screen_viewholder holder, int position) {
        holder.username.setText(arrayList.get(position).getUsername());
        holder.fullname.setText(arrayList.get(position).getFullname());
        holder.lastmessage.setText(arrayList.get(position).getLastmessage());

        holder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_screen_fragment.getInstance().switch_fragment();
            }
        });
        holder.fullname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_screen_fragment.getInstance().switch_fragment();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class main_screen_viewholder extends RecyclerView.ViewHolder
    {
        TextView username,fullname,lastmessage;
        ImageView profile_image,uploaded_image;
        public main_screen_viewholder(@NonNull View itemView) {
            super(itemView);
            username=(TextView) itemView.findViewById(R.id.Username);
            fullname=(TextView) itemView.findViewById(R.id.Fullname);
            lastmessage=(TextView) itemView.findViewById(R.id.lastmessage);
            profile_image=(ImageView) itemView.findViewById(R.id.profile_image);
            uploaded_image=(ImageView) itemView.findViewById(R.id.uploaded_image);
        }

        public TextView getFullname() {
            return fullname;
        }

        public TextView getLastmessage() {
            return lastmessage;
        }

        public TextView getUsername() {
            return username;
        }

        public ImageView getProfile_image() {
            return profile_image;
        }

        public ImageView getUploaded_image() {
            return uploaded_image;
        }
    }
}
