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

public class WebAPIHelper {

    final static String webAddress = "http://192.168.1.8";

    private static JSONArray getFromAPI(String apiUrl){
        String stringJson = null;
        JSONArray jsonResult = new JSONArray();
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(webAddress + apiUrl);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                stringJson = sb.toString();
                is.close();
                Log.e("API Response", stringJson);
            } catch (Exception e) {
                Log.e("log_tag", "Error converting result " + e.toString());
            }
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }
        // Parse string to json object
        try {
            jsonResult = new JSONArray(stringJson);
        }
        catch(Exception e) {
            Log.e("ERROR", "Error parsing data " + e.toString());
        }
        return  jsonResult;
    }

    private static int getIntFromJson(JSONArray jsonArray, String key){
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json_data = jsonArray.getJSONObject(i);
                return json_data.getInt("COUNT(*)");
            }
        }
        catch (Exception ex){
            Log.e("ERROR", "getIntFromJson: Unable to locate key '" + key + "' : " + ex.toString());
        }
        return 0;
    }

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
        return getIntFromJson(getFromAPI("/Database/PHP/GetTotalGames.php"),"COUNT(*)");
    }

    public static int GetTotalNumberOfGoals(){
        return getIntFromJson(getFromAPI("/Database/PHP/GetTotalGoals.php"),"COUNT(*)");
    }

}
