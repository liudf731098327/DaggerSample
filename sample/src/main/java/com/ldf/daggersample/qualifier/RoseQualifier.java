package com.ldf.daggersample.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by mac on 2017/11/30.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface RoseQualifier {
}
