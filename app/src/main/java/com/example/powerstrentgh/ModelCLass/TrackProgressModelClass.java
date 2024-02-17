package com.example.powerstrentgh.ModelCLass;

public class TrackProgressModelClass {
    String imageurl,imageName;

    public TrackProgressModelClass(String imageurl, String imageName) {
        this.imageurl = imageurl;
        this.imageName = imageName;
    }

    public TrackProgressModelClass() {
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
