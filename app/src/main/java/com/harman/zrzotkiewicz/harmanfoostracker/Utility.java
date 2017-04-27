package com.harman.zrzotkiewicz.harmanfoostracker;


import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import org.apache.commons.codec.binary.Base64;

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

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        byte[] bArray = bos.toByteArray();

        bArray = encodeUrlSafe(bArray);

        String result = new String(bArray);

        return result;
    }

    public static byte[] encodeUrlSafe(byte[] data) {
        byte[] encode = Base64.encodeBase64(data);
        for (int i = 0; i < encode.length; i++) {
            if (encode[i] == '+') {
                encode[i] = '-';
            } else if (encode[i] == '/') {
                encode[i] = '_';
            }
        }
        return encode;
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

    public static String FixInvalidChars(String text){
        text = text.replace("\\","");
        text = text.replace("\"","");
        text = text.replace("'","");
        return text;
    }


}
