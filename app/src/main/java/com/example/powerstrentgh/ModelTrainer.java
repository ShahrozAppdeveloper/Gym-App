package com.example.powerstrentgh;

public class ModelTrainer {
    String userid,image,username;

    public ModelTrainer() {
    }

    public ModelTrainer(String userid, String image, String username) {
        this.userid = userid;
        this.image = image;
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
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
}
