package com.example.powerstrentgh;

public class ModelClassPopular {
int img;
String head,desc;

    public ModelClassPopular(int img, String head, String desc) {
        this.img = img;
        this.head = head;
        this.desc = desc;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
