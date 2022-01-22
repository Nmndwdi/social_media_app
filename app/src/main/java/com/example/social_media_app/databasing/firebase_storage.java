package com.example.social_media_app.databasing;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.social_media_app.fragments.edit_profile_fragment;
import com.example.social_media_app.fragments.post_fragment;
import com.example.social_media_app.holder_fragments.profile_holder_fragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class firebase_storage {
    String image_uri,userid,post_description,gender;

    FirebaseFirestore db;
    StorageReference storageReference;

    public firebase_storage()
    {

    }

    public firebase_storage(String image_uri, String userid,String gender) {
        this.image_uri = image_uri;
        this.userid = userid;
        this.gender=gender;
    }

    public firebase_storage(String image_uri, String userid, String post_description,String gender) {
        this.image_uri = image_uri;
        this.userid = userid;
        this.post_description = post_description;
        this.gender=gender;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }

    public String getPost_description() {
        return post_description;
    }

    public void setPost_description(String post_description) {
        this.post_description = post_description;
    }

    public void create_post()
    {
        if(image_uri!=null)
        {
            StorageReference fileref=FirebaseStorage.getInstance().getReference("latest_pic").child(userid);
            fileref.putFile(Uri.parse(image_uri)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    StorageReference fileref1=FirebaseStorage.getInstance().getReference("post_image").child(userid).child(System.currentTimeMillis()+".");
                    fileref1.putFile(Uri.parse(image_uri)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileref1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    db=FirebaseFirestore.getInstance();
                                    Map<String,Object>map=new HashMap<>();
                                    map.put("post",uri.toString());
                                    map.put("post_description",post_description);
                                    db.collection(gender).document(userid).update("latest_pic",uri.toString(),"post_description",post_description,"Posts",FieldValue.arrayUnion(map)).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            profile_holder_fragment.getInstance().profile_screen_pop();
                                            post_fragment.getInstance().cancel_progress_dialog();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("fireuperr","image_upload_unsuccessful");
                }
            });
        }
    }

    public void profile_picture()
    {
        storageReference=FirebaseStorage.getInstance().getReference("profile_picture");
        if(image_uri!=null)
        {
            StorageReference fileref=storageReference.child(userid);
            fileref.putFile(Uri.parse(image_uri)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            db=FirebaseFirestore.getInstance();
                            Map<String,Object>map=new HashMap<>();
                            map.put("Profile_image",uri.toString());
                            db.collection(gender).document(userid).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    profile_holder_fragment.getInstance().profile_screen_pop();
                                    edit_profile_fragment.getInstance().cancel_progress_dialog();
                                    Log.d("success","success");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
//                                            profile_holder_fragment.getInstance().profile_screen_pop();
//                                            edit_profile_fragment.getInstance().cancel_progress_dialog();
                                    Log.d("fail","failed");
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
//                            profile_holder_fragment.getInstance().profile_screen_pop();
//                            edit_profile_fragment.getInstance().cancel_progress_dialog();
                            Log.d("fail_download_url","url download failed");
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
//                    profile_holder_fragment.getInstance().profile_screen_pop();
//                    edit_profile_fragment.getInstance().cancel_progress_dialog();
                    Log.d("fireuperr","image_upload_unsuccessful");
                }
            });
        }
    }
}
