package com.example.powerstrentgh.Developer.UserPanel.UserModelClass;

public class AddRequserDetailsToDatabase {
    String userID,username,userimageurl,reqID
            ;

    public AddRequserDetailsToDatabase() {
    }

    public AddRequserDetailsToDatabase(String userID, String username, String userimageurl, String reqID) {
        this.userID = userID;
        this.username = username;
        this.userimageurl = userimageurl;
        this.reqID = reqID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserimageurl() {
        return userimageurl;
    }

    public void setUserimageurl(String userimageurl) {
        this.userimageurl = userimageurl;
    }

    public String getReqID() {
        return reqID;
    }

    public void setReqID(String reqID) {
        this.reqID = reqID;
    }
}
