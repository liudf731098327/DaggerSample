package com.ldf.daggersample.bean;

import javax.inject.Inject;

/**
 * Created by mac on 2017/11/30.
 */

public class Grass implements Flower {

    @Inject
    public Grass(){

    }

    @Override
    public String say() {
        return "我是草";
    }
}
