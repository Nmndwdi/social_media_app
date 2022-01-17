package com.example.social_media_app.model_classes;

public class likings_horizontal_model_class {
    String last_pic,fullname,userid,profile_pic,user_description;

    public likings_horizontal_model_class()
    {

    }

    public likings_horizontal_model_class(String last_pic, String fullname, String userid, String profile_pic, String user_description) {
        this.last_pic = last_pic;
        this.fullname = fullname;
        this.userid = userid;
        this.profile_pic = profile_pic;
        this.user_description = user_description;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getUser_description() {
        return user_description;
    }

    public void setUser_description(String user_description) {
        this.user_description = user_description;
    }

    public String getLast_pic() {
        return last_pic;
    }

    public void setLast_pic(String last_pic) {
        this.last_pic = last_pic;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
