package com.ldf.daggersample.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by mac on 2017/11/30.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}
