package com.harman.zrzotkiewicz.harmanfoostracker;

import java.util.ArrayList;
import java.util.List;

public class AddPlayerHelper {


    public static String CreateNewPlayer(PlayerData playerData){
        String error = "null";

        // Validate name
        if(playerData.PlayerName.length() <= 1)
            return "Your name is too short.";

        // Make sure name is not taken
        String playerNameLower = playerData.PlayerName;
        playerNameLower = playerNameLower.toLowerCase();
        for(String name : DatabaseManager.GetPlayerNames())
            if(playerNameLower.equals(name.toLowerCase()))
                return "The user name " + playerData.PlayerName + " is already taken.";

        // Verify that jersey number was not taken
        for(String jerseyNumber : DatabaseManager.GetUsedJerseyNumbers())
            if(playerData.JerseyNumber.equals(jerseyNumber))
                return "The jersey number " + playerData.JerseyNumber + " is already taken.";

        // Validate photo
        if(playerData.PhotoBlob == "")
            return "You have to provide a photo.";

        // Validate pin
        if(playerData.Pin.length() != 4)
            return "Your PIN must be 4 digits long.";

        // Validate pin
        if(!playerData.Pin.equals(playerData.PinConfirm))
            return "Your PINs did not match.";

        return error;
    }


    public static List<String> GetAvailableJerseyNumbers(){
        List<String> availableJerseyNumbers = new ArrayList<>();
        List<String> usedJerseyNumbers = DatabaseManager.GetUsedJerseyNumbers();

        for(int i = 0; i < 100; i++)
            if(!usedJerseyNumbers.contains(Integer.toString(i)))
                availableJerseyNumbers.add(Integer.toString(i));

        return availableJerseyNumbers;
    }


}
