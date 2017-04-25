package com.harman.zrzotkiewicz.harmanfoostracker;

public class AddPlayerHelper {


    public static String CreateNewPlayer(PlayerData playerData){
        String error = "null";

        // Validate name
        if(playerData.PlayerName.length() <= 1)
            return "Your name is too short.";

        // Validate name
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


}
