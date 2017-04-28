package com.harman.zrzotkiewicz.harmanfoostracker.objects;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Point {

    public static String timeStamp = null;

    public Point(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeStamp = sdf.format(c.getTime());
    }

}
