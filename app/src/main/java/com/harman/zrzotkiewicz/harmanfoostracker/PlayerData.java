package com.harman.zrzotkiewicz.harmanfoostracker;


public class PlayerData {

    public String PlayerName;
    public String Pin;
    public String PinConfirm;
    public String DOB;
    public String Hometown;
    public String Bio;
    public String JerseyNumber;
    public String Handedness;
    public String Height;
    public String Weight;
    public String PhotoBlob;

    public PlayerData(){}

    public Boolean AreAnyFieldsEmpty(){
        if (PlayerName.equals("") || Pin.equals("") ||PinConfirm.equals("") ||
                DOB.equals("") || Hometown.equals("") || Bio.equals("") ||
                JerseyNumber.equals("") || Handedness.equals("") ||
                Height.equals("") || Weight.equals("") || PhotoBlob.equals("")){
            return true;
        }
        return false;
    }


}
