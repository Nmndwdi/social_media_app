package com.example.social_media_app.model_classes;

public class main_screen_model_class {
    String username,fullname,lastmessage,image_description,profile_image,uploaded_image,userid,user_description;

    public main_screen_model_class()
    {

    }

    public main_screen_model_class(String username, String fullname, String lastmessage, String profile_image, String uploaded_image) {
        this.username = username;
        this.fullname = fullname;
        this.lastmessage = lastmessage;
        this.profile_image = profile_image;
        this.uploaded_image = uploaded_image;
    }

    public String getUser_description() {
        return user_description;
    }

    public void setUser_description(String user_description) {
        this.user_description = user_description;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImage_description() {
        return image_description;
    }

    public void setImage_description(String image_description) {
        this.image_description = image_description;
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

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getUploaded_image() {
        return uploaded_image;
    }

    public void setUploaded_image(String uploaded_image) {
        this.uploaded_image = uploaded_image;
    }
}
