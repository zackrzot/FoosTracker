package com.harman.zrzotkiewicz.harmanfoostracker.objects;


import java.util.ArrayList;
import java.util.List;

public class Player {

    public enum TEAM { RED, BLUE }

    public enum OUTCOME { WIN, LOSS }

    public TEAM PlayerTeam;
    public String PlayerName;
    public OUTCOME PlayerOutcome;
    private List<Point> OffGoals;
    private List<Point> DefGoals;
    private List<Point> OwnGoals;
    private int Slaps = 0;

    public Player(TEAM playerTeam, String playerName){
        PlayerTeam = playerTeam;
        PlayerName = playerName;
        OffGoals = new ArrayList<>();
    }

    //region Slaps
    public void AddSlap(){
        Slaps++;
    }

    public void SubSlap(){
        if(Slaps > 0)
            Slaps--;
    }

    public int GetSlap(){
        return Slaps;
    }
    //endregion

    //region Offensive Goals
    public void AddOffGoal(){
        OffGoals.add(new Point());
    }

    public void SubOffGoal(){
        if(!OffGoals.isEmpty())
            OffGoals.remove(OffGoals.size()-1);
    }

    public int GetNumOffGoals(){
        return OffGoals.size();
    }
    //endregion

    //region Defensive Goals
    public void AddDefGoal(){
        DefGoals.add(new Point());
    }

    public void SubDefGoal(){
        if(!DefGoals.isEmpty())
            DefGoals.remove(DefGoals.size()-1);
    }

    public int GetNumDefGoals(){
        return DefGoals.size();
    }
    //endregion

    //region Own Goals
    public void AddOwnGoal(){
        OwnGoals.add(new Point());
    }

    public void SubOwnGoal(){
        if(!OwnGoals.isEmpty())
            OwnGoals.remove(OwnGoals.size()-1);
    }

    public int GetNumOwnGoals(){
        return OwnGoals.size();
    }
    //endregion


}
