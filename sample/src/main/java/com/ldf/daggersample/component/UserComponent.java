package com.ldf.daggersample.component;

import com.ldf.daggersample.bean.User;
import com.ldf.daggersample.module.UserModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mac on 2017/11/30.
 */

@Singleton
@Component(modules = UserModule.class, dependencies = FlowerComponent.class)
public interface UserComponent {
    User getUser();
}
