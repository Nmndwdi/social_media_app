package com.example.social_media_app.databasing;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class databasing_write {
    String username,fullname,email,password,gender,userid;
    int age;

    Map<String,Object> map=new HashMap<>();

    FirebaseFirestore db;

    public databasing_write()
    {

    }

    public databasing_write(Map<String, Object> map,String userid,String gender) {
        this.map = map;
        this.userid=userid;
        this.gender=gender;
    }

    public databasing_write(String username, String fullname, String email, String password, String gender, int age, String userid) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.age=age;
        this.gender=gender;
        this.userid=userid;
    }

    public databasing_write(String username, String fullname,String email, String gender,String userid, int age) {
        this.username = username;
        this.fullname = fullname;
        this.gender = gender;
        this.age = age;
        this.userid=userid;
        this.email=email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void create_account()
    {
        db=FirebaseFirestore.getInstance();
        Map<String,Object>user=new HashMap<>();
        user.put("Username",username);
        user.put("Fullname",fullname);
        user.put("Email",email);
        user.put("Password",password);
        user.put("age",age);
        user.put("timestamp",System.currentTimeMillis());
        user.put("Profile_image",null);
        user.put("Description",null);
        user.put("Country","Not selected");
        user.put("State",null);
        user.put("City",null);
        user.put("gender",gender);
        user.put("latest_pic",null);
        user.put("post_description",null);
        db.collection(gender).document(userid)
                .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("data_upload_success","Data is successfully written");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("data_upload_failed","Data upload is failed");
            }
        });
    }

    public void create_account_google()
    {
        db=FirebaseFirestore.getInstance();
        Map<String,Object>user=new HashMap<>();
        user.put("Username",username);
        user.put("Fullname",fullname);
        user.put("age",age);
        user.put("Email",email);
        user.put("timestamp",System.currentTimeMillis());
        user.put("Profile_image",null);
        user.put("Description",null);
        user.put("Country","Not selected");
        user.put("State",null);
        user.put("City",null);
        user.put("gender",gender);
        user.put("latest_pic",null);
        user.put("post_description",null);
        db.collection(gender).document(userid)
                .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("data_upload_success","Data is successfully written");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("data_upload_failed","Data upload is failed");
            }
        });
    }

    public void update_account()
    {
        db=FirebaseFirestore.getInstance();
        db.collection(gender).document(userid).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("success","successfully updated");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("failure","update failed");
            }
        });
    }

}
