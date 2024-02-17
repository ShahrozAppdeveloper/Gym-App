package com.example.powerstrentgh.ModelCLass;

public class CoachmodelClass{
    String image,username,userid;

    public CoachmodelClass() {
    }

    public CoachmodelClass(String image, String username, String userid) {
        this.image = image;
        this.username = username;
        this.userid = userid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
