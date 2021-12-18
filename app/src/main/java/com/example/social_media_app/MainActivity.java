package com.example.social_media_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.social_media_app.Adapters.main_screen_fragments_adapter;
import com.example.social_media_app.animationss.DepthPageTransformer;
import com.example.social_media_app.databasing.databasing_write;
import com.example.social_media_app.databinding.ActivityMainBinding;
import com.example.social_media_app.holder_fragments.main_screen_holder_fragment;
import com.example.social_media_app.holder_fragments.search_screen_holder_fragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseUser user;
    TabLayout tabLayout;
    ViewPager viewPager;
    int backButtonCount=0;
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

        databasing_write databasing=new databasing_write();
        databasing.getdata();


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
                    Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
                    backButtonCount++;
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
            viewPager.setCurrentItem(1,true);
        }
    }
}