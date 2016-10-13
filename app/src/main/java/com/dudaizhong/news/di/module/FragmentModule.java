package com.dudaizhong.news.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.dudaizhong.news.di.scopes.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dudaizhong on 2016/10/12.
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity(){
        return fragment.getActivity();
    }
}
