package com.example.powerstrentgh.ModelCLass;

public class UserCreateChallengeModelClass {

    String name,vediourl;
    int img;

    public UserCreateChallengeModelClass(String name, String vediourl, int img) {
        this.name = name;
        this.vediourl = vediourl;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVediourl() {
        return vediourl;
    }

    public void setVediourl(String vediourl) {
        this.vediourl = vediourl;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
