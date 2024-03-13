package com.example.adds;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.adds.Adapters.ExpandableListAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FAQActivity extends AppCompatActivity {

    private AdView mAdView;
    Toolbar toolbar;
    private List<String> parent_title;
    private HashMap<String,List<String>> child_title;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;

    private static String PARENT_TITLE_ONE="What is a TODO app?";
    private static String PARENT_TITLE_TWO="How do I delete a task?";
    private static String PARENT_TITLE_THREE="Can I share tasks with others?";
    private static String PARENT_TITLE_FOUR="How can I mark a task as complete?";

    private static String CHILD_TITLE_ONE="A TODO app is a digital tool designed to help users organize, manage, and prioritize tasks or activities in a systematic manner. It helps users keep track of their daily, weekly, or long-term tasks efficiently.";
    private static String CHILD_TITLE_TWO="Deleting a task is usually straightforward. Find the task you want to remove and simply right swipe.";
    private static String CHILD_TITLE_THREE="You can't. But you will be able to do the same in the upcoming versions";
    private static String CHILD_TITLE_FOUR="Simply tick the checkbox";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        parent_title=new ArrayList<>();
        child_title=new HashMap<>();
        expandableListAdapter=new ExpandableListAdapter(parent_title,child_title,this);
        expandableListView=(ExpandableListView)findViewById(R.id.expandable_list_view);
        getExpandableList();
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setGroupIndicator(null);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                return true;
            }
        });
    }

    private void getExpandableList() {
        parent_title.add(PARENT_TITLE_ONE);
        parent_title.add(PARENT_TITLE_TWO);
        parent_title.add(PARENT_TITLE_THREE);
        parent_title.add(PARENT_TITLE_FOUR);

        // parent one
        List<String> parent_one=new ArrayList<>();
        parent_one.add(CHILD_TITLE_ONE);

        // parent two
        List<String> parent_two=new ArrayList<>();
        parent_two.add(CHILD_TITLE_TWO);

        // parent three
        List<String> parent_three=new ArrayList<>();
        parent_three.add(CHILD_TITLE_THREE);

        // parent four
        List<String> parent_four=new ArrayList<>();
        parent_four.add(CHILD_TITLE_FOUR);

        // adding all the child with the respective parent
        child_title.put(PARENT_TITLE_ONE,parent_one);
        child_title.put(PARENT_TITLE_TWO,parent_two);
        child_title.put(PARENT_TITLE_THREE,parent_three);
        child_title.put(PARENT_TITLE_FOUR,parent_four);
    }
}