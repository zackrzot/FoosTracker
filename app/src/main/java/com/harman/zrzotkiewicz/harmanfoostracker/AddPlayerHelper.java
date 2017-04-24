package com.harman.zrzotkiewicz.harmanfoostracker;

public class AddPlayerHelper {


    public static String CreateNewPlayer(PlayerData playerData){
        String error = "null";

        // Validate name
        if(playerData.PlayerName.length() <= 1)
            return "Your name is too short.";






        return error;
    }


}
