package com.example.powerstrentgh.ModelCLass;

public class AddUserDetailToRealtime {
    String userID,curentusername,imageul;

    public AddUserDetailToRealtime() {
    }

    public AddUserDetailToRealtime(String userID, String curentusername, String imageul) {
        this.userID = userID;
        this.curentusername = curentusername;
        this.imageul = imageul;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCurentusername() {
        return curentusername;
    }

    public void setCurentusername(String curentusername) {
        this.curentusername = curentusername;
    }

    public String getImageul() {
        return imageul;
    }

    public void setImageul(String imageul) {
        this.imageul = imageul;
    }
}
