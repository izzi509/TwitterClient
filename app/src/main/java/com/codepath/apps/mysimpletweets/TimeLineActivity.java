package com.codepath.apps.mysimpletweets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.mysimpletweets.fragments.HomeTimelineFragment;
import com.codepath.apps.mysimpletweets.fragments.MentionsTimelineFragment;


public class TimeLineActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        // get the view Pager
        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
        // set the view adapter for the pager
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager()));
        //find the sliding tabstrip
        PagerSlidingTabStrip tabStrip= (PagerSlidingTabStrip) findViewById(R.id.tabs);
        //attach the tabstrip to the viewer
        tabStrip.setViewPager(vpPager);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void onProfileView(MenuItem mi){
        //launch the profile view
        Intent i =new Intent(this,ProfileActivity.class);
        startActivity(i);
    }

// return the order of the fragment
public class TweetsPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = {"Home","Mentions"};
// adapter gets the manager insert or remove fragment from activity
    public TweetsPagerAdapter(FragmentManager fm){
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new HomeTimelineFragment();
        }
        else if (position == 1){
            return new MentionsTimelineFragment();
        }else {
            return  null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
    }




