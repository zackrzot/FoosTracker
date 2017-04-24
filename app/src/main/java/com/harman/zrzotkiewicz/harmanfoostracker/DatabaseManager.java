package com.harman.zrzotkiewicz.harmanfoostracker;

import android.util.Log;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DatabaseManager {

    static final String webAddress = "http://autotest.harman.com/";
    static final String url = "jdbc:mysql://autotest.harman.com:3306/FoosTracker";
    static final String user = "update_worker";
    static final String pass = "harman@123";

    // Must be ran on own thread
    public static Boolean IsWebAppOnline() {
        try {
            URL url = new URL(webAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int code = connection.getResponseCode();
            if (code == 200)
                return true;
        }
        catch (Exception ex){
            Log.e("ERROR", "Exception caught when checking web address: " + ex);
        }
        return false;
    }

    public static int GetTotalNumberOfGames(){
        return 0;
    }

    public static int GetTotalNumberOfGoals(){
        return 0;
    }

}
