package com.example.social_media_app.model_classes;

public class likings_horizontal_model_class {
    String image,fullname;
    public likings_horizontal_model_class()
    {

    }

    public likings_horizontal_model_class(String image, String fullname) {
        this.image = image;
        this.fullname = fullname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
