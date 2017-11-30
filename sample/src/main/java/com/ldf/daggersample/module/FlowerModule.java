package com.ldf.daggersample.module;

import com.ldf.daggersample.bean.Flower;
import com.ldf.daggersample.bean.Grass;
import com.ldf.daggersample.bean.Rose;
import com.ldf.daggersample.qualifier.GrassQualifier;
import com.ldf.daggersample.qualifier.RoseQualifier;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mac on 2017/11/30.
 */

@Module
public class FlowerModule {

    @RoseQualifier
    @Provides
    Flower provideRose() {
        return new Rose();
    }

    @GrassQualifier
    @Provides
    Flower provideGrass() {
        return new Grass();
    }
}
