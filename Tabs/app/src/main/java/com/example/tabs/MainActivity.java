/*App shows 4 tabs that can be scrolled or tapped upon to see a new fragment using ViewPager*/


package com.example.tabs;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);   //setting the toolbar
        TabLayout tabLayout = findViewById(R.id.tab_layout); //setting tool bar layout
        tabLayout.addTab(tabLayout.newTab().setText(R.string.hello_blank_fragment));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tech_stories));     //setting text for each tab
        tabLayout.addTab(tabLayout.newTab().setText(R.string.cooking_tips));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.test));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);   //set tabs to fill entire layout
        final ViewPager viewPager = findViewById(R.id.pager);  //creating instance of a viewpager
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount()); //passing fragment object and tab count to pageadapter class
        viewPager.setAdapter(adapter); //attaching pageadapter to viewpage layout manager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout)); //listener for page change
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {  //listener for tab selection
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("HEY", "calling ontab selected and hence the method below");
                viewPager.setCurrentItem(tab.getPosition());   /*okay, the view pager by default creates the fragment for the current page and the next one and keeps it in the fragment manager
                I read that in stack overflow and i verified it with Log statements, getitem is initially called twice, to get the first fragment and to get the next fragment, if i swipe on the second
                tab there is one call to onselected and the other to getitem,to fetch the third tab,however if i jump to the third tab from the first tab itself,there are two calls to getitem,
                one to get the third tab and the other to get the fourth tab. appropriately selecting the last tab gives no call to getitem.same follows if i swipe from the reverse order*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
