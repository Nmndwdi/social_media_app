package com.example.social_media_app.model_classes;

public class likings_vertical_model_class {
    String image,username,fullname,last_chat;
    public likings_vertical_model_class()
    {

    }

    public likings_vertical_model_class(String image, String username, String fullname,String last_chat) {
        this.image = image;
        this.username = username;
        this.fullname = fullname;
        this.last_chat=last_chat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getLast_chat() {
        return last_chat;
    }

    public void setLast_chat(String last_chat) {
        this.last_chat = last_chat;
    }
}
