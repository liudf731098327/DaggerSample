package com.ldf.daggersample.component;

import com.ldf.daggersample.MainActivity;
import com.ldf.daggersample.scope.AppScope;

import dagger.Component;

/**
 * Created by mac on 2017/11/30.
 */

@AppScope
@Component(dependencies = UserComponent.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
}
