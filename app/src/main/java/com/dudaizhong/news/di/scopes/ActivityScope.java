package com.dudaizhong.news.di.scopes;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Dudaizhong on 2016/10/12.
 */

@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
