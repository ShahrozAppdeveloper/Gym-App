package com.example.powerstrentgh.ModelCLass;

public class CurrentStatusDetails {
    String UerID,curentstatus,Email,Passowrd;

    public CurrentStatusDetails(String uerID, String curentstatus, String email, String passowrd) {
        UerID = uerID;
        this.curentstatus = curentstatus;
        Email = email;
        Passowrd = passowrd;
    }

    public CurrentStatusDetails() {
    }

    public String getUerID() {
        return UerID;
    }

    public void setUerID(String uerID) {
        UerID = uerID;
    }

    public String getCurentstatus() {
        return curentstatus;
    }

    public void setCurentstatus(String curentstatus) {
        this.curentstatus = curentstatus;
    }



    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassowrd() {
        return Passowrd;
    }

    public void setPassowrd(String passowrd) {
        Passowrd = passowrd;
    }
}
