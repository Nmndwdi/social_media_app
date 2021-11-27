package com.example.social_media_app.model_classes;

public class main_screen_model_class {
    String username,fullname,lastmessage;
    int profile_image,uploaded_image;

    public main_screen_model_class()
    {

    }

    public main_screen_model_class(String username, String fullname, String lastmessage, int profile_image, int uploaded_image) {
        this.username = username;
        this.fullname = fullname;
        this.lastmessage = lastmessage;
        this.profile_image = profile_image;
        this.uploaded_image = uploaded_image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }

    public int getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(int profile_image) {
        this.profile_image = profile_image;
    }

    public int getUploaded_image() {
        return uploaded_image;
    }

    public void setUploaded_image(int uploaded_image) {
        this.uploaded_image = uploaded_image;
    }
}
