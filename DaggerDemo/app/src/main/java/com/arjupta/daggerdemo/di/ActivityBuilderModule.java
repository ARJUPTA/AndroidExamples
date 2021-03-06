package com.arjupta.daggerdemo.di;

import com.arjupta.daggerdemo.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();
}
