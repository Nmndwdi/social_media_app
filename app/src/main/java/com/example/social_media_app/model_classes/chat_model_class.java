package com.example.social_media_app.model_classes;

public class chat_model_class {
    String userid, message;
    Long timestamp;

    public chat_model_class()
    {

    }
    public chat_model_class(String userid, String message, Long timestamp) {
        this.userid = userid;
        this.message = message;
        this.timestamp = timestamp;
    }
    public chat_model_class(String userid, String message) {
        this.userid = userid;
        this.message = message;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
