package com.example.social_media_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.social_media_app.R;
import com.example.social_media_app.model_classes.chat_model_class;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class chat_screen_adapter_recyclerview extends RecyclerView.Adapter {

    Context context;
    ArrayList<chat_model_class>arrayList;
    int sender_view_type=1,receiver_view_type=2;

    public chat_screen_adapter_recyclerview(Context context, ArrayList<chat_model_class> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==sender_view_type)
        {
            View view= LayoutInflater.from(context).inflate(R.layout.message_sender,parent,false);
            return new message_sender_viewholder(view);
        }
        else
        {
            View view=LayoutInflater.from(context).inflate(R.layout.message_receiver,parent,false);
            return new message_receiver_viewholder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        chat_model_class chat_model_class=arrayList.get(position);
        if(holder.getClass()==message_sender_viewholder.class)
        {
            message_sender_viewholder message_sender_viewholder=(message_sender_viewholder)(holder);
            message_sender_viewholder.sender_message.setText(chat_model_class.getMessage());
            message_sender_viewholder.sender_time.setText(chat_model_class.getTimestamp().toString());
        }
        else
        {
            ((message_receiver_viewholder)holder).receiver_message.setText(chat_model_class.getMessage());
            ((message_receiver_viewholder)holder).receiver_time.setText(chat_model_class.getTimestamp().toString());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(arrayList.get(position).getUserid().equals(FirebaseAuth.getInstance().getUid()))
        {
            return sender_view_type;
        }
        else
        {
            return receiver_view_type;
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class message_sender_viewholder extends RecyclerView.ViewHolder{
        TextView sender_message,sender_time;
        public message_sender_viewholder(@NonNull View itemView) {
            super(itemView);
            sender_message=itemView.findViewById(R.id.sender_message);
            sender_time=itemView.findViewById(R.id.sender_time);
        }
    }
    public class message_receiver_viewholder extends RecyclerView.ViewHolder{

        TextView receiver_message,receiver_time;
        public message_receiver_viewholder(@NonNull View itemView) {
            super(itemView);
            receiver_message=itemView.findViewById(R.id.receiver_message);
            receiver_time=itemView.findViewById(R.id.receiver_time);
        }
    }
}
