package com.harman.zrzotkiewicz.harmanfoostracker;

import android.util.Log;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;


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


    public static void query() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://10.34.150.151:3306/FoosTracker", "zrzot", "harman@123");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from GAMES");
            while (rs.next())
                Log.d("[[SQL]]", rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();
        } catch (Exception e) {
            Log.d("[[SQL ERR]]", e.toString());
        }
    }

}

