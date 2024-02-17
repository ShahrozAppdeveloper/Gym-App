package com.example.powerstrentgh.ModelCLass;

public class AddtrainnerDetailTorealtime {
    String userid,username,image;

    public AddtrainnerDetailTorealtime() {
    }

    public AddtrainnerDetailTorealtime(String userid, String username, String image) {
        this.userid = userid;
        this.username = username;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
