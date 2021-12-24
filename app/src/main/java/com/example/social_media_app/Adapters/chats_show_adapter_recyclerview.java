package com.example.social_media_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.social_media_app.R;
import com.example.social_media_app.model_classes.chat_screen_model_class;

import java.util.ArrayList;

public class chats_show_adapter_recyclerview extends RecyclerView.Adapter<chats_show_adapter_recyclerview.chat_viewholder>{

    Context context;
    ArrayList<chat_screen_model_class>arrayList=new ArrayList<>();

    public chats_show_adapter_recyclerview(Context context, ArrayList<chat_screen_model_class> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public chat_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.chat_screen,parent,false);
        return new chat_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chat_viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class chat_viewholder extends RecyclerView.ViewHolder{
        TextView fullname,last_message;
        public chat_viewholder(@NonNull View itemView) {
            super(itemView);
            fullname=itemView.findViewById(R.id.chats_fullname);
            last_message=itemView.findViewById(R.id.chats_last_chat);
        }
    }
}
