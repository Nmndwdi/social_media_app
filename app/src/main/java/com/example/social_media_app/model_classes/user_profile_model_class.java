package com.example.social_media_app.model_classes;

public class user_profile_model_class {
    String user_posts,post_description;

    public user_profile_model_class() {

    }

    public user_profile_model_class(String  user_posts,String post_description) {
        this.user_posts = user_posts;
        this.post_description=post_description;
    }

    public String getPost_description() {
        return post_description;
    }

    public void setPost_description(String post_description) {
        this.post_description = post_description;
    }

    public String getUser_posts() {
        return user_posts;
    }

    public void setUser_posts(String user_posts) {
        this.user_posts = user_posts;
    }
}
