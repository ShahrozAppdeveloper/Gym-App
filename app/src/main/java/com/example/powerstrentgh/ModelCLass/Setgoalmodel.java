package com.example.powerstrentgh.ModelCLass;

import android.content.Context;

import java.io.Serializable;

public class Setgoalmodel implements Serializable {
    private String name,desc;

    public Setgoalmodel(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
