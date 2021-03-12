package com.arjupta.daggerdemo.di;

import com.arjupta.daggerdemo.di.auth.AuthModule;
import com.arjupta.daggerdemo.di.auth.AuthViewModelModule;
import com.arjupta.daggerdemo.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelModule.class,
                    AuthModule.class
            }
    )
    public abstract AuthActivity contributeAuthActivity();
}
