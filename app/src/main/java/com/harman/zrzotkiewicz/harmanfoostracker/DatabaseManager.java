package com.harman.zrzotkiewicz.harmanfoostracker;

import android.util.Log;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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
        } catch (Exception ex) {
            Log.e("ERROR", "Exception caught when checking web address: " + ex);
        }
        return false;
    }

    public static int GetTotalNumberOfGames() {
        return 0;
    }

    public static int GetTotalNumberOfGoals() {
        return 0;
    }


    public static List<String> GetPlayerNames(){
        final List<String> names = new ArrayList<>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://10.34.150.151:3306/FoosTracker", "zrzot", "harman@123");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select NAME from PLAYERS");
                    while (rs.next())
                        names.add(rs.getString(1));
                    con.close();
                } catch (Exception e) {
                    Log.d("[[SQL ERR]]", e.toString());
                }
            }});
        // Start thread
        t.start();
        // Wait for thread to finish
        try {  t.join(); }
        catch (Exception e) { Log.d("[[SQL THREAD ERR]]", e.toString()); }
        // Return list of names
        return names;
    }


    public static List<String> GetUsedJerseyNumbers(){
        final List<String> names = new ArrayList<>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://10.34.150.151:3306/FoosTracker", "zrzot", "harman@123");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select JERSEY from PLAYERS");
                    while (rs.next())
                        names.add(rs.getString(1));
                    con.close();
                } catch (Exception e) {
                    Log.d("[[SQL ERR]]", e.toString());
                }
            }});
        // Start thread
        t.start();
        // Wait for thread to finish
        try {  t.join(); }
        catch (Exception e) { Log.d("[[SQL THREAD ERR]]", e.toString()); }
        // Return list of names
        return names;
    }


    public static Boolean AddNewPlayerToDatabase(final PlayerData playerData){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://10.34.150.151:3306/FoosTracker", "zrzot", "harman@123");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("CALL insertPlayer("+playerData.Pin+", \"\", "+playerData.PlayerName+", "+playerData.DOB+", "+playerData.Hometown+", "+playerData.Bio+", "+playerData.JerseyNumber+", "+playerData.Handedness+", "+playerData.Height+", "+playerData.Weight+")");
                    while (rs.next())
                        Log.d("[[SQL]]", rs.toString());
                    con.close();
                } catch (Exception e) {
                    Log.d("[[SQL ERR]]", e.toString());
                }
            }});
        // Start thread
        t.start();
        // Wait for thread to finish
        try {  t.join(); }
        catch (Exception e) { Log.d("[[SQL THREAD ERR]]", e.toString()); }
        // Return list of names
        return true;
    }


}

