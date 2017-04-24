package com.harman.zrzotkiewicz.harmanfoostracker;

import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GameState {

    //region Variables
    private static Boolean GameActive = false;
    private static String RedTeamPlayer1Name = "";
    private static String RedTeamPlayer2Name = "";
    private static String BlueTeamPlayer1Name = "";
    private static String BlueTeamPlayer2Name = "";
    private static int RedTeamPlayer1OffensiveGoals = 0;
    private static int RedTeamPlayer1DefensiveGoals = 0;
    private static int RedTeamPlayer1OwnGoals = 0;
    private static int RedTeamPlayer1SlapBacks = 0;
    private static int RedTeamPlayer2OffensiveGoals = 0;
    private static int RedTeamPlayer2DefensiveGoals = 0;
    private static int RedTeamPlayer2OwnGoals = 0;
    private static int RedTeamPlayer2SlapBacks = 0;
    private static int BlueTeamPlayer1OffensiveGoals = 0;
    private static int BlueTeamPlayer1DefensiveGoals = 0;
    private static int BlueTeamPlayer1OwnGoals = 0;
    private static int BlueTeamPlayer1SlapBacks = 0;
    private static int BlueTeamPlayer2OffensiveGoals = 0;
    private static int BlueTeamPlayer2DefensiveGoals = 0;
    private static int BlueTeamPlayer2OwnGoals = 0;
    private static int BlueTeamPlayer2SlapBacks = 0;
    private static String StartTime;
    private static String EndTime;
    private static String GameType = "";
    //endregion

    //region General Game
    public static Boolean IsGameActive(){
        return GameActive;
    }

    public static void ResetGameState(){
        Log.d("INFO", "Resetting game state.");
        GameActive = false;
        RedTeamPlayer1Name = "";
        RedTeamPlayer2Name = "";
        BlueTeamPlayer1Name = "";
        BlueTeamPlayer2Name = "";
        RedTeamPlayer1OffensiveGoals = 0;
        RedTeamPlayer1DefensiveGoals = 0;
        RedTeamPlayer1OwnGoals = 0;
        RedTeamPlayer1SlapBacks = 0;
        RedTeamPlayer2OffensiveGoals = 0;
        RedTeamPlayer2DefensiveGoals = 0;
        RedTeamPlayer2OwnGoals = 0;
        RedTeamPlayer2SlapBacks = 0;
        BlueTeamPlayer1OffensiveGoals = 0;
        BlueTeamPlayer1DefensiveGoals = 0;
        BlueTeamPlayer1OwnGoals = 0;
        BlueTeamPlayer1SlapBacks = 0;
        BlueTeamPlayer2OffensiveGoals = 0;
        BlueTeamPlayer2DefensiveGoals = 0;
        BlueTeamPlayer2OwnGoals = 0;
        BlueTeamPlayer2SlapBacks = 0;
        GameType = "";
    }

    public static void SetGameType(String gameType) { GameType = gameType; }

    public static String GetGameType() { return GameType; }

    public static void SetGameActive(){
        GameActive = true;
    }

    public static void SetStartTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StartTime = sdf.format(c.getTime());
    }

    public static String GetStartTime(){
        return StartTime;
    }

    public static void SetEndTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        EndTime = sdf.format(c.getTime());
    }

    public static String GetEndTime(){
        return EndTime;
    }
    //endregion

    //region Team Specific
    public static int GetRedTeamScore(){
        return (RedTeamPlayer1OffensiveGoals + RedTeamPlayer1DefensiveGoals +
                RedTeamPlayer2OffensiveGoals + RedTeamPlayer2DefensiveGoals +
                BlueTeamPlayer1OwnGoals + BlueTeamPlayer2OwnGoals);
    }

    public static int GetBlueTeamScore(){
        return (BlueTeamPlayer1OffensiveGoals + BlueTeamPlayer1DefensiveGoals +
                BlueTeamPlayer2OffensiveGoals + BlueTeamPlayer2DefensiveGoals +
                RedTeamPlayer1OwnGoals + RedTeamPlayer2OwnGoals);
    }

    //endregion

    //region Red Team Player 1
    public static void SetRTP1Name(String name){
        RedTeamPlayer1Name = name;
    }

    public static String GetRTP1Name(){
        return RedTeamPlayer1Name;
    }

    public static void AddRTP1OffG(){
        if(RedTeamPlayer1OffensiveGoals<10)
        RedTeamPlayer1OffensiveGoals++;
    }

    public static void SubRTP1OffG(){
        if(RedTeamPlayer1OffensiveGoals>0)
            RedTeamPlayer1OffensiveGoals--;
    }

    public static int GetRTP1OffG(){
        return RedTeamPlayer1OffensiveGoals;
    }

    public static void AddRTP1DefG(){
        if(RedTeamPlayer1DefensiveGoals<10)
            RedTeamPlayer1DefensiveGoals++;
    }

    public static void SubRTP1DefG(){
        if(RedTeamPlayer1DefensiveGoals>0)
            RedTeamPlayer1DefensiveGoals--;
    }

    public static int GetRTP1DefG(){
        return RedTeamPlayer1DefensiveGoals;
    }

    public static void AddRTP1OwnG(){
        if(RedTeamPlayer1OwnGoals<10)
            RedTeamPlayer1OwnGoals++;
    }

    public static void SubRTP1OwnG(){
        if(RedTeamPlayer1OwnGoals>0)
            RedTeamPlayer1OwnGoals--;
    }

    public static int GetRTP1OwnG(){
        return RedTeamPlayer1OwnGoals;
    }

    public static void AddRTP1Slap(){
        RedTeamPlayer1SlapBacks++;
    }

    public static void SubRTP1Slap(){
        if(RedTeamPlayer1SlapBacks>0)
            RedTeamPlayer1SlapBacks--;
    }

    public static int GetRTP1Slaps(){
        return RedTeamPlayer1SlapBacks;
    }
    //endregion

    //region Red Team Player 2
    public static void SetRTP2Name(String name){
        RedTeamPlayer2Name = name;
    }

    public static String GetRTP2Name(){
        return RedTeamPlayer2Name;
    }

    public static void AddRTP2OffG(){
        if(RedTeamPlayer2OffensiveGoals<10)
            RedTeamPlayer2OffensiveGoals++;
    }

    public static void SubRTP2OffG(){
        if(RedTeamPlayer2OffensiveGoals>0)
            RedTeamPlayer2OffensiveGoals--;
    }

    public static int GetRTP2OffG(){
        return RedTeamPlayer2OffensiveGoals;
    }

    public static void AddRTP2DefG(){
        if(RedTeamPlayer2DefensiveGoals<10)
            RedTeamPlayer2DefensiveGoals++;
    }

    public static void SubRTP2DefG(){
        if(RedTeamPlayer2DefensiveGoals>0)
            RedTeamPlayer2DefensiveGoals--;
    }

    public static int GetRTP2DefG(){
        return RedTeamPlayer2DefensiveGoals;
    }

    public static void AddRTP2OwnG(){
        if(RedTeamPlayer2OwnGoals<10)
            RedTeamPlayer2OwnGoals++;
    }

    public static void SubRTP2OwnG(){
        if(RedTeamPlayer2OwnGoals>0)
            RedTeamPlayer2OwnGoals--;
    }

    public static int GetRTP2OwnG(){
        return RedTeamPlayer2OwnGoals;
    }

    public static void AddRTP2Slap(){
        RedTeamPlayer2SlapBacks++;
    }

    public static void SubRTP2Slap(){
        if(RedTeamPlayer2SlapBacks>0)
            RedTeamPlayer2SlapBacks--;
    }

    public static int GetRTP2Slaps(){
        return RedTeamPlayer2SlapBacks;
    }
    //endregion

    //region Blue Team Player 1
    public static void SetBTP1Name(String name){
        BlueTeamPlayer1Name = name;
    }

    public static String GetBTP1Name(){
        return BlueTeamPlayer1Name;
    }

    public static void AddBTP1OffG(){
        if(BlueTeamPlayer1OffensiveGoals<10)
            BlueTeamPlayer1OffensiveGoals++;
    }

    public static void SubBTP1OffG(){
        if(BlueTeamPlayer1OffensiveGoals>0)
            BlueTeamPlayer1OffensiveGoals--;
    }

    public static int GetBTP1OffG(){
        return BlueTeamPlayer1OffensiveGoals;
    }

    public static void AddBTP1DefG(){
        if(BlueTeamPlayer1DefensiveGoals<10)
            BlueTeamPlayer1DefensiveGoals++;
    }

    public static void SubBTP1DefG(){
        if(BlueTeamPlayer1DefensiveGoals>0)
            BlueTeamPlayer1DefensiveGoals--;
    }

    public static int GetBTP1DefG(){
        return BlueTeamPlayer1DefensiveGoals;
    }

    public static void AddBTP1OwnG(){
        if(BlueTeamPlayer1OwnGoals<10)
            BlueTeamPlayer1OwnGoals++;
    }

    public static void SubBTP1OwnG(){
        if(BlueTeamPlayer1OwnGoals>0)
            BlueTeamPlayer1OwnGoals--;
    }

    public static int GetBTP1OwnG(){
        return BlueTeamPlayer1OwnGoals;
    }

    public static void AddBTP1Slap(){
        BlueTeamPlayer1SlapBacks++;
    }

    public static void SubBTP1Slap(){
        if(BlueTeamPlayer1SlapBacks>0)
            BlueTeamPlayer1SlapBacks--;
    }

    public static int GetBTP1Slaps(){
        return BlueTeamPlayer1SlapBacks;
    }
    //endregion

    //region Blue Team Player 2
    public static void SetBTP2Name(String name){
        BlueTeamPlayer2Name = name;
    }

    public static String GetBTP2Name(){
        return BlueTeamPlayer2Name;
    }

    public static void AddBTP2OffG(){
        if(BlueTeamPlayer2OffensiveGoals<10)
            BlueTeamPlayer2OffensiveGoals++;
    }

    public static void SubBTP2OffG(){
        if(BlueTeamPlayer2OffensiveGoals>0)
            BlueTeamPlayer2OffensiveGoals--;
    }

    public static int GetBTP2OffG(){
        return BlueTeamPlayer2OffensiveGoals;
    }

    public static void AddBTP2DefG(){
        if(BlueTeamPlayer2DefensiveGoals<10)
            BlueTeamPlayer2DefensiveGoals++;
    }

    public static void SubBTP2DefG(){
        if(BlueTeamPlayer2DefensiveGoals>0)
            BlueTeamPlayer2DefensiveGoals--;
    }

    public static int GetBTP2DefG(){
        return BlueTeamPlayer2DefensiveGoals;
    }

    public static void AddBTP2OwnG(){
        if(BlueTeamPlayer2OwnGoals<10)
            BlueTeamPlayer2OwnGoals++;
    }

    public static void SubBTP2OwnG(){
        if(BlueTeamPlayer2OwnGoals>0)
            BlueTeamPlayer2OwnGoals--;
    }

    public static int GetBTP2OwnG(){
        return BlueTeamPlayer2OwnGoals;
    }

    public static void AddBTP2Slap(){
        BlueTeamPlayer2SlapBacks++;
    }

    public static void SubBTP2Slap(){
        if(BlueTeamPlayer2SlapBacks>0)
            BlueTeamPlayer2SlapBacks--;
    }

    public static int GetBTP2Slaps(){
        return BlueTeamPlayer2SlapBacks;
    }
    //endregion

}
