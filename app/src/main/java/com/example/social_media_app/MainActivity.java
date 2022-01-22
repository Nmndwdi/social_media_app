package com.example.social_media_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.social_media_app.Adapters.main_screen_fragments_adapter;
import com.example.social_media_app.animationss.DepthPageTransformer;
import com.example.social_media_app.databasing.databasing_write;
import com.example.social_media_app.databinding.ActivityMainBinding;
import com.example.social_media_app.fragments.chat_detail_fragment;
import com.example.social_media_app.fragments.chats_fragment;
import com.example.social_media_app.holder_fragments.likings_screen_holder_fragment;
import com.example.social_media_app.holder_fragments.main_screen_holder_fragment;
import com.example.social_media_app.holder_fragments.profile_holder_fragment;
import com.example.social_media_app.holder_fragments.search_screen_holder_fragment;
import com.example.social_media_app.signing.signup_activity;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    TabLayout tabLayout;
    ViewPager viewPager;
    int backButtonCount=0;
    private static String gender_key;
    private static String gender;

    private static MainActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewPager=findViewById(R.id.main_screen_viewpager);
        viewPager.setAdapter(new main_screen_fragments_adapter(getSupportFragmentManager()));
        tabLayout=findViewById(R.id.main_screen_tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setPageTransformer(false,new DepthPageTransformer());
        instance=this;
        viewPager.setOffscreenPageLimit(3);
        SharedPreferences sharedPreferences=getSharedPreferences("gender_file",MODE_PRIVATE);
        gender=sharedPreferences.getString("user_gender_key","");
        gender_key=sharedPreferences.getString("gender_key","");
    }

    public String getGender_key()
    {
        return gender_key;
    }

    public String getGender()
    {
        return gender;
    }

    public static MainActivity getInstance()
    {
        return instance;
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem()==0)
        {
            int size= main_screen_holder_fragment.getInstance().main_screen_stack_size();
            if(size==0)
            {
                if(backButtonCount >= 1)
                {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    backButtonCount=0;
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Press again to exit", Toast.LENGTH_SHORT).show();
                    backButtonCount++;
                    Thread thread=new Thread()
                    {
                        public void run()
                        {
                            try {
                                sleep(2000);
                                backButtonCount=0;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    thread.start();
                }
            }
            else
            {
                backButtonCount=0;
                main_screen_holder_fragment.getInstance().main_screen_pop();
            }
        }
        else if(viewPager.getCurrentItem()==1)
        {
            backButtonCount=0;
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
            backButtonCount=0;
            int size= likings_screen_holder_fragment.getInstance().likings_fragment_stack_size();
            if(size==0)
            {
                viewPager.setCurrentItem(1,true);
            }
            else
            {
                likings_screen_holder_fragment.getInstance().likings_screen_pop();
            }
        }
        else if(viewPager.getCurrentItem()==3)
        {
            backButtonCount=0;
            int size= profile_holder_fragment.getInstance().profile_screen_stack_size();
            if(size==0) {
                viewPager.setCurrentItem(2, true);
            }
            else
            {
                profile_holder_fragment.getInstance().profile_screen_pop();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            chats_fragment chats_fragment=new chats_fragment();
            chats_fragment.show(getSupportFragmentManager(),chats_fragment.getTag());
        }
        return super.onTouchEvent(event);
    }

    public void switch_chat_detail_fragment()
    {
        chat_detail_fragment chat_detail_fragment=new chat_detail_fragment();
        search_screen_holder_fragment.getInstance().search_screen_user_profile_fragment_push();
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.search_screen_holder,chat_detail_fragment).commit();
    }

    public void switch_user_chat_fragment()
    {
        chat_detail_fragment chat_detail_fragment=new chat_detail_fragment();
        main_screen_holder_fragment.getInstance().main_screen_user_profile_fragment_push();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_screen_holder,chat_detail_fragment).commit();
    }

    public void switch_liking_chat_fragment()
    {
        chat_detail_fragment chat_detail_fragment=new chat_detail_fragment();
        likings_screen_holder_fragment.getInstance().likings_screen_user_profile_push();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.likings_screen_holder,chat_detail_fragment).commit();
    }

}