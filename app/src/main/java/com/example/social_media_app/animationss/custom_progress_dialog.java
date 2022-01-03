package com.example.social_media_app.animationss;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.social_media_app.R;

public class custom_progress_dialog extends Dialog {
    public custom_progress_dialog(@NonNull Context context) {
        super(context);

        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        getWindow().setAttributes(params);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        View view= LayoutInflater.from(context).inflate(R.layout.progress_dialog,null);
        setContentView(view);
    }
}
