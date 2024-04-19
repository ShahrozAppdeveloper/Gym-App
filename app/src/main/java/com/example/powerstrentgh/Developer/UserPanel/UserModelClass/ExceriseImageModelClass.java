package com.example.powerstrentgh.Developer.UserPanel.UserModelClass;

public class ExceriseImageModelClass {
    int image;
    String name;

    public ExceriseImageModelClass(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public ExceriseImageModelClass() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
