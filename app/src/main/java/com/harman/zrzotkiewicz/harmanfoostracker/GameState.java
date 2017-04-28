package com.harman.zrzotkiewicz.harmanfoostracker;

import android.util.Log;

import com.harman.zrzotkiewicz.harmanfoostracker.objects.Player;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GameState {

    //region Variables
    public static String StartTime = null;
    public static String EndTime = null;
    public static String GameType = null;
    public static Player RTP1 = null;
    public static Player RTP2 = null;
    public static Player BTP1 = null;
    public static Player BTP2 = null;
    //endregion



    public static void ResetGameState(){
        Log.d("INFO", "Resetting game state.");
        StartTime = null;
        EndTime = null;
        GameType = null;
        RTP1 = null;
        RTP2 = null;
        BTP1 = null;
        BTP2 = null;
    }

    public static String GetGameType() {
        if(RTP2 == null && BTP2 == null)
            return "1V1";
        else if(RTP2 != null && BTP2 != null)
            return "2V2";
        else
            return "1V2";
    }

    public static Boolean IsGameActive(){
        if(RTP1 == null && BTP1 == null)
            return false;
        return true;
    }

    public static void SetStartTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StartTime = sdf.format(c.getTime());
    }

    public static void SetEndTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        EndTime = sdf.format(c.getTime());
    }



}
