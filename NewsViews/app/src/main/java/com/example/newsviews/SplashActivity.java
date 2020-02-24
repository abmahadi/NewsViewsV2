package com.example.newsviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotLayout;
    private Button nextBTN;
    private Button skipBTN;

    private TextView[] dotsTV;
    private int[] layouts;

    private MyPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStatusBarTransparent();
        setContentView(R.layout.activity_splash);

        if(!isFirstTimeStartApp())
        {
            startMainActivity();
            finish();
        }

        init();

        setStatusBarTransparent();
        //when user press skip main activity start
        skipBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startMainActivity();

            }
        });

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int currentPage =viewPager.getCurrentItem()+1;
                if(currentPage <layouts.length)
                {
                    //move to next page
                    viewPager.setCurrentItem(currentPage);
                }else {
                    startMainActivity();
                }


            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position == layouts.length-1)
                {
                    //for last position
                    nextBTN.setText("Start");
                    skipBTN.setVisibility(View.GONE);

                }else{
                    nextBTN.setText("Next");
                    skipBTN.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private  boolean isFirstTimeStartApp()
    {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("introNewsViewsApp", Context.MODE_PRIVATE);
        return  preferences.getBoolean("FirstTimeStartFlag",true);

    }

    private void setFirstTimeStartStatus(boolean startStatus)
    {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("introNewsViewsApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("FirstTimeStartFlag",startStatus);
        editor.commit();
    }

    // for Dot
    private void setDotStatus(int page) {
        dotLayout.removeAllViews();
        dotsTV = new TextView[layouts.length];

    }

    private void startMainActivity() {

        setFirstTimeStartStatus(false);

        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    private void setStatusBarTransparent() {

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void init() {
        viewPager = findViewById(R.id.splash_ViewPager);
        dotLayout = findViewById(R.id.dot_layout);
        nextBTN = findViewById(R.id.next_BTN);
        skipBTN = findViewById(R.id.skip_BTN);
        layouts = new int[]{R.layout.my_info_slider, R.layout.app_name_slider, R.layout.my_project_slider};
        pagerAdapter = new MyPagerAdapter(layouts, getApplicationContext());
        viewPager.setAdapter(pagerAdapter);

    }
}
