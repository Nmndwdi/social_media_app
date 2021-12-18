package com.example.social_media_app.databasing;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.social_media_app.signing.username_fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirestoreRegistrar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class databasing_write {
    String username,fullname,email,password,gender,userid;
    int age;

    FirebaseFirestore db;

    public databasing_write()
    {

    }

    public databasing_write(String username, String fullname, String email, String password,String gender,int age,String userid) {
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


    public void getdata()
    {
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot documentSnapshot:task.getResult())
                    {
                        Log.d("fuk", documentSnapshot.getId() + " => " + documentSnapshot.getData());
                    }
                }
                else
                {
                    Log.d("Err","errr");
                }
            }
        });
    }
}
