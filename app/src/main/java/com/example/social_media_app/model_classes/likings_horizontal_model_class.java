package com.example.social_media_app.model_classes;

import java.util.ArrayList;
import java.util.Map;

public class likings_horizontal_model_class {
    String latest_pic,fullname,userid,profile_pic,user_description;
    ArrayList<Map<String,Object>> posts=new ArrayList<>();

    public likings_horizontal_model_class()
    {

    }

    public likings_horizontal_model_class(String latest_pic, String fullname, String userid, String profile_pic, String user_description) {
        this. latest_pic=latest_pic;
        this.fullname = fullname;
        this.userid = userid;
        this.profile_pic = profile_pic;
        this.user_description = user_description;
    }

    public ArrayList<Map<String, Object>> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Map<String, Object>> posts) {
        this.posts = posts;
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

    public String getLatest_pic() {
        return latest_pic;
    }

    public void setLatest_pic(String latest_pic) {
        this.latest_pic = latest_pic;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
