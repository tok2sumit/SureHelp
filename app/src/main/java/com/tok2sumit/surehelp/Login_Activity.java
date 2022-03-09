package com.tok2sumit.surehelp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Login_Activity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView google,facebook;

    @Override
    protected void onCreate(Bundle savedInstanceStae) {
        super.onCreate(savedInstanceStae);
        setContentView(R.layout.activity_login2);

        tabLayout = findViewById(R.id.tbl_layout);
        viewPager = findViewById(R.id.view_pager);
        google = findViewById(R.id.googleLogin);
        facebook = findViewById(R.id.facebookLogin);

//        Adding a Tab Layout
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Signup"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }

}
