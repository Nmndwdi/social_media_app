package com.example.social_media_app.model_classes;

public class chat_screen_model_class {
    String username,last_message;
    public chat_screen_model_class()
    {

    }

    public chat_screen_model_class(String username, String last_message) {
        this.username = username;
        this.last_message = last_message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }
}
