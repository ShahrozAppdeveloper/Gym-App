package com.example.powerstrentgh.ModelCLass;

public class CertificateClass {
    String userid,image;

    public CertificateClass(String userid, String image) {
        this.userid = userid;
        this.image = image;
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
}
