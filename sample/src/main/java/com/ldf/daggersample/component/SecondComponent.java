package com.ldf.daggersample.component;

import com.ldf.daggersample.SecondActivity;
import com.ldf.daggersample.scope.AppScope;

import dagger.Component;

/**
 * Created by mac on 2017/11/30.
 */

@AppScope
@Component(dependencies = UserComponent.class)
public interface SecondComponent {
    void inject(SecondActivity activity);
}
