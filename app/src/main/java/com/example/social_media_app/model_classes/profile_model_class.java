package com.example.social_media_app.model_classes;

public class profile_model_class {
    String  profile_image;

    public profile_model_class()
    {

    }
    public profile_model_class(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}
