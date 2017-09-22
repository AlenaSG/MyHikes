package com.epicodus.my_hikes;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import okhttp3.OkHttpClient;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;
import okhttp3.Call;
import okhttp3.Request;

import static android.content.ContentValues.TAG;

/**
 * Created by alenagolovina on 9/16/17.
 */

public class HikesServiceJava {
    public static void findHikes(String city, Callback callback) {

        OkHttpClient client = new OkHttpClient();

        //construct the URL
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.HIKES_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("q[city_cont]", city);
        String url = urlBuilder.build().toString();
        Log.v(TAG, "Request URL: " + url);

        Request request = new Request.Builder()
                .url(url)
                .header("X-Mashape-Key", Constants.HIKES_MASHAPE_KEY)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }


    public ArrayList<Hike> processResults(Response response) {
        ArrayList<Hike> hikes = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject hikesJSON = new JSONObject(jsonData);
                JSONArray placesJSON = hikesJSON.getJSONArray("places");
                for (int i = 0; i < placesJSON.length(); i++) {
                    JSONObject hikeJSON = placesJSON.getJSONObject(i);
                    String name = hikeJSON.getString("name");
                    String directions = hikeJSON.optString("directions");
                    double lat = hikeJSON.getDouble("lat");
                    double lon = hikeJSON.getDouble("lon");

                    JSONArray activitiesJSON = hikeJSON.getJSONArray("activities");
                    ArrayList<Activity> activities = new ArrayList<Activity>();
                    for (int j = 0; j < activitiesJSON.length(); j++) {
                        JSONObject activityJSON = activitiesJSON.getJSONObject(j);
                        double length = activityJSON.getDouble("length");
                        String url = activityJSON.getString("url");
                        String description = activityJSON.getString("description");
                        activities.add(new Activity(description, url, length));
                    }

                    Hike hike = new Hike(name, directions);
                    hikes.add(hike);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hikes;
    }
}
