package com.tok2sumit.surehelp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;

public class Onboard extends AppCompatActivity {
    ImageView logo,backimg;
    LottieAnimationView lottieAnimationView;

    private static final int NUM_PAGES = 3;
    private ViewPager viewPager;
    private Onboard.ScreenSlidePagerAdapter pagerAdapter;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

//        for splash Activity
//        logo = findViewById(R.id.imgsplashlogo);
//        lottieAnimationView = findViewById(R.id.spash_lottie);
//
//        logo.animate().translationY(1400).setDuration(1000).setStartDelay(4000);



        viewPager = findViewById(R.id.pager);
        pagerAdapter = new Onboard.ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(@NonNull FragmentManager fragmentManager) {
            super(fragmentManager);
        }


        @NonNull
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    OnBoardingFragment1 tab1 = new OnBoardingFragment1();
                    return tab1;

                case 1:
                    OnBoardingFragment2 tab2 = new OnBoardingFragment2();
                    return tab2;

                case 2:
                    OnBoardingFragment3 tab3 = new OnBoardingFragment3();
                    return tab3;
            }
            return null;
        }


        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
