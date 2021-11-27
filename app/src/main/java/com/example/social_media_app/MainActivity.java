package com.example.social_media_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.social_media_app.Adapters.main_screen_fragments_adapter;
import com.example.social_media_app.animationss.DepthPageTransformer;
import com.example.social_media_app.databinding.ActivityMainBinding;
import com.example.social_media_app.fragments.main_screen_fragment;
import com.example.social_media_app.fragments.user_profile_fragment;
import com.example.social_media_app.holder_fragments.main_screen_holder_fragment;
import com.example.social_media_app.holder_fragments.search_screen_holder_fragment;
import com.example.social_media_app.signing.signup_activity;
import com.example.social_media_app.signing.username_fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseUser user;
    TabLayout tabLayout;
    ViewPager viewPager;
    //private static MainActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        main_screen_fragment main_screen_fragment=new main_screen_fragment();
//        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.linear,main_screen_fragment);
//        transaction.commit();
//        Toolbar toolbar=(Toolbar) findViewById(R.id.main_screen_toolbar);
//        setSupportActionBar(toolbar);
        
        viewPager=findViewById(R.id.main_screen_viewpager);
        viewPager.setAdapter(new main_screen_fragments_adapter(getSupportFragmentManager()));
        tabLayout=findViewById(R.id.main_screen_tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setPageTransformer(false,new DepthPageTransformer());
        //instance=this;
        viewPager.setOffscreenPageLimit(2);




//        binding.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                user=FirebaseAuth.getInstance().getCurrentUser();
//                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful())
//                        {
//                            Toast.makeText(MainActivity.this, "user deleted successfully", Toast.LENGTH_SHORT).show();
//                            Intent intent=new Intent(MainActivity.this, signup_activity.class);
//                            startActivity(intent);
//                        }
//                        else
//                        {
//                            Toast.makeText(MainActivity.this, "Error,user cant be deleted", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//        });
//        binding.signout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent=new Intent(MainActivity.this, signup_activity.class);
//                startActivity(intent);
//            }
//        });
//
//        binding.messageSent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String message=binding.message.getText().toString();
//                databasing databasing=new databasing();
//                databasing.addmessage(message);
//            }
//        });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_screen_menu,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId())
//        {
//            case R.id.post:
//                Toast.makeText(this, "add a post", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.story:
//                Toast.makeText(this, "add a story", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.chats:
//                Toast.makeText(this, "Recent Chats", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//        }
//        return true;
//    }

//    public static MainActivity getInstance()
//    {
//        return instance;
//    }
////
//    public void switch_fragment(){
//        user_profile_fragment user_profile_fragment=new user_profile_fragment();
//        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.linearrrrrr,user_profile_fragment)
//                .addToBackStack(null)
//                .commitNow();
//    }


    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem()==0)
        {
            int size= main_screen_holder_fragment.getInstance().main_screen_stack_size();
            if(size==0)
            {
                super.onBackPressed();
            }
            else
            {
                main_screen_holder_fragment.getInstance().main_screen_pop();
            }
        }
        else if(viewPager.getCurrentItem()==1)
        {
            int size= search_screen_holder_fragment.getInstance().search_screen_stack_size();
            if(size==0)
            {
                viewPager.setCurrentItem(0,true);
            }
            else
            {
                search_screen_holder_fragment.getInstance().search_screen_pop();
            }
        }
        else if(viewPager.getCurrentItem()==2)
        {
            viewPager.setCurrentItem(1,true);
        }
    }
}