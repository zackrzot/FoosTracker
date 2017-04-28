package com.harman.zrzotkiewicz.harmanfoostracker;

import android.util.Log;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseManager {

    static final String webAddress = "http://autotest.harman.com/";
    static final String url = "jdbc:mysql://10.34.150.151:3306/FoosTracker";
    static final String user = "update_worker";
    static final String password = "harman@123";

    private static Boolean sqlError;

    private static Boolean sqlUpdate(final String query){
        sqlError = false;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stmt = con.createStatement();
                    Log.d("[[SQL QRY]]", query);
                    stmt.executeQuery(query);
                    con.close();
                } catch (Exception e) {
                    Log.d("[[SQL ERR]]", e.toString());
                    sqlError = true;
                }
            }});
        // Start thread
        t.start();
        // Wait for thread to finish
        try {  t.join(); }
        catch (Exception e) { Log.d("[[SQL THREAD ERR]]", e.toString()); sqlError = true; }
        // Return
        if(sqlError)
            return false;
        return true;
    }

    private static List<String> sqlSelect(final String query){
        final List<String> values = new ArrayList<>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                        values.add(rs.getString(1));
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
        return values;
    }

    private static int sqlCount(final String query){
        final List<String> values = new ArrayList<>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                        values.add(rs.getString(1));
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
        try{
            return Integer.parseInt(values.get(0));
        }
        catch(Exception ex) {
            Log.d("[[SQL THREAD ERR]]", ex.toString());
            return 0;
        }
    }

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
        return sqlCount("SELECT COUNT(*) FROM GAMES");
    }

    public static int GetTotalNumberOfGoals() {
        return sqlCount("SELECT COUNT(*) FROM POINTS");
    }

    public static List<String> GetPlayerNames(){
        return sqlSelect("select NAME from PLAYERS");
    }

    public static List<String> GetUsedJerseyNumbers(){
        return sqlSelect("select JERSEY from PLAYERS");
    }

    public static Boolean AddNewPlayerToDatabase(final PlayerData playerData){
        String query = "CALL insertPlayer('"+playerData.Pin+"'," +
                " '"+playerData.PhotoBlob+"'," +
                " '"+Utility.FixInvalidChars(playerData.PlayerName)+"'," +
                " '"+playerData.DOB+"'," +
                " '"+Utility.FixInvalidChars(playerData.Hometown)+"'," +
                " '"+Utility.FixInvalidChars(playerData.Bio)+"'," +
                " '"+playerData.JerseyNumber+"'," +
                " '"+playerData.Handedness+"'," +
                " '"+playerData.Height+"'," +
                " '"+playerData.Weight+"');";
        return sqlUpdate(query);
    }


}

