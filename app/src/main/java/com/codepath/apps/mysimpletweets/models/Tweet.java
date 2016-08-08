package com.codepath.apps.mysimpletweets.models;



// Parse the json + store the data,  encapsulate state logic or display logic

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tweet {

    private  String body;
    private  long uid;
    private User user;
    private String createdat;

    public User getUser() {
        return user;
    }

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public String getCreatedat() {
        return createdat;
    }
// deserialize the json
    // tweet.fron json

    public static Tweet fromJSon(JSONObject jsonObject){

        Tweet tweet = new Tweet();
        // extract the value from the json and store them
        try {
            tweet.body= jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdat = jsonObject.getString("created_at");
             tweet.user= User.fromJSON(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return tweet;
    }
    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray){
        ArrayList<Tweet> tweets= new ArrayList<>();

        for (int i=0; i < jsonArray.length();i++){
            try {
                JSONObject tweetJson = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSon(tweetJson);
                if (tweet != null) {
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }

        return tweets;

    }

}
