package com.example.social_media_app.model_classes;

public class profile_model_class {
    String post,post_description;

    public profile_model_class()
    {

    }
    public profile_model_class(String post,String post_description) {
        this.post = post;
        this.post_description=post_description;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPost_description() {
        return post_description;
    }

    public void setPost_description(String post_description) {
        this.post_description = post_description;
    }
}
