package com.ldf.daggersample.bean;

/**
 * Created by mac on 2017/11/30.
 */

public class User {
    Flower flower;

    public User(Flower flower) {
        this.flower = flower;
    }

    public String show() {
        return flower.say();
    }
}
