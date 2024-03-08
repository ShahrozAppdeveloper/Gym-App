package com.example.powerstrentgh.Developer.UserPanel;

public class AdduserDetailsToDatabase {
    String userID,username,userimageurl;

    public AdduserDetailsToDatabase() {
    }

    public AdduserDetailsToDatabase(String userID, String username, String userimageurl) {
        this.userID = userID;
        this.username = username;
        this.userimageurl = userimageurl;
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
}
