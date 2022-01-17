package com.example.social_media_app.databasing;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.social_media_app.fragments.edit_profile_fragment;
import com.example.social_media_app.holder_fragments.profile_holder_fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class databasing_write {
    String username, fullname, email, password, gender, userid, save_userid,profile_pic,last_pic,user_description;
    int age;

    Map<String, Object> map = new HashMap<>();

    FirebaseFirestore db;

    public databasing_write() {

    }

    public databasing_write(String userid, String save_userid,String gender,String username,String fullname,String profile_pic,String last_pic,String user_description) {
        this.userid = userid;
        this.save_userid = save_userid;
        this.gender=gender;
        this.username=username;
        this.fullname=fullname;
        this.profile_pic=profile_pic;
        this.last_pic=last_pic;
        this.user_description=user_description;
    }

    public databasing_write(Map<String, Object> map, String userid, String gender) {
        this.map = map;
        this.userid = userid;
        this.gender = gender;
    }

    public databasing_write(String username, String fullname, String email, String password, String gender, int age, String userid) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.userid = userid;
    }

    public databasing_write(String username, String fullname, String email, String gender, String userid, int age) {
        this.username = username;
        this.fullname = fullname;
        this.gender = gender;
        this.age = age;
        this.userid = userid;
        this.email = email;
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

    public String getSave_userid() {
        return save_userid;
    }

    public void setSave_userid(String save_userid) {
        this.save_userid = save_userid;
    }

    public void create_account() {
        db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("Username", username);
        user.put("Fullname", fullname);
        user.put("Email", email);
        user.put("Password", password);
        user.put("age", age);
        user.put("timestamp", System.currentTimeMillis());
        user.put("Profile_image", null);
        user.put("Description", null);
        user.put("Country", "Not selected");
        user.put("State", null);
        user.put("City", null);
        user.put("gender", gender);
        user.put("latest_pic", null);
        user.put("post_description", null);
        db.collection(gender).document(userid)
                .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("data_upload_success", "Data is successfully written");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("data_upload_failed", "Data upload is failed");
            }
        });
    }

    public void create_account_google() {
        db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("Username", username);
        user.put("Fullname", fullname);
        user.put("age", age);
        user.put("Email", email);
        user.put("timestamp", System.currentTimeMillis());
        user.put("Profile_image", null);
        user.put("Description", null);
        user.put("Country", "Not selected");
        user.put("State", null);
        user.put("City", null);
        user.put("gender", gender);
        user.put("latest_pic", null);
        user.put("post_description", null);
        db.collection(gender).document(userid)
                .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("data_upload_success", "Data is successfully written");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("data_upload_failed", "Data upload is failed");
            }
        });
    }

    public void update_account(String image) {
        db = FirebaseFirestore.getInstance();
        db.collection(gender).document(userid).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                if(image==null) {
                    profile_holder_fragment.getInstance().profile_screen_pop();
                    edit_profile_fragment.getInstance().cancel_progress_dialog();
                }
                Log.d("success", "successfully updated");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//                profile_holder_fragment.getInstance().profile_screen_pop();
//                edit_profile_fragment.getInstance().cancel_progress_dialog();
                Log.d("failure", "update failed");
            }
        });
    }

    public void save_profile(Boolean saved_profile) {
        if (!saved_profile) {
            db = FirebaseFirestore.getInstance();
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> map1 = new HashMap<>();
            map.put("saved", save_userid);
            map.put("username",username);
            map.put("fullname",fullname);
            map.put("profile_pic",profile_pic);
            map.put("last_pic",last_pic);
            map.put("user_description",user_description);
            map.put("save_time", System.currentTimeMillis());
            map1.put(userid + save_userid, true);
            db.collection("likings").document("saved_profiles").collection(userid).document(save_userid).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("likings_success", "success");
                    db.collection(gender).document(save_userid).set(map1, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d("success","success");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("failure","failure");
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("likings_failed", "failed");
                }
            });
        }
        else
        {
            db=FirebaseFirestore.getInstance();
            db.collection("likings").document("saved_profiles").collection(userid).document(save_userid).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("success","successfullly_deleted");
                    Map<String, Object> map1 = new HashMap<>();
                    map1.put(userid + save_userid, false);
                    db.collection(gender).document(save_userid).set(map1,SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d("success","success");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("failure","failure");
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("failure","cannot delete the saved_profile");
                }
            });
        }
    }

}
