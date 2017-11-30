package com.ldf.daggersample;

import android.app.Application;

import com.ldf.daggersample.component.DaggerFlowerComponent;
import com.ldf.daggersample.component.DaggerUserComponent;
import com.ldf.daggersample.component.UserComponent;

/**
 * Created by mac on 2017/11/30.
 */

public class BaseApplication extends Application {

    UserComponent userComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        userComponent = DaggerUserComponent.builder().flowerComponent(DaggerFlowerComponent.create()).build();
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }
}
