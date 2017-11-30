package com.ldf.daggersample.module;

import com.ldf.daggersample.bean.Flower;
import com.ldf.daggersample.bean.User;
import com.ldf.daggersample.qualifier.GrassQualifier;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 2017/11/30.
 */

@Module
public class UserModule {

    @Singleton
    @Provides
    User provideUser(@GrassQualifier Flower flower) {
        return new User(flower);
    }
}
