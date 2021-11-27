package com.example.social_media_app.model_classes;

public class user_profile_model_class {
    int user_profile_image;

    public user_profile_model_class() {

    }

    public user_profile_model_class(int user_profile_image) {
        this.user_profile_image = user_profile_image;
    }

    public int getUser_profile_image() {
        return user_profile_image;
    }

    public void setUser_profile_image(int user_profile_image) {
        this.user_profile_image = user_profile_image;
    }
}
