package com.codepath.apps.mysimpletweets;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.fragments.UserTimelineFragment;
import com.codepath.apps.mysimpletweets.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ProfileActivity extends AppCompatActivity {
    TwitterClient client;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        client= TwitterApplication.getRestClient();
        //get the account info
        client.getUserInfo(new JsonHttpResponseHandler() {
                               @Override
                               public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                   user = User.fromJSON(response);
                                   //my current user account info
                                   getSupportActionBar().setTitle("@" + user.getScreenName());
                                   populateProfileHeader(user);
                               }
                           });

            String screenName = getIntent().getStringExtra("screen_name");
        if (savedInstanceState == null) {
            UserTimelineFragment fragmentUserTimeline = UserTimelineFragment.newInstance(screenName);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContainer, fragmentUserTimeline);
            ft.commit();
        }
    }


    private void populateProfileHeader(User user) {
        TextView tvName= (TextView)findViewById(R.id.tvFullName);
        TextView tvTagLine= (TextView)findViewById(R.id.tvTagLine);
        TextView tvFollowers= (TextView)findViewById(R.id.tvFollowers);
        TextView tvFollowing=(TextView)findViewById(R.id.tvFollowing);
        ImageView ivProfileImage = (ImageView)findViewById(R.id.ivProfileImage);
        tvName.setText(user.getName());
        tvTagLine.setText(user.getTagLine());
        tvFollowers.setText(user.getFollowersCount() +"followers");
        tvFollowing.setText(user.getFriendsCount() + "following");
        Picasso.with(this).load(user.getProfileImageUrl()).into(ivProfileImage);
    }
}