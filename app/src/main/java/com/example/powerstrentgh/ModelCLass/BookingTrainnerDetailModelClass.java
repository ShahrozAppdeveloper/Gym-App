package com.example.powerstrentgh.ModelCLass;

public class BookingTrainnerDetailModelClass {
    String userid,username,userimage,TrainnnerID;

    public BookingTrainnerDetailModelClass() {
    }

    public BookingTrainnerDetailModelClass(String userid, String username, String userimage, String trainnnerID) {
        this.userid = userid;
        this.username = username;
        this.userimage = userimage;
        TrainnnerID = trainnnerID;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getTrainnnerID() {
        return TrainnnerID;
    }

    public void setTrainnnerID(String trainnnerID) {
        TrainnnerID = trainnnerID;
    }
}
