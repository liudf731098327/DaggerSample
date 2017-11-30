package com.ldf.daggersample.component;

import com.ldf.daggersample.bean.Flower;
import com.ldf.daggersample.module.FlowerModule;
import com.ldf.daggersample.qualifier.GrassQualifier;
import com.ldf.daggersample.qualifier.RoseQualifier;

import dagger.Component;

/**
 * Created by mac on 2017/11/30.
 */

@Component(modules = FlowerModule.class)
public interface FlowerComponent {

    @RoseQualifier
    Flower getRose();

    @GrassQualifier
    Flower getGrass();
}
