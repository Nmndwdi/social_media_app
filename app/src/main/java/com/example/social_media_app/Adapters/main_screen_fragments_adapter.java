package com.example.social_media_app.Adapters;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.social_media_app.fragments.Search_fragment;
import com.example.social_media_app.fragments.main_screen_fragment;
import com.example.social_media_app.fragments.profile_fragment;
import com.example.social_media_app.fragments.user_profile_fragment;
import com.example.social_media_app.holder_fragments.main_screen_holder_fragment;
import com.example.social_media_app.holder_fragments.profile_holder_fragment;
import com.example.social_media_app.holder_fragments.search_screen_holder_fragment;
import com.example.social_media_app.signing.login_fragment;

public class main_screen_fragments_adapter extends FragmentPagerAdapter {
    public main_screen_fragments_adapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
//                Log.d("firstview","first page of viewpager");
                return new main_screen_holder_fragment();
            case 1:
//                Log.d("secondview","second page of viewpager");
                return new search_screen_holder_fragment();
            case 2:
                return new profile_holder_fragment();
            default:
                return new main_screen_holder_fragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        switch (position)
        {
            case 0:
                title="Home";
                break;
            case 1:
                title="Search";
                break;
            case 2:
                title="Profile";
                break;
            default:
                break;

        }
        return title;
    }
}
