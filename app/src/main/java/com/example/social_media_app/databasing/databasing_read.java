package com.example.social_media_app.databasing;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.social_media_app.model_classes.main_screen_model_class;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class databasing_read {
    FirebaseAuth auth;
    FirebaseFirestore db;
    String gender;

    public databasing_read() {

    }

    public databasing_read(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<main_screen_model_class> main_read() {
        ArrayList<main_screen_model_class> arrayList = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        db.collection(gender).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d("main_read", "main_read_failed");
                    return;
                } else {
                    arrayList.clear();
                    for (QueryDocumentSnapshot doc : value) {
                        main_screen_model_class main_screen_model_class = new main_screen_model_class();
                        String username = doc.getString("Username");
                        String fullname = doc.getString("Fullname");
                        String image_description = doc.getString("latest_image_description");
                        String latest_pic = doc.getString("latest_pic");
                        String profile_image = doc.getString("Profile_image");
                        main_screen_model_class.setUsername(username);
                        main_screen_model_class.setFullname(fullname);
                        main_screen_model_class.setImage_description(image_description);
                        main_screen_model_class.setUploaded_image(latest_pic);
                        main_screen_model_class.setProfile_image(profile_image);
                        arrayList.add(main_screen_model_class);
                    }
                }
            }
        });
        return arrayList;
    }

}
