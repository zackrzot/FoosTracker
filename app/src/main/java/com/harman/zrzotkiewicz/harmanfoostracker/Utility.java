package com.harman.zrzotkiewicz.harmanfoostracker;


import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;

public class Utility {

    public static Bitmap MakeImageSquare(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int extraHeight = height-width;
        int halfOfExtraHeight = (extraHeight / 2);
        return Bitmap.createBitmap(bitmap, 0, halfOfExtraHeight, width, width);
    }

    public static String BitmapToByteArrayBlobString(Bitmap bitmap){
        // If no bitmap available
        if(bitmap == null)
            return "";
        // Transform
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();
        String result = "";
        try{
            result = new String(bArray, "UTF-8");
            result = URLEncoder.encode(result, "UTF-8");
        }
        catch (Exception ex){
            Log.d("Error", "Unable to convert byte array to string: " + ex.toString());
        }
        //Log.d("IMG", result);
        return result;
    }

    public static void ShowAlertDialog(final Activity activity, final String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(activity)
                        .setMessage(message)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });
    }

    public static void NotifyDeviceOnlineStatus(final Activity activity){
        Snackbar.make(activity.findViewById(android.R.id.content), "Connecting to server...", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        new Thread(new Runnable(){
            @Override
            public void run() {
                if(DatabaseManager.IsWebAppOnline()){
                    Snackbar.make(activity.findViewById(android.R.id.content), "Device Online.", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
                else{
                    Snackbar.make(activity.findViewById(android.R.id.content), "Device Offline.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        }).start();
    }



}
