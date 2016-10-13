package com.dudaizhong.news.di.module;

import android.app.Activity;

import com.dudaizhong.news.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dudaizhong on 2016/10/12.
 */

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity(){
        return activity;
    }
}
