package com.example.adds;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class OnBoarding extends AppCompatActivity {

    ViewPager mSLideViewPager;
    LinearLayout mDotLayout;
    Button backbtn, nextbtn, skipbtn;

    TextView[] dots;
    ViewPageAdapter viewPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        SharedPreferences preferences = getSharedPreferences("PREFERENCES", MODE_PRIVATE);
        String FirstTime = preferences.getString("FirstTimeInstall", "yes");

        if (FirstTime.equals("no")){
            Intent intent = new Intent(OnBoarding.this, MainActivity.class);
            startActivity(intent);
            finish();

        }else{
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstTimeInstall", "no");
            editor.apply();
        }

        backbtn = findViewById(R.id.backButton);
        nextbtn = findViewById(R.id.nextButton);
        skipbtn = findViewById(R.id.SkipButton);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getitem(0) > 0){
                    mSLideViewPager.setCurrentItem(getitem(-1),true);
                }
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 2)
                    mSLideViewPager.setCurrentItem(getitem(1),true);
                else {
                    Intent i = new Intent(OnBoarding.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OnBoarding.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        mSLideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.indicator_layout);

        viewPageAdapter = new ViewPageAdapter(this);
        mSLideViewPager.setAdapter(viewPageAdapter);

        setUpindicator(0);
        mSLideViewPager.addOnPageChangeListener(viewListener);
    }

    public void setUpindicator(int position){
        dots = new TextView[3];
        mDotLayout.removeAllViews();

        for(int i =0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);
        }

        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            setUpindicator(position);
            if (position > 0){
                backbtn.setVisibility(View.VISIBLE);
            }else {
                backbtn.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    private int getitem(int i){
        return mSLideViewPager.getCurrentItem() + i;
    }
}