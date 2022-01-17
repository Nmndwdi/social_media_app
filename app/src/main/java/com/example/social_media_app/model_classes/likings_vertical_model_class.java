package com.example.social_media_app.model_classes;

public class likings_vertical_model_class {
    String profile_pic,username,userid,fullname,last_chat,user_description;
    public likings_vertical_model_class()
    {

    }

    public likings_vertical_model_class(String profile_pic,String username, String userid, String fullname,String last_chat,String user_description) {
        this.profile_pic = profile_pic;
        this.username=username;
        this.userid = userid;
        this.fullname = fullname;
        this.last_chat=last_chat;
        this.user_description=user_description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_description() {
        return user_description;
    }

    public void setUser_description(String user_description) {
        this.user_description = user_description;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
