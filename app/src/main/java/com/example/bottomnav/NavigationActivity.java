package com.example.bottomnav;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.bottomnavview.BottomNavView;

public class NavigationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        ViewPager vp = findViewById(R.id.vp);
        BottomNavView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setupWithViewPager(vp);
        vp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            MyFragment fragment = new MyFragment();
            Bundle bundle = new Bundle();
            if (i == 0) {
                bundle.putString("des", "首页");
            } else if (i == 1) {
                bundle.putString("des", "购车");
            } else {
                bundle.putString("des", "我的");
            }
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
